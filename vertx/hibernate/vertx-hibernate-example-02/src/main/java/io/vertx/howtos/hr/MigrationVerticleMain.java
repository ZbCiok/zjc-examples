package io.vertx.howtos.hr;

import io.vertx.core.Vertx;
import io.vertx.howtos.hr.MigrationVerticle;

public class MigrationVerticleMain {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MigrationVerticle());
    }
}
