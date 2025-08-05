package stepdefinitions;

import hooks.Hooks;
import io.cucumber.java.en.*;
import io.qameta.allure.*;
import org.openqa.selenium.By;

import static org.junit.Assert.assertTrue;

@Epic("UI Automation")
@Feature("Login Feature")
public class LoginSteps {

    @Given("User is on the login page")
    public void userOnLoginPage() {
        Hooks.driver.get("https://www.saucedemo.com/");
    }

    @When("User enters username {string} and password {string}")
    public void userEntersCredentials(String username, String password) {
        Hooks.driver.findElement(By.id("user-name")).sendKeys(username);
        Hooks.driver.findElement(By.id("password")).sendKeys(password);
        Hooks.driver.findElement(By.id("login-button")).click();
    }

    @Then("User should see inventory page")
    public void userShouldSeeInventoryPage() {
        assertTrue(Hooks.driver.findElement(By.id("inventory_container")).isDisplayed());
    }
}
