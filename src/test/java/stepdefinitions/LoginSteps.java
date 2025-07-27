package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.LoginPage;


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

    @Given("I open the login page")
    public void openLoginPage() {
        driver.get("https://example.com/login");
    }

    @When("I enter valid username and password")
    public void enterValidCredentials() {
        loginPage.enterUsername("validUser");
        loginPage.enterPassword("validPass");
    }

    @When("I enter invalid username and password")
    public void enterInvalidCredentials() {
        loginPage.enterUsername("wrongUser");
        loginPage.enterPassword("wrongPass");
    }

    @When("I leave username and password empty")
    public void leaveFieldsEmpty() {
        loginPage.enterUsername("");
        loginPage.enterPassword("");
    }

    @And("I click the login button")
    public void clickLoginButton() {
        loginPage.clickLogin();
    }

    @Then("I should be redirected to the dashboard")
    public void redirectedToDashboard() {
        assertTrue(driver.getCurrentUrl().contains("/dashboard"));
    }

    @Then("I should see an error message")
    public void seeErrorMessage() {
        assertTrue(driver.getPageSource().contains("Invalid username or password"));
    }

    @Then("I should see a validation warning")
    public void seeValidationWarning() {
        assertTrue(driver.getPageSource().contains("Please fill out this field"));
    }
}
