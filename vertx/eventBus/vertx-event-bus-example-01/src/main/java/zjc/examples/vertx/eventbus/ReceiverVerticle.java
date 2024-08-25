package zjc.examples.vertx.eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

public class ReceiverVerticle extends AbstractVerticle {

  private static final Logger LOGGER = LoggerFactory.getLogger(ReceiverVerticle.class);

  @Override
  public void start() throws Exception {
    vertx.eventBus().consumer("incoming.message", this::onMessage);
  }

  // onMessage() will be called when a message is received.
  private <T> void onMessage(Message<T> tMessage) {
      JsonObject message = (JsonObject) tMessage.body();
      LOGGER.info("Message Received " + message);
      tMessage.reply(message);
  }
}
