package zjc.examples.quarkus.hibernate.orm;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.IsNot.not;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ProductsEndpointTest {

    @Test
    public void testListAllProducts() {
        //List all, should have all 3 products the database has initially:
        given()
                .when().get("/products")
                .then()
                .statusCode(200)
                .body(
                        containsString("name#1"),
                        containsString("name#2"),
                        containsString("name#3"));

        //Delete the name#1:
        given()
                .when().delete("/products/1")
                .then()
                .statusCode(204);

        //List all, name#1 should be missing now:
        given()
                .when().get("/products")
                .then()
                .statusCode(200)
                .body(
                        not(containsString("name#1")),
                        containsString("name#2"),
                        containsString("name#3"));

        //Create the name#4:
        given()
                .when()
                .body("{\"name\" : \"name#4\"}")
                .contentType("application/json")
                .post("/products")
                .then()
                .statusCode(201);

        //List all, name#1 should be missing now:
        given()
                .when().get("/products")
                .then()
                .statusCode(200)
                .body(
                        not(containsString("name#1")),
                        containsString("name#2"),
                        containsString("name#3"),
                        containsString("name#4"));
    }

}
