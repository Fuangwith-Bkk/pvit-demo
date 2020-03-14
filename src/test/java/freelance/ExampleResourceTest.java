package freelance;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;


@QuarkusTest
public class ExampleResourceTest {

    @Test
    public void testFindAll() {
        given()
          .when().get("/freelancers")
          .then()
             .statusCode(200);
           
    }
    
    @Test
    public void testFindById() {
        given()
          .when().get("/freelancers/123456")
          .then()
             .statusCode(200);
           
    }

    @Test
    public void testDeleteById() {
        given()
          .when().delete("/freelancers/123456")
          .then()
             .statusCode(204);
           
    }

}