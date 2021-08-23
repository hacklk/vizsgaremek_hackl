import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageClasses.LandingCalendar;
import pageClasses.LoginLogout;
import pageClasses.Account;

import java.util.concurrent.TimeUnit;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Account_Test {

    WebDriver webDriver;
    LandingCalendar landingCalendar;
    LoginLogout loginLogout;
    Account account;


    @BeforeEach
    public void setDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        webDriver = new ChromeDriver(options);

        webDriver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }

    @AfterEach
    public void close(){
        webDriver.quit();
    }

    @Test
    @Order(1)
    @DisplayName("ACT-01 SUBMIT user details")
    public void testLoadAccountDetails(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.clickHamburgerButton();
        landingCalendar.clickLoginPageButton();
        loginLogout = new LoginLogout(webDriver);
        loginLogout.userLogin(FinalConstants.EMAIL, FinalConstants.VALID_PASSWORD);
        landingCalendar.clickProfileButton();
        account = new Account(webDriver);
        account.uploadPersonalDetails(FinalConstants.FIRSTNAME, FinalConstants.SURNAME, FinalConstants.NICKNAME);

        Assertions.assertEquals("Hello, " + FinalConstants.FIRSTNAME, webDriver.findElement(By.xpath("//*/mat-toolbar-row[1]/div/div/span")).getText());

    }

    @Test
    @Order(2)
    @DisplayName("ACT-02 CHANGE PASSWORD attempt, wrong password format")
    public void testWrongPassword(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.clickHamburgerButton();
        landingCalendar.clickLoginPageButton();
        loginLogout = new LoginLogout(webDriver);
        loginLogout.userLogin(FinalConstants.EMAIL, FinalConstants.VALID_PASSWORD);
        landingCalendar.clickProfileButton();
        account = new Account(webDriver);
        account.changePassword(FinalConstants.NOT_VALID_PASSWORD, FinalConstants.NEW_PASSWORD, FinalConstants.NEW_PASSWORD);

        Assertions.assertEquals("assertive", webDriver.findElement(By.xpath("//*[contains(@class,'cdk-live-announcer-element cdk-visually-hidden')]")).getAttribute("aria-live"));
    }

    @Test
    @Order(3)
    @DisplayName("ACT-03 CHANGE PASSWORD with wrong verification")
    public void testWrongConfirmPassword(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.clickHamburgerButton();
        landingCalendar.clickLoginPageButton();
        loginLogout = new LoginLogout(webDriver);
        loginLogout.userLogin(FinalConstants.EMAIL, FinalConstants.VALID_PASSWORD);
        landingCalendar.clickProfileButton();
        account = new Account(webDriver);
        account.changePassword(FinalConstants.VALID_PASSWORD, FinalConstants.NEW_PASSWORD, FinalConstants.NOT_CORRECT_PASSWORD);

        Assertions.assertEquals("polite", webDriver.findElement(By.xpath("//*[contains(@class,'cdk-live-announcer-element cdk-visually-hidden')]")).getAttribute("aria-live"));
    }

    @Test
    @Order(4)
    @DisplayName("ACT-04 CHANGE PASSWORD - with correct data")
    public void testChangePassword(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.clickHamburgerButton();
        landingCalendar.clickLoginPageButton();
        loginLogout = new LoginLogout(webDriver);
        loginLogout.userLogin(FinalConstants.EMAIL, FinalConstants.VALID_PASSWORD);
        landingCalendar.clickProfileButton();
        account = new Account(webDriver);
        account.changePassword(FinalConstants.VALID_PASSWORD, FinalConstants.NEW_PASSWORD, FinalConstants.NEW_PASSWORD);

        Assertions.assertEquals("true", webDriver.findElement(By.xpath("//*[contains(@class,'cdk-live-announcer-element cdk-visually-hidden')]")).getAttribute("aria-atomic"));
    }

    @Test
    @Order(5)
    @DisplayName("ACT-05 SET BILLING DETAILS")
    public void testUploadAccountBillingDetails(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.clickHamburgerButton();
        landingCalendar.clickLoginPageButton();
        loginLogout = new LoginLogout(webDriver);
        loginLogout.userLogin(FinalConstants.EMAIL, FinalConstants.NEW_PASSWORD);
        landingCalendar.clickProfileButton();
        account = new Account(webDriver);
        account.uploadBillingDetails(FinalConstants.FULLNAME, FinalConstants.COUNTRY, FinalConstants.PO_CODE, FinalConstants.CITY, FinalConstants.ADDRESS);

        Assertions.assertTrue(webDriver.findElement(By.xpath("//*[contains(@class,'mat-simple-snackbar ng-star-inserted')]")).isDisplayed());

    }

    @Test
    @Order(6)
    @DisplayName("ACT-06 Restore LOGIN PASSWORD")
    public void testRestorePassword(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.clickHamburgerButton();
        landingCalendar.clickLoginPageButton();
        loginLogout = new LoginLogout(webDriver);
        loginLogout.userLogin(FinalConstants.EMAIL, FinalConstants.NEW_PASSWORD);
        landingCalendar.clickProfileButton();
        account = new Account(webDriver);
        account.changePassword(FinalConstants.NEW_PASSWORD, FinalConstants.USER_PASSWORD, FinalConstants.USER_PASSWORD);

        Assertions.assertEquals("true", webDriver.findElement(By.xpath("//*[contains(@class,'cdk-live-announcer-element cdk-visually-hidden')]")).getAttribute("aria-atomic"));
    }

    @Test
    @Order(7)
    @DisplayName("ACT-07 Alter BILLING ADDRESS")
    public void testModifyBillingDetails(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.clickHamburgerButton();
        landingCalendar.clickLoginPageButton();
        loginLogout = new LoginLogout(webDriver);
        loginLogout.userLogin(FinalConstants.EMAIL, FinalConstants.VALID_PASSWORD);
        landingCalendar.clickProfileButton();
        account = new Account(webDriver);
        account.uploadBillingDetails(FinalConstants.FULLNAME, FinalConstants.COUNTRY, FinalConstants.NEW_POSTAL_CODE, FinalConstants.NEW_CITY, FinalConstants.NEW_ADDRESS);

        Assertions.assertTrue(webDriver.findElement(By.xpath("//*[contains(@class,'mat-simple-snackbar ng-star-inserted')]")).isDisplayed());

    }

    @Test
    @Order(8)
    @DisplayName("ACT-08 Delete all BILLING details, but the name")
    public void testDeleteBillingDetails(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.clickHamburgerButton();
        landingCalendar.clickLoginPageButton();
        loginLogout = new LoginLogout(webDriver);
        loginLogout.userLogin(FinalConstants.EMAIL, FinalConstants.VALID_PASSWORD);
        landingCalendar.clickProfileButton();
        account = new Account(webDriver);
        account.deleteBillingDetails(FinalConstants.FULLNAME);

        Assertions.assertTrue(webDriver.findElement(By.xpath("//*[contains(@class,'mat-simple-snackbar ng-star-inserted')]")).isDisplayed());
    }
}
