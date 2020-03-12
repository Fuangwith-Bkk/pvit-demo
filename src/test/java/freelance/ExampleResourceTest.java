package freelance;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

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

}