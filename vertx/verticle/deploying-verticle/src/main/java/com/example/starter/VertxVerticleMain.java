package com.example.starter;

import io.vertx.core.Vertx;

public class VertxVerticleMain {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new MyVerticle());
  }
}
