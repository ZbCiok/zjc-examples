package zjc.examples.vertx.hibernate;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.http.HttpServer;
import io.vertx.mutiny.ext.web.Router;
import io.vertx.mutiny.ext.web.RoutingContext;
import io.vertx.mutiny.ext.web.handler.BodyHandler;
import org.hibernate.reactive.mutiny.Mutiny;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Persistence;
import java.util.List;
import java.util.Map;


public class MainVerticle extends AbstractVerticle {

  private static final Logger logger = LoggerFactory.getLogger(MainVerticle.class);
  private Mutiny.SessionFactory emf;  // <1>

  @Override
  public Uni<Void> asyncStart() {
    Uni<Void> startHibernate = Uni.createFrom().deferred(() -> {
      var pgPort = config().getInteger("pgPort", 5432);
      var props = Map.of("javax.persistence.jdbc.url", "jdbc:postgresql://localhost:" + pgPort + "/vertx-rest");  // <1>

      emf = Persistence
        .createEntityManagerFactory("pg-demo", props)
        .unwrap(Mutiny.SessionFactory.class);

      return Uni.createFrom().voidItem();
    });

    startHibernate = vertx.executeBlocking(startHibernate)  // <2>
      .onItem().invoke(() -> logger.info("✅ Hibernate Reactive is ready"));

    // ----- router
    Router router = Router.router(vertx);

    BodyHandler bodyHandler = BodyHandler.create();
    router.post().handler(bodyHandler::handle);

    router.get("/products").respond(this::listProducts);
    router.get("/products/:id").respond(this::getProduct);
    router.post("/products").respond(this::createProduct);


    Uni<HttpServer> startHttpServer = vertx.createHttpServer()
      .requestHandler(router)
      .listen(8080)
      .onItem().invoke(() -> logger.info("✅ HTTP server listening on port 8080"));

    return Uni.combine().all().unis(startHibernate, startHttpServer).discardItems();  // <1>
  }

  private Uni<List<Product>> listProducts(RoutingContext ctx) {
    return emf.withSession(session -> session
      .createQuery("from Product", Product.class)
      .getResultList());
  }

  private Uni<Product> getProduct(RoutingContext ctx) {
    long id = Long.parseLong(ctx.pathParam("id"));
    return emf.withSession(session -> session
      .find(Product.class, id))
      .onItem().ifNull().continueWith(Product::new);
  }

  private Uni<Product> createProduct(RoutingContext ctx) {
    Product product = ctx.body().asPojo(Product.class);
    return emf.withSession(session -> session.
      persist(product)
      .call(session::flush)
      .replaceWith(product));
  }

  public static void main(String[] args) {

    long startTime = System.currentTimeMillis();

    logger.info("🚀 Starting a PostgreSQL");

    io.vertx.core.Vertx vertx1 = io.vertx.core.Vertx.vertx();
    vertx1.deployVerticle(new MigrationVerticle());


    long tcTime = System.currentTimeMillis();

    logger.info("🚀 Starting Vert.x");


    Vertx vertx = Vertx.vertx();

    DeploymentOptions options = new DeploymentOptions().setConfig(new JsonObject()
            .put("pgPort", 5432)); // <1>


    vertx.deployVerticle(MainVerticle::new, options).subscribe().with(  // <2>
      ok -> {
        long vertxTime = System.currentTimeMillis();
        logger.info("✅ Deployment success");
        logger.info("💡 PostgreSQL started in {}ms", (tcTime - startTime));
        logger.info("💡 Vert.x app started in {}ms", (vertxTime - tcTime));
      },
      err -> logger.error("🔥 Deployment failure", err));
  }
}
