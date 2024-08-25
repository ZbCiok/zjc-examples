package zjc.example.quarkus.hibernate.reactive;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.text.IsEmptyString.emptyString;

@QuarkusTest
public class ProductsEndpointTest {

    @Test
    public void testListAllProducts() {
        //List all, should have all 3 products the database has initially:
		Response response = given()
				.when()
				.get("/products")
				.then()
				.statusCode(200)
				.contentType("application/json")
				.extract().response();
		assertThat(response.jsonPath().getList("name")).containsExactlyInAnyOrder("name#1", "name#2", "name#3");

        // Update name#1 to name#4
        given()
			.when()
				.body("{\"name\" : \"name#4\"}")
				.contentType("application/json")
				.put("/products/1")
			.then()
				.statusCode(200)
				.body(
					containsString("\"id\":"),
					containsString("\"name\":\"name#4\""));

        //List all, name#4 should've replaced name#1:
		response = given()
			.when()
				.get("/products")
			.then()
				.statusCode(200)
				.contentType("application/json")
				.extract().response();
		assertThat(response.jsonPath().getList("name")).containsExactlyInAnyOrder("name#4", "name#2", "name#3");

        //Delete name#4:
        given()
			.when()
				.delete("/products/1")
			.then()
				.statusCode(204);

        //List all, name#4 should be missing now:
        given()
			.when()
				.get("/products")
			.then()
                .statusCode(200)
                .body(
					not(containsString( "name#4")),
					containsString("name#2"),
					containsString("name#3"));

        //Create the name#5:
        given()
			.when()
				.body("{\"name\" : \"name#5\"}")
				.contentType("application/json")
				.post("/products")
			.then()
				.statusCode(201)
				.body(
					containsString("\"id\":"),
					containsString("\"name\":\"name#5\""));

        //List all, name#4 should be still missing now:
        response = given()
			.when()
				.get("/products")
			.then()
				.statusCode(200)
				.extract().response();
		assertThat(response.jsonPath().getList("name")).containsExactlyInAnyOrder("name#5", "name#2", "name#3");
    }

    @Test
    public void testEntityNotFoundForDelete() {
        given()
			.when()
				.delete("/products/9236")
			.then()
				.statusCode(404)
				.body(emptyString());
    }

    @Test
    public void testEntityNotFoundForUpdate() {
        given()
			.when()
				.body("{\"name\" : \"Watermelon\"}")
				.contentType("application/json")
				.put("/products/32432")
			.then()
				.statusCode(404)
				.body(emptyString());
    }

	@Test
	public void testInvalidCreate() {
		given()
			.when()
				.body("{\"name\" : \"Wrong\", \"id\" : \"50\"}")
				.contentType("application/json")
				.post("/products")
			.then()
				.statusCode(422)
				.body(emptyString());
	}

}
