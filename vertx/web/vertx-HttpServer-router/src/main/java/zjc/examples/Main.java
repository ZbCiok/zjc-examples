package zjc.examples;

import io.vertx.core.Vertx;
import zjc.examples.vertx.rest.router.ProductVerticle;
import zjc.examples.vertx.rest.router.MQHandleVerticle;

public class Main {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new ProductVerticle());
        vertx.deployVerticle(new MQHandleVerticle());
    }
}