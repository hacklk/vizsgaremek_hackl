import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageClasses.LandingCalendar;
import pageClasses.LoginLogout;

import java.util.concurrent.TimeUnit;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginLogout_Test {
    WebDriver webDriver;
    LandingCalendar landingCalendar;
    LoginLogout loginLogout;


    @BeforeEach
    public void setDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        webDriver = new ChromeDriver(options);

        webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }

    @AfterEach
    public void close(){
        webDriver.quit();
    }


    @Test
    @Order(1)
    @DisplayName("LOG-01 LOGIN attempt withouth mail address")
    public void testLoginNoEmail(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.loginIconClick();
        loginLogout = new LoginLogout(webDriver);
        loginLogout.userLoginNoEmail(FinalConstants.VALID_PASSWORD);

        Assertions.assertTrue(webDriver.findElement(By.id("mat-error-2")).isDisplayed());
    }

    @Test
    @Order(2)
    @DisplayName("LOG-02 LOGIN attempt without password")
    public void testLoginNoPassword(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.loginIconClick();
        loginLogout = new LoginLogout(webDriver);
        loginLogout.userLoginNoPassword(FinalConstants.EMAIL);

        Assertions.assertFalse(webDriver.findElement(By.xpath("//div/button[1]")).isEnabled());
    }

    @Test
    @Order(3)
    @DisplayName("LOG-03 LOGIN attempt wrong password")
    public void testLoginWrongPassword(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.loginIconClick();
        loginLogout = new LoginLogout(webDriver);
        loginLogout.userLoginWrongPassword(FinalConstants.EMAIL, FinalConstants.USER_WRONG_PASSWORD);

        Assertions.assertTrue(webDriver.findElement(By.xpath("//notifier-container/ul/li/notifier-notification/p")).isDisplayed());
    }

    @Test
    @Order(4)
    @DisplayName("LOG-04 LOGIN")
    public void testLogin(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.loginIconClick();
        loginLogout = new LoginLogout(webDriver);
        loginLogout.userLogin(FinalConstants.EMAIL, FinalConstants.VALID_PASSWORD);

        Assertions.assertEquals("Hello, " + FinalConstants.FIRSTNAME, webDriver.findElement(By.xpath("//mat-toolbar-row[1]/div/div/span")).getText());
    }

//    LOGOUT
    @Test
    @Order(5)
    @DisplayName("LOG-04 LOGOUT")
    public void testLogout() {
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.loginIconClick();
        loginLogout = new LoginLogout(webDriver);
        loginLogout.userLogin(FinalConstants.EMAIL, FinalConstants.VALID_PASSWORD);
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.logout();

        Assertions.assertEquals("BELÉPÉS", webDriver.findElement(By.cssSelector("mat-toolbar-row > div > div > span")).getText());
    }

}
