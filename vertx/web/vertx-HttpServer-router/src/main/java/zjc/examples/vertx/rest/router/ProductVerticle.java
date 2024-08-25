package zjc.examples.vertx.rest.router;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import java.io.FileNotFoundException;

public class ProductVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        vertx.createHttpServer(new HttpServerOptions())
                .requestHandler(getRouter())
                .listen(8080,asyncResult -> {
                    if (asyncResult.succeeded()) {
                        System.out.println("ProductVerticle SUCCESS");
                        startPromise.complete();
                    } else {
                        System.out.println("ProductVerticle FAILED");
                        startPromise.fail(asyncResult.cause());
                    }
                });
    }

    private Router getRouter() throws FileNotFoundException {
        Router router = Router.router(vertx);

        // call API and place message in MQ after completion
        router.post("/product").handler(this::createProductRecAndplaceMessageInMq);

        // GET request
        router.get("/product").handler(handler -> handler.response().end("all Product details "));

        // GET request with path parameters
        router.get("/product/:name").handler(handler -> {
            String name = handler.pathParam("name");
            handler.response().end(String.format("Product %s details ", name));
        });

        // Get reroute
        router.get("/producer").handler(a -> {
            a.reroute("/subproducer");
        });
        router.get("/subproducer").handler( i-> i.response().end("Hello subproducer"));

        // POST request
        router.post("/mail").handler(context -> {
            context.request().bodyHandler(System.out::println);
            context.response().end("done");
        });

        return router;
    }

    private void createProductRecAndplaceMessageInMq(RoutingContext ctx) {
        //  perform API call task...
        vertx.eventBus().request("mqHandlerOnAPICompletion","", reply -> ctx.request().response().end((String) "Created product record. Placing message in MQ: " + reply.result().body()));
    }

}