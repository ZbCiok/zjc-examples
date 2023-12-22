package zjc.examples.vertx.springBoot.config;

import zjc.examples.vertx.springBoot.verticle.StudentVerticle;
import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class MainConfig {

    @Autowired
    StudentVerticle studentVerticle;

    @Autowired
    zjc.examples.vertx.springBoot.verticle.MQHandleVerticle MQHandleVerticle;

    @PostConstruct
    public void deployVerticle() {
        // deploy the verticles
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(studentVerticle);
        vertx.deployVerticle(MQHandleVerticle);
    }
}
