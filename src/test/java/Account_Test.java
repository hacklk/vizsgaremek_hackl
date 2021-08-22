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
    @DisplayName("PD-01 Regisztrált felhasználó (user) adatainak megadása")
    public void testUploadPersonaDetails(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(Constraints.URL);
        landingCalendar.clickHamburgerButton();
        landingCalendar.clickLoginPageButton();
        loginLogout = new LoginLogout(webDriver);
        loginLogout.userLogin(Constraints.EMAIL, Constraints.PASSWORD);
//        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.profileButtonClick();
        account = new Account(webDriver);
        account.uploadPersonalDetails(Constraints.USER_FIRSTNAME, Constraints.USER_SURNAME, Constraints.USER_NICKNAME);

        Assertions.assertEquals("Hello, " + Constraints.USER_FIRSTNAME, webDriver.findElement(By.xpath("//*/mat-toolbar-row[1]/div/div/span")).getText());

    }

    @Test
    @Order(2)
    @DisplayName("PD-02 Felhasználó jelszavának a módosítása rossz jelszót megadva")
    public void testAddWrongPasswordToChange(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(Constraints.URL);
        landingCalendar.clickHamburgerButton();
        landingCalendar.clickLoginPageButton();
        loginLogout = new LoginLogout(webDriver);
        loginLogout.userLogin(Constraints.EMAIL, Constraints.PASSWORD);
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.profileButtonClick();
        account = new Account(webDriver);
        account.changePassword(Constraints.NOT_VALID_PASSWORD, Constraints.USER_NEW_PASSWORD, Constraints.USER_NEW_PASSWORD);

        Assertions.assertEquals("assertive", webDriver.findElement(By.xpath("//*[contains(@class,'cdk-live-announcer-element cdk-visually-hidden')]")).getAttribute("aria-live"));
    }

    @Test
    @Order(3)
    @DisplayName("PD-03 Felhasználó jelszavának módosítása, az új jelszó megerősítése helytelenül")
    public void testAddWrongConfirmPassword(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(Constraints.URL);
        landingCalendar.clickHamburgerButton();
        landingCalendar.clickLoginPageButton();
        loginLogout = new LoginLogout(webDriver);
        loginLogout.userLogin(Constraints.EMAIL, Constraints.PASSWORD);
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.profileButtonClick();
        account = new Account(webDriver);
        account.changePassword(Constraints.PASSWORD, Constraints.USER_NEW_PASSWORD, Constraints.NOT_MATCH_PASSWORD);

        Assertions.assertEquals("polite", webDriver.findElement(By.xpath("//*[contains(@class,'cdk-live-announcer-element cdk-visually-hidden')]")).getAttribute("aria-live"));
    }

    @Test
    @Order(4)
    @DisplayName("PD-04 Felhasználó jelszavának módosítása, helyes adatokkal")
    public void testChangePassword(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(Constraints.URL);
        landingCalendar.clickHamburgerButton();
        landingCalendar.clickLoginPageButton();
        loginLogout = new LoginLogout(webDriver);
        loginLogout.userLogin(Constraints.EMAIL, Constraints.PASSWORD);
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.profileButtonClick();
        account = new Account(webDriver);
        account.changePassword(Constraints.PASSWORD, Constraints.USER_NEW_PASSWORD, Constraints.USER_NEW_PASSWORD);

        Assertions.assertEquals("true", webDriver.findElement(By.xpath("//*[contains(@class,'cdk-live-announcer-element cdk-visually-hidden')]")).getAttribute("aria-atomic"));
    }

    @Test
    @Order(5)
    @DisplayName("PD-05 Felhasználó számlázási adatainak megadása")
    public void testUploadUserBillingDetails(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(Constraints.URL);
        landingCalendar.clickHamburgerButton();
        landingCalendar.clickLoginPageButton();
        loginLogout = new LoginLogout(webDriver);
        loginLogout.userLogin(Constraints.EMAIL, Constraints.USER_NEW_PASSWORD);
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.profileButtonClick();
        account = new Account(webDriver);
        account.uploadBillingDetails(Constraints.USER_FULLNAME, Constraints.USER_COUNTRY, Constraints.USER_POSTAL_CODE, Constraints.USER_CITY, Constraints.USER_ADDRESS);

        Assertions.assertTrue(webDriver.findElement(By.xpath("//*[contains(@class,'mat-simple-snackbar ng-star-inserted')]")).isDisplayed());

    }

    @Test
    @Order(6)
    @DisplayName("PD-06 Felhasználó jelszavának visszaállítása az eredetire")
    public void testChangeBackToOriginalPassword(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(Constraints.URL);
        landingCalendar.clickHamburgerButton();
        landingCalendar.clickLoginPageButton();
        loginLogout = new LoginLogout(webDriver);
        loginLogout.userLogin(Constraints.EMAIL, Constraints.USER_NEW_PASSWORD);
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.profileButtonClick();
        account = new Account(webDriver);
        account.changePassword(Constraints.USER_NEW_PASSWORD, Constraints.USER_PASSWORD, Constraints.USER_PASSWORD);

        Assertions.assertEquals("true", webDriver.findElement(By.xpath("//*[contains(@class,'cdk-live-announcer-element cdk-visually-hidden')]")).getAttribute("aria-atomic"));
    }

    @Test
    @Order(7)
    @DisplayName("PD-07 Felhasználó számlázási adatainak (címének) módosítása")
    public void testModifyUserBillingDetails(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(Constraints.URL);
        landingCalendar.clickHamburgerButton();
        landingCalendar.clickLoginPageButton();
        loginLogout = new LoginLogout(webDriver);
        loginLogout.userLogin(Constraints.EMAIL, Constraints.PASSWORD);
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.profileButtonClick();
        account = new Account(webDriver);
        account.uploadBillingDetails(Constraints.USER_FULLNAME, Constraints.USER_COUNTRY, Constraints.USER_MODIFIED_POSTAL_CODE, Constraints.USER_MODIFIED_CITY, Constraints.USER_MODIFIED_ADDRESS);

        Assertions.assertTrue(webDriver.findElement(By.xpath("//*[contains(@class,'mat-simple-snackbar ng-star-inserted')]")).isDisplayed());

    }

    @Test
    @Order(8)
    @DisplayName("PD-08 Felhasználó számlázási adatainak (kivéve név) törlése")
    public void testDeleteUserBillingDetails(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(Constraints.URL);
        landingCalendar.clickHamburgerButton();
        landingCalendar.clickLoginPageButton();
        loginLogout = new LoginLogout(webDriver);
        loginLogout.userLogin(Constraints.EMAIL, Constraints.PASSWORD);
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.profileButtonClick();
        account = new Account(webDriver);
        account.deleteBillingDetails(Constraints.USER_FULLNAME);

        Assertions.assertTrue(webDriver.findElement(By.xpath("//*[contains(@class,'mat-simple-snackbar ng-star-inserted')]")).isDisplayed());

    }
}
