package zjc.examples.vertx.hibernate;

import io.vertx.core.Vertx;
import zjc.examples.vertx.hibernate.MigrationVerticle;

public class MigrationVerticleMain {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MigrationVerticle());
    }
}
