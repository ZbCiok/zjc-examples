package zjc.examples.vertx.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

public class SampleVerticle extends AbstractVerticle {
    private long startTime = System.currentTimeMillis();
    private long counter = 1;
    
    @Override
    public void start() {
        vertx.setPeriodic(2000, counter -> {
            long runTime = (System.currentTimeMillis() - startTime) / 1000;
            System.out.println("Server run time: " + runTime + " seconds.");
        });

        vertx.createHttpServer()
            .requestHandler(req -> {
                System.out.println("Request #" + counter++ +
                                   " from " + req.remoteAddress().host());
                req.response().end("Hello from SampleVerticle!");
            })
            .listen(8080);
        
        System.out.println("==================================================");
        System.out.println("=== SampleVerticle listening on localhost:8080 ===");
        System.out.println("==================================================");
    }

    @Override
    public void stop() {
        System.out.println("===================================");
        System.out.println("=== SampleVerticle signing off! ===");
        System.out.println("====================================");
    }
    
    public static void main (String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new SampleVerticle());
    }
}
