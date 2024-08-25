package com.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class MyApp extends AbstractVerticle {

    @Override
    public void start(Promise<Void> promise) {
        vertx
            .createHttpServer()
            .requestHandler(r ->
                r.response().end("Greetings!"))
            .listen(8080, result -> {
                if (result.succeeded()) {
                    promise.complete();
                } else {
                    promise.fail(result.cause());
                }
            });
    }
}