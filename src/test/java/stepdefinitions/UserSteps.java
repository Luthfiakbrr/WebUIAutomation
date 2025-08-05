package stepdefinitions;

import io.cucumber.java.en.*;
import io.qameta.allure.*;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@Epic("API Automation")
@Feature("User API Feature")
public class UserSteps {

    Response response;

    @Step("Set GET endpoint for all users")
    @Given("I set GET endpoint for all users")
    public void setGETEndpointForAllUsers() {
        baseURI = "https://gorest.co.in/public/v2";
    }

    @Step("Send GET request to /users")
    @When("I send GET HTTP request")
    public void sendGETHTTPRequest() {
        response = given()
                .when()
                .get("/users");
    }

    @Step("Validate HTTP status code is 200")
    @Then("I receive valid HTTP response code 200")
    public void validateHTTPResponseCode200() {
        response.then().statusCode(200);
    }

    @Step("Response should contain non-empty list of users")
    @And("Response body should contain list of users")
    public void validateResponseBodyNotEmpty() {
        response.then().body("$", not(empty()));
    }
}
