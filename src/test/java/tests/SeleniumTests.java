package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SeleniumTests {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Gestion automatique du WebDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testLogoIsVisible() {
        driver.get("http://localhost:8080/_00_ASBank2023/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logo")));

        assertTrue(logo.isDisplayed(), "Le logo doit être visible");
    }

    @Test
    public void testUserConnect() {
        driver.get("http://localhost:8080/_00_ASBank2023/");

        driver.findElement(By.linkText("Page de Login")).click();
        driver.findElement(By.id("controller_Connect_login_action_userCde")).click();
        driver.findElement(By.id("controller_Connect_login_action_userCde")).sendKeys("client1");
        driver.findElement(By.id("controller_Connect_login_action_userPwd")).click();
        driver.findElement(By.id("controller_Connect_login_action_userPwd")).sendKeys("clientpass1");
        driver.findElement(By.id("controller_Connect_login_action_submit")).click();

        String expectedTitle = "Bienvenue Jane client1 !";
        WebElement actualTitle = driver.findElement(By.id("userConnected"));

        assertEquals(actualTitle.getText(), expectedTitle, "Page title does not match the expected value");


    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
