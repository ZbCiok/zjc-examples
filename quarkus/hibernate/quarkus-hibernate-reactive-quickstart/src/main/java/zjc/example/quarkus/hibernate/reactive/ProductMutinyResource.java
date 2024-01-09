package zjc.example.quarkus.hibernate.reactive;

import static jakarta.ws.rs.core.Response.Status.CREATED;
import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;
import static jakarta.ws.rs.core.Response.Status.NO_CONTENT;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import io.smallrye.mutiny.Uni;
import org.hibernate.reactive.mutiny.Mutiny.SessionFactory;

@Path("products")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class ProductMutinyResource {

    @Inject
    SessionFactory sf;

    @GET
    public Uni<List<Product>> get() {
        return sf.withTransaction((s,t) -> s
                .createNamedQuery("Product.findAll", Product.class)
                .getResultList()
        );
    }

    @GET
    @Path("{id}")
    public Uni<Product> getSingle(Integer id) {
        return sf.withTransaction((s,t) -> s.find(Product.class, id));
    }

    @POST
    public Uni<Response> create(Product product) {
        if (product == null || product.getId() != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }

        return sf.withTransaction((s,t) -> s.persist(product))
                .replaceWith(Response.ok(product).status(CREATED)::build);
    }

    @PUT
    @Path("{id}")
    public Uni<Response> update(Integer id, Product product) {
        if (product == null || product.getName() == null) {
            throw new WebApplicationException("Product name was not set on request.", 422);
        }

        return sf.withTransaction((s,t) -> s.find(Product.class, id)
                .onItem().ifNull().failWith(new WebApplicationException("Product missing from database.", NOT_FOUND))
                // If entity exists then update it
                .invoke(entity -> entity.setName(product.getName())))
                .map(entity -> Response.ok(entity).build());
    }

    @DELETE
    @Path("{id}")
    public Uni<Response> delete(Integer id) {
        return sf.withTransaction((s,t) -> s.find(Product.class, id)
                .onItem().ifNull().failWith(new WebApplicationException("Product missing from database.", NOT_FOUND))
                // If entity exists then delete it
                .call(s::remove))
                .replaceWith(Response.ok().status(NO_CONTENT)::build);    }

}
