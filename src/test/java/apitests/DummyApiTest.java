package apitests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DummyApiTest {

    @BeforeAll
    public static void setup() {
        // Set base URI
        RestAssured.baseURI = "https://gorest.co.in/public/v2";

        // Allow insecure HTTPS (untuk self-signed certificates, meskipun ini API publik)
        RestAssured.useRelaxedHTTPSValidation();

        // Set timeout konfigurasi HTTP client
        RestAssured.config = RestAssuredConfig.config().httpClient(
                HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", 10000)
                        .setParam("http.socket.timeout", 10000)
                        .setParam("http.connection-manager.timeout", 10000)
        );
    }

    @Epic("User Management")
    @Feature("GET Users")
    @Story("Get All Users")
    @Test
    public void getUsersShouldReturn200() {
        given()
                .when()
                .get("/users")
                .then()
                .statusCode(200);
    }

    @Test
    public void createUserShouldReturn201() {
        // Ganti token dengan yang valid
        String token = "Bearer 3b2fa8fdefec96fcea066b88a3c5a43758de3fcb627855744d4cdf916d18f3bb";

        // Email unik supaya tidak konflik
        String uniqueEmail = "luthfi" + System.currentTimeMillis() + "@example.com";

        String jsonBody = """
        {
          "name": "Luthfi",
          "gender": "male",
          "email": "%s",
          "status": "active"
        }
        """.formatted(uniqueEmail);

        Response response = given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .post("/users");

        response.then().log().all();
        response.then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("name", notNullValue())
                .body("email", notNullValue());
    }

    @Test
    public void getUsersShouldReturnList() {
        Response response = given()
                .when()
                .get("/users");

        response.then().statusCode(200);
        assertNotNull(response.jsonPath().getList(""), "List of users should not be null");
    }
}
