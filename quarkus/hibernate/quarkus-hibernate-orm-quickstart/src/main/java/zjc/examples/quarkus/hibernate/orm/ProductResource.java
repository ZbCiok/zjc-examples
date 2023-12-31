package zjc.examples.quarkus.hibernate.orm;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Path("products")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class ProductResource {

    private static final Logger LOGGER = Logger.getLogger(ProductResource.class.getName());

    @Inject
    EntityManager entityManager;

    @GET
    public List<Product> get() {
        return entityManager.createNamedQuery("Product.findAll", Product.class)
                .getResultList();
    }

    @GET
    @Path("{id}")
    public Product getSingle(Integer id) {
        Product entity = entityManager.find(Product.class, id);
        if (entity == null) {
            throw new WebApplicationException("Product with id of " + id + " does not exist.", 404);
        }
        return entity;
    }

    @POST
    @Transactional
    public Response create(Product product) {
        if (product.getId() != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }

        entityManager.persist(product);
        return Response.ok(product).status(201).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Product update(Integer id, Product product) {
        if (product.getName() == null) {
            throw new WebApplicationException("Product Name was not set on request.", 422);
        }

        Product entity = entityManager.find(Product.class, id);

        if (entity == null) {
            throw new WebApplicationException("Product with id of " + id + " does not exist.", 404);
        }

        entity.setName(product.getName());

        return entity;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(Integer id) {
        Product entity = entityManager.getReference(Product.class, id);
        if (entity == null) {
            throw new WebApplicationException("Product with id of " + id + " does not exist.", 404);
        }
        entityManager.remove(entity);
        return Response.status(204).build();
    }

    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {

        @Inject
        ObjectMapper objectMapper;

        @Override
        public Response toResponse(Exception exception) {
            LOGGER.error("Failed to handle request", exception);

            int code = 500;
            if (exception instanceof WebApplicationException) {
                code = ((WebApplicationException) exception).getResponse().getStatus();
            }

            ObjectNode exceptionJson = objectMapper.createObjectNode();
            exceptionJson.put("exceptionType", exception.getClass().getName());
            exceptionJson.put("code", code);

            if (exception.getMessage() != null) {
                exceptionJson.put("error", exception.getMessage());
            }

            return Response.status(code)
                    .entity(exceptionJson)
                    .build();
        }

    }
}
