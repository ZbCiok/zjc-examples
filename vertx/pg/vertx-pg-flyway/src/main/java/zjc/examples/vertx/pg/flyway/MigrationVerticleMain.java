package zjc.examples.vertx.pg.flyway;

import io.vertx.core.Vertx;

public class MigrationVerticleMain {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MigrationVerticle());
    }
}
