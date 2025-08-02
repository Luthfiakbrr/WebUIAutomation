package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.LoginPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.time.Duration;



import static org.junit.Assert.assertTrue;

public class LoginSteps {
    WebDriver driver;
    LoginPage loginPage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // Gunakan mode headless Chromium terbaru
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        loginPage = new LoginPage(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("User is on the login page")
    public void userIsOnLoginPage() {
        driver.get("https://the-internet.herokuapp.com/login");
    }

    @When("User enters valid username and password")
    public void userEntersValidCredentials() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginPage.enterUsername("tomsmith");
        loginPage.enterPassword("SuperSecretPassword!");
    }

    @And("User clicks login button")
    public void userClicksLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("User should be logged in")
    public void userShouldBeLoggedIn() {
        Assert.assertTrue(loginPage.isLoginSuccess());
    }

    @Then("I should be redirected to the dashboard")
    public void i_should_be_redirected_to_the_dashboard() {
        assertTrue(driver.getCurrentUrl().contains("/dashboard"));
    }

    @Then("I should see an error message")
    public void i_should_see_error_message() {
        assertTrue(driver.getPageSource().contains("Invalid username or password"));
    }

    @Then("I should see a validation warning")
    public void i_should_see_validation_warning() {
        assertTrue(driver.getPageSource().contains("Please fill out this field"));
    }
}
