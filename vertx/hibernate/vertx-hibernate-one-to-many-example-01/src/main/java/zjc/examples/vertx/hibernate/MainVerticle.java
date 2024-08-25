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
import zjc.examples.vertx.hibernate.model.Comment;
import zjc.examples.vertx.hibernate.model.Product;
import org.hibernate.reactive.mutiny.Mutiny;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static jakarta.persistence.Persistence.createEntityManagerFactory;
import static org.hibernate.reactive.mutiny.Mutiny.SessionFactory;

import java.util.List;
import java.util.Map;


public class MainVerticle extends AbstractVerticle {

  private static final Logger logger = LoggerFactory.getLogger(MainVerticle.class);
  private Mutiny.SessionFactory emf;
  private static SessionFactory factory;

  @Override
  public Uni<Void> asyncStart() {

    // hibernate-start
    Uni<Void> startHibernate = Uni.createFrom().deferred(() -> {
      var pgPort = config().getInteger("pgPort", 5432);
      var props = Map.of("jakarta.persistence.jdbc.url", "jdbc:postgresql://localhost:" + pgPort + "/vertx-rest");  // <1>

      emf = createEntityManagerFactory("pg-demo", props)
        .unwrap(Mutiny.SessionFactory.class);

      return Uni.createFrom().voidItem();
    });

    startHibernate = vertx.executeBlocking(startHibernate)  // <2>
      .onItem().invoke(() -> logger.info("✅ Hibernate Reactive is ready"));
    // end hibernate-start


    // router
    Router router = Router.router(vertx);

    BodyHandler bodyHandler = BodyHandler.create();
    router.post().handler(bodyHandler::handle);

    router.get("/products").respond(this::listProducts);
    router.get("/products/:id").respond(this::getProduct);
    router.post("/products").respond(this::createProduct);

    router.get("/comments").respond(this::listComments);
    router.post("/products/:id/comments").respond(this::createComment);
    // end router

    // async-start[]
    Uni<HttpServer> startHttpServer = vertx.createHttpServer()
      .requestHandler(router)
      .listen(8080)
      .onItem().invoke(() -> logger.info("✅ HTTP server listening on port 8080"));

    return Uni.combine().all().unis(startHibernate, startHttpServer).discardItems();  // <1>
    // end async-start[]
  }

  // crud methods Product
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
  // end crud-methods Product

  // crud methods Comment
  private Uni<List<Comment>> listComments(RoutingContext ctx) {
    return emf.withSession(session -> session
            .createQuery("from Comment", Comment.class)
            .getResultList());
  }

  private Uni<Comment> createComment(RoutingContext ctx) {

    long id = Long.parseLong(ctx.pathParam("id"));
    Comment comment = ctx.body().asPojo(Comment.class);

    try {
      factory.withTransaction(
                      session -> session.find(Product.class, id).invoke(comment::setProduct)
              )
              .await().indefinitely();

      factory.withTransaction(
                      // persist the comment
                      (session, tx) -> session.persistAll(comment)
              )
              // wait for it to finish
              .await().indefinitely();

      return Uni.createFrom().item(comment);

    } finally {
      //factory.close();
    }
  }

  // end crud methods Comment

  public static void main(String[] args) {
    factory = createEntityManagerFactory( persistenceUnitName( args ) )
                    .unwrap(SessionFactory.class);

    long startTime = System.currentTimeMillis();

    logger.info("🚀 Starting a PostgreSQL container");
    io.vertx.core.Vertx vertx1 = io.vertx.core.Vertx.vertx();
    vertx1.deployVerticle(new MigrationVerticle());


    long tcTime = System.currentTimeMillis();

    logger.info("🚀 Starting Vert.x");

    // vertx-start[]
    Vertx vertx = Vertx.vertx();

    DeploymentOptions options = new DeploymentOptions().setConfig(new JsonObject()
            .put("pgPort", 5432));

    vertx.deployVerticle(MainVerticle::new, options).subscribe().with(  // <2>
      ok -> {
        long vertxTime = System.currentTimeMillis();
        logger.info("✅ Deployment success");
        logger.info("💡 PostgreSQL started in {}ms", (tcTime - startTime));
        logger.info("💡 Vert.x app started in {}ms", (vertxTime - tcTime));
      },
      err -> logger.error("🔥 Deployment failure", err));
    // end vertx-start[]
  }

  public static String persistenceUnitName(String[] args) {
    return args.length > 0 ? args[0] : "pg-demo";
  }

}
