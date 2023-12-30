package zjc.examples;

import io.quarkus.runtime.annotations.QuarkusMain;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.Quarkus;

@QuarkusMain  // This annotation tells Quarkus to use this as the main method,
              // unless it is overridden in the config
public class Main {

    /**
     * It is not recommenced to do any business logic in this main method,
     * as Quarkus has not been set up yet, and Quarkus may run in a different ClassLoader.
     * If you want to perform logic on startup use an
     * io.quarkus.runtime.QuarkusApplication as see below:
     * public static class MyApp implements QuarkusApplication
     */
    public static void main(String ... args) {
        System.out.println("Running main method");
        Quarkus.run(args);   // This launches Quarkus
    }

    public static class MyApp implements QuarkusApplication {
        @Override
        public int run(String... args) throws Exception {
            System.out.println("Do startup logic here");
            Quarkus.waitForExit();
            return 0;
        }
    }
}