package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.LoginPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
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

    @Given("I open the login page")
    public void openLoginPage() {
        System.out.println("Step: Open login page");
        driver.get("https://example.com/login");

        // Tambahkan kode ini untuk menunggu sampai elemen login muncul
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
    }

    @When("I enter valid username and password")
    public void enterValidCredentials() {
        System.out.println("Step: Enter valid credentials");
        loginPage.enterUsername("validUser");
        loginPage.enterPassword("validPass");
    }

    @When("I enter invalid username and password")
    public void enterInvalidCredentials() {
        System.out.println("Step: Enter invalid credentials");
        loginPage.enterUsername("wrongUser");
        loginPage.enterPassword("wrongPass");
    }

    @When("I leave username and password empty")
    public void leaveFieldsEmpty() {
        System.out.println("Step: Leave username and password empty");
        loginPage.enterUsername("");
        loginPage.enterPassword("");
    }

    @And("I click the login button")
    public void clickLoginButton() {
        System.out.println("Step: Click login button");
        loginPage.clickLogin();
    }

    @Then("I should be redirected to the dashboard")
    public void redirectedToDashboard() {
        System.out.println("Step: Check if redirected to dashboard");
        assertTrue(driver.getCurrentUrl().contains("/dashboard"));
    }

    @Then("I should see an error message")
    public void seeErrorMessage() {
        System.out.println("Step: Check error message");
        assertTrue(driver.getPageSource().contains("Invalid username or password"));
    }

    @Then("I should see a validation warning")
    public void seeValidationWarning() {
        System.out.println("Step: Check validation warning");
        assertTrue(driver.getPageSource().contains("Please fill out this field"));
    }
}
