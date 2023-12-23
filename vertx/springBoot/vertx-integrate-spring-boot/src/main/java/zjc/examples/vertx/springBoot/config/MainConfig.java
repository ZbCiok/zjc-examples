package zjc.examples.vertx.springBoot.config;

import zjc.examples.vertx.springBoot.verticle.ProductVerticle;
import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class MainConfig {

    @Autowired
    ProductVerticle productVerticle;

    @Autowired
    zjc.examples.vertx.springBoot.verticle.MQHandleVerticle MQHandleVerticle;

    @PostConstruct
    public void deployVerticle() {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(productVerticle);
        vertx.deployVerticle(MQHandleVerticle);
    }
}
