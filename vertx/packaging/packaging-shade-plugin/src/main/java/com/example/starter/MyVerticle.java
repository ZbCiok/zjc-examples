package com.example.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class MyVerticle  extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) {
    System.out.println("MyVerticle started!");
  }

  @Override
  public void stop(Promise<Void> startPromise) throws Exception {
    System.out.println("MyVerticle stopped!");
  }
}
