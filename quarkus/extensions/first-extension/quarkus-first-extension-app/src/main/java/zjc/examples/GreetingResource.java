package zjc.examples;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import zjc.examples.quarkus.first.extension.runtime.ExampleService;

@Path("/hello")
public class GreetingResource {

    @Inject
    ExampleService exampleService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return exampleService.helloExt();
    }
}
