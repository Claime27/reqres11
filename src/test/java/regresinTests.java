import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class RegresinTests {

    @Test
    void singleUserTest() {
        given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("data.id" ,is(2));
    }

    @Test
    void singleUserNotFound() {
        given()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .statusCode(404);

    }

    
    @Test
    void createUser() {
        String createUser = "{\n" +
            "    \"name\": \"morpheus\",\n" +
            "    \"job\": \"leader\"\n" +
            "}";
        given()
                .body(createUser)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name" ,is("morpheus"))
                .body("job" ,is("leader"));

    }

   
    @Test
    void updateRequest() {
         String updateRequest = "{\n" +
            "    \"name\": \"morpheus\",\n" +
            "    \"job\": \"zion resident\"\n" +
            "}";
        given()
                .body(updateRequest)
                .contentType(JSON)
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"));
    }

   
    @Test
    void unsuccesfulRegister() {
        String unsuccesfulRegister = "{\n" +
            "    \"email\": \"sydney@fife\"\n" +
            "}";
        given()
                .body(unsuccesfulRegister)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }
}
