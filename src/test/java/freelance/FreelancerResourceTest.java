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
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status;

// import io.quarkus.test.common.QuarkusTestResource;
// import io.quarkus.test.h2.H2DatabaseTestResource;



@QuarkusTest
public class FreelancerResourceTest {
  private static final Logger logger = Logger.getLogger(FreelancerResourceTest.class);
  // @Test
  // public void testFindAll() {
  //   logger.info("Test GET all Ids");
  //   given()
  //     .when().get("/freelancers")
  //     .then()
  //     .statusCode(Status.OK.getStatusCode());

  // }

  // @Test
  // public void testFindById() {
  //   logger.info("Test GET id: 123456");
  //   given()
  //     .when().get("/freelancers/123456")
  //     .then()
  //     .statusCode(Status.OK.getStatusCode());

  // }

  // @Test
  // public void testDeleteById() {
  //   logger.info("Test DELETE id: 123456");
  //   given()
  //     .when().delete("/freelancers/123456")
  //     .then()
  //     .statusCode(Status.NO_CONTENT.getStatusCode());
  //   logger.info("Check that 123456 is deleted");
  //   given()
  //     .when().get("/freelancers/123456")
  //     .then()
  //     .statusCode(Status.NOT_FOUND.getStatusCode());

  // }

  // @Test
  // public void createFreelancer() throws JsonProcessingException {
  //   Freelancer freelancer = new Freelancer();
  //   freelancer.setId(999999L);
  //   freelancer.setFirstName("Joe");
  //   freelancer.setLastName("Elliott");
  //   freelancer.setEmail("jelliott@defleppard.com");
  //   ObjectMapper objectMapper = new ObjectMapper();
  //   String freelancerAsString = objectMapper.writeValueAsString(freelancer);
  //   logger.info("Test POST with: "+freelancerAsString);
  //   given()
  //     .contentType(MediaType.APPLICATION_JSON)
  //     .body(freelancerAsString)
  //     .post("/freelancers")
  //     .then()
  //     .statusCode(Status.CREATED.getStatusCode());
  //   given()
  //     .get("/freelancers/999999")
  //     .then()
  //     .statusCode(Status.OK.getStatusCode());
  // }
  // @Test
  // public void updateFreelancer() throws JsonProcessingException {
  //   Freelancer freelancer = new Freelancer();
  //   freelancer.setId(888888L);
  //   freelancer.setFirstName("Joe");
  //   freelancer.setLastName("Elliott");
  //   freelancer.setEmail("jelliott@defleppard.com");
  //   ObjectMapper objectMapper = new ObjectMapper();
  //   String freelancerAsString = objectMapper.writeValueAsString(freelancer);
  //   logger.info("Test POST with: "+freelancerAsString);
  //   given()
  //     .contentType(MediaType.APPLICATION_JSON)
  //     .body(freelancerAsString)
  //     .post("/freelancers")
  //     .then()
  //     .statusCode(Status.CREATED.getStatusCode());

  //   given()
  //     .get("/freelancers/888888")
  //     .then()
  //     .assertThat()
  //     .statusCode(Status.OK.getStatusCode())
  //     .body("email", equalTo(freelancer.getEmail()))
  //     .body("lastName", equalTo(freelancer.getLastName()))
  //     .body("firstName",equalTo(freelancer.getFirstName()));
  // }
  // @Test
  // public void updateExistingFreelancer() throws JsonProcessingException{
  //   Freelancer freelancer = new Freelancer();
  //   String newEmail = "jteller@rider.com";
  //   freelancer.setId(123456L);
  //   freelancer.setEmail(newEmail);
  //   ObjectMapper objectMapper = new ObjectMapper();
  //   String freelancerAsString = objectMapper.writeValueAsString(freelancer);
    
  //   logger.info("Test PUT for existing freelancer with: "+freelancerAsString);
  //   given()
  //     .contentType(MediaType.APPLICATION_JSON)
  //     .body(freelancerAsString)
  //     .put("/freelancers/123456")
  //     .then()
  //     .statusCode(Status.NO_CONTENT.getStatusCode());

  //    given()
  //     .get("/freelancers/123456")
  //     .then()
  //     .assertThat()
  //     .statusCode(Status.OK.getStatusCode())
  //     .body("email", equalTo("jteller@rider.com")); 
     
    
  // }
  @Test
void shouldPingLiveness() {
    given()
        .when().get("/health/live")
        .then()
        .statusCode(Status.OK.getStatusCode());
}

// @Test
// void shouldPingReadiness() {
//     given()
//         .when().get("/health/ready")
//         .then()
//         .statusCode(Status.OK.getStatusCode());
//}
// @Test
// void shouldPingHealth() {
//   given()
//       .when().get("/health")
//       .then()
//       .statusCode(Status.OK.getStatusCode());
// }
@Test
void shouldPingMetrics() {
    given()
        .header(ACCEPT, APPLICATION_JSON)
        .when().get("/metrics/application")
        .then()
        .statusCode(Status.OK.getStatusCode());
}

}