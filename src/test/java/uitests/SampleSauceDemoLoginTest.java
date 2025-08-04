package uitests;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import io.qameta.allure.*;

import static org.testng.Assert.assertTrue;

@Epic("UI Automation")
@Feature("Login Feature")
public class SampleSauceDemoLoginTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test(description = "Valid login on SauceDemo")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User can log in successfully")
    public void successfulLoginShouldNavigateToInventoryPage() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        WebElement inventoryContainer = driver.findElement(By.id("inventory_container"));
        assertTrue(inventoryContainer.isDisplayed(), "Inventory page should be visible after login.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
