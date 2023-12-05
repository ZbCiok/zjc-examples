package zjc.examples.vertx.eventBus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Launcher;
import io.vertx.core.eventbus.EventBus;
public class Sender extends AbstractVerticle {

  public static void main(String[] args) {
    Launcher.executeCommand("run", Sender.class.getName(), "-cluster");
  }

  @Override
  public void start() throws Exception {

    EventBus eb = vertx.eventBus();

    // Send a message every second
    vertx.setPeriodic(1000, v -> eb.publish("news-feed", "Some news!"));
  }
}