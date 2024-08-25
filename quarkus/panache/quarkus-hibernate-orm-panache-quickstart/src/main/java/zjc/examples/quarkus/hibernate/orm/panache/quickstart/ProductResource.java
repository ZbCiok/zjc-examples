package zjc.examples.quarkus.hibernate.orm.panache.quickstart;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import io.quarkus.panache.common.Sort;

@Path("crud/products")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")

// This is a classic REST Endpoint
public class ProductResource {

    @Inject
    ProductRepository repository;

    @GET
    @Produces("application/json")
    public List<Product> get() {
        return repository.listAll(Sort.by("name"));
    }

    @GET
    @Path("{id}")
    public Response getProductById(@PathParam("id") Long id) {
        return repository
                .findByIdOptional(id)
                .map(d -> Response.ok(d).build())
                .orElse(Response.status(204).build());
    }

    @POST
    @Transactional
    public Response create(Product product) {
        if (product.id != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }

        repository.persist(product);
        return Response.ok(product).status(201).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, Product product) {

        return repository
                .findByIdOptional(id)
                .map(
                        t -> {
                            t.name = product.name;
                            return Response.status(204).build();
                        })
                .orElse(Response.status(Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        repository.delete("id", id);
        return Response.status(204).build();
    }

}