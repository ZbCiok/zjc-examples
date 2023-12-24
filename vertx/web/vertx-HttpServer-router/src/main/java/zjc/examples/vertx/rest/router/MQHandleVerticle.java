package zjc.examples.vertx.rest.router;

import io.vertx.core.AbstractVerticle;


public class MQHandleVerticle extends AbstractVerticle {

    @Override
    public void start() {
        vertx.eventBus().consumer("mqHandlerOnAPICompletion", msg -> {
            System.out.println("placing message in MQ");

            msg.reply("Success");
        });
    }
}