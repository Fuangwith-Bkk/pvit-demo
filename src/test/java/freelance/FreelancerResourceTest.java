package freelance;

import io.quarkus.test.junit.QuarkusTest;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import javax.ws.rs.core.MediaType;


import com.example.freelancer.model.Freelancer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@QuarkusTest
public class FreelancerResourceTest {
  private static final Logger logger = Logger.getLogger(FreelancerResourceTest.class);
  @Test
  public void testFindAll() {
    logger.info("Test GET all Ids");
    given()
      .when().get("/freelancers")
      .then()
      .statusCode(200);

  }

  @Test
  public void testFindById() {
    logger.info("Test GET id: 123456");
    given()
      .when().get("/freelancers/123456")
      .then()
      .statusCode(200);

  }

  @Test
  public void testDeleteById() {
    logger.info("Test DELETE id: 123456");
    given()
      .when().delete("/freelancers/123456")
      .then()
      .statusCode(204);
    logger.info("Check that 123456 is deleted");
    given()
      .when().get("/freelancers/123456")
      .then()
      .statusCode(404);

  }

  @Test
  public void createFreelancer() throws JsonProcessingException {
    Freelancer freelancer = new Freelancer();
    freelancer.setId(999999L);
    freelancer.setFirstName("Joe");
    freelancer.setLastName("Elliott");
    freelancer.setEmail("jelliott@defleppard.com");
    ObjectMapper objectMapper = new ObjectMapper();
    String freelancerAsString = objectMapper.writeValueAsString(freelancer);
    logger.info("Test POST with: "+freelancerAsString);
    given()
      .contentType(MediaType.APPLICATION_JSON)
      .body(freelancerAsString)
      .post("/freelancers")
      .then()
      .statusCode(201);
    given()
      .get("/freelancers/999999")
      .then()
      .statusCode(200);
  }
  @Test
  public void updateFreelancer() throws JsonProcessingException {
    Freelancer freelancer = new Freelancer();
    freelancer.setId(888888L);
    freelancer.setFirstName("Joe");
    freelancer.setLastName("Elliott");
    freelancer.setEmail("jelliott@defleppard.com");
    ObjectMapper objectMapper = new ObjectMapper();
    String freelancerAsString = objectMapper.writeValueAsString(freelancer);
    logger.info("Test POST with: "+freelancerAsString);
    given()
      .contentType(MediaType.APPLICATION_JSON)
      .body(freelancerAsString)
      .post("/freelancers")
      .then()
      .statusCode(201);

    given()
      .get("/freelancers/888888")
      .then()
      .assertThat()
      .statusCode(200)
      .body("email", equalTo(freelancer.getEmail()))
      .body("lastName", equalTo(freelancer.getLastName()))
      .body("firstName",equalTo(freelancer.getFirstName()));
      
      // .body("firstName",equalTo(freelancer.getFirstName())
      // .body("lastName",equalTo(freelancer.getLastName());
  }
  @Test
  public void updateExistingFreelancer() throws JsonProcessingException{
    Freelancer freelancer = new Freelancer();
    String newEmail = "jteller@rider.com";
    freelancer.setId(123456L);
    freelancer.setEmail(newEmail);
    ObjectMapper objectMapper = new ObjectMapper();
    String freelancerAsString = objectMapper.writeValueAsString(freelancer);
    
    logger.info("Test PUT for existing freelancer with: "+freelancerAsString);
    given()
      .contentType(MediaType.APPLICATION_JSON)
      .body(freelancerAsString)
      .put("/freelancers/123456")
      .then()
      .statusCode(204);

     given()
      .get("/freelancers/123456")
      .then()
      .assertThat()
      .statusCode(200)
      .body("email", equalTo("jteller@rider.com")); 
     
    
  }


}