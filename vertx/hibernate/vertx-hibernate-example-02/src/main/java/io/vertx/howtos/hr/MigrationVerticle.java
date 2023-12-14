package io.vertx.howtos.hr;

import io.vertx.howtos.hr.utils.DbUtils;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.Configuration;

public class MigrationVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> promise) {

        final Configuration config = DbUtils.buildMigrationsConfiguration();

        final Flyway flyway = new Flyway(config);

        flyway.migrate();

        promise.complete();
    }

}
