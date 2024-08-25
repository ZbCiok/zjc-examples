package zjc.examples.quarkus.first.extension.runtime;

import jakarta.enterprise.context.Dependent;

@Dependent
public class ExampleService {
    public String helloExt() {
        return "Hello quarkus-first-extension";
    }
}