import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageClasses.Landing;
import pageClasses.LoginLogout;
//import pageClasses.Calendar;

import java.util.concurrent.TimeUnit;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginLogout_Test {
    WebDriver webDriver;
    Landing landing;
    LoginLogout loginLogout;

    @BeforeEach
    public void SetDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        webDriver = new ChromeDriver(options);

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown(){
        webDriver.quit();
    }


    @Test
    @Order(1)
    @DisplayName("LI-01 Bejelentkezés felhasználóként email cím megadása nélkül")
    public void TestUserLoginNoEmail(){
        landing = new Landing(webDriver);
//        landing.navigateToURL(Constraints.URL);
        landing.clickHamburgerButton();
        landing.clickLoginPageButton();
        loginLogout = new LoginLogout(webDriver);
//        loginLogout.userLoginNoEmail(Constraints.PASSWORD);

        Assertions.assertTrue(webDriver.findElement(By.id("mat-error-2")).isDisplayed());
    }

    @Test
    @Order(2)
    @DisplayName("LI-02 Bejelentkezés felhasználóként jelszó megadása nélkül")
    public void TestUserLoginNoPassword(){
        landing = new Landing(webDriver);
//        landing.navigateToURL(Constraints.URL);
        landing.clickHamburgerButton();
        landing.clickLoginPageButton();
        loginLogout = new LoginLogout(webDriver);
//        loginLogout.userLoginNoPassword(Constraints.EMAIL);

        Assertions.assertFalse(webDriver.findElement(By.xpath("//div/button[1]")).isEnabled());
    }

    @Test
    @Order(3)
    @DisplayName("LI-03 Bejelentkezés felhasználóként rossz jelszó megadásával")
    public void TestUserLoginWrongPassword(){
        landing = new Landing(webDriver);
//        landing.navigateToURL(Constraints.URL);
        landing.clickHamburgerButton();
        landing.clickLoginPageButton();
        loginLogout = new LoginLogout(webDriver);
//        loginLogout.userLoginWrongPassword(Constraints.EMAIL, Constraints.USER_WRONG_PASSWORD);

        Assertions.assertTrue(webDriver.findElement(By.xpath("//notifier-container/ul/li/notifier-notification/p")).isDisplayed());
    }

    @Test
    @Order(4)
    @DisplayName("LI-04 Bejelentkezés felhasználóként érvényes adatokkal")
    public void TestUserLogin(){
        landing = new Landing(webDriver);
//        landing.navigateToURL(Constraints.URL);
        landing.clickHamburgerButton();
        landing.clickLoginPageButton();
        loginLogout = new LoginLogout(webDriver);
//        loginLogout.userLogin(Constraints.EMAIL, Constraints.PASSWORD);

//        Assertions.assertEquals("Hello, " + Constraints.USER_FIRSTNAME, webDriver.findElement(By.xpath("//mat-toolbar-row[1]/div/div/span")).getText());
    }
}
