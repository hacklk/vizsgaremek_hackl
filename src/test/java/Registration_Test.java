import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageClasses.LandingCalendar;
import pageClasses.LoginLogout;
import pageClasses.Registration;
import java.util.concurrent.TimeUnit;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Registration_Test {
    WebDriver webDriver;
    LandingCalendar landingCalendar;
    Registration registration;
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
    @DisplayName("RI-01 Regisztráció email cím megadása nélkül")
    public void testNoEmailRegistration(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(Constraints.URL);
        landingCalendar.clickHamburgerButton();
        landingCalendar.clickRegistrationButton();
        registration = new Registration(webDriver);
        registration.noEmailRegistration(Constraints.PASSWORD);

        Assertions.assertEquals("https://igym-igym-dev.azurewebsites.net/authentication/registration", webDriver.getCurrentUrl());
    }

    @Test
    @Order(2)
    @DisplayName("RI-02 Regisztráció jelszó megadása nélkül")
    public void testNoPasswordRegistration(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(Constraints.URL);
        landingCalendar.clickHamburgerButton();
        landingCalendar.clickRegistrationButton();
        registration = new Registration(webDriver);
        registration.noPasswordRegistration(Constraints.EMAIL);

        Assertions.assertEquals("https://igym-igym-dev.azurewebsites.net/authentication/registration", webDriver.getCurrentUrl());
    }

    @Test
    @Order(3)
    @DisplayName("RI-03 Regisztráció érvénytelen jelszó megadásával")
    public void testNotValidPasswordRegistration(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(Constraints.URL);
        landingCalendar.clickHamburgerButton();
        landingCalendar.clickRegistrationButton();
        registration = new Registration(webDriver);
        registration.notValidPasswordRegistration(Constraints.EMAIL, Constraints.NOT_VALID_PASSWORD);

        Assertions.assertEquals("https://igym-igym-dev.azurewebsites.net/authentication/registration", webDriver.getCurrentUrl());
    }

    @Test
    @Order(4)
    @DisplayName("RI-04 Regisztráció nem egyező jelszavak megadásával")
    public void testNotMatchPasswordRegistration(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(Constraints.URL);
        landingCalendar.clickHamburgerButton();
        landingCalendar.clickRegistrationButton();
        registration = new Registration(webDriver);
        registration.notMatchPasswordRegistration(Constraints.EMAIL, Constraints.PASSWORD, Constraints.NOT_MATCH_PASSWORD);

        Assertions.assertEquals("https://igym-igym-dev.azurewebsites.net/authentication/registration", webDriver.getCurrentUrl());
    }

    @Test
    @Order(5)
    @DisplayName("RI-05 Regisztráció a felhasználási feltételek elfogadása nélkül")
    public void testNoClickTermsCondtsRegistration(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(Constraints.URL);
        landingCalendar.clickHamburgerButton();
        landingCalendar.clickRegistrationButton();
        registration = new Registration(webDriver);
        registration.noClickTermsCondtsRegistration(Constraints.EMAIL, Constraints.PASSWORD);

        Assertions.assertFalse(webDriver.findElement(By.xpath("//*/div/button[1]")).isEnabled());
    }

    @Test
    @Order(6)
    @DisplayName("RI-06 Regisztráció az adatvédelmi feltételek elfogadása nélkül")
    public void testNoClickPrivacyPolicyRegistration(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(Constraints.URL);
        landingCalendar.clickHamburgerButton();
        landingCalendar.clickRegistrationButton();
        registration = new Registration(webDriver);
        registration.noClickPrivacyPolicyRegistration(Constraints.EMAIL, Constraints.PASSWORD);

        Assertions.assertFalse(webDriver.findElement(By.xpath("//*/div/button[1]")).isEnabled());
    }

    @Test
    @Order(7)
    @Disabled
    @DisplayName("RI-07 Regisztráció minden érvényes adat megadásával")
    public void testRegistration(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(Constraints.URL);
        landingCalendar.clickHamburgerButton();
        landingCalendar.clickRegistrationButton();
        registration = new Registration(webDriver);
        registration.registration(Constraints.EMAIL, Constraints.PASSWORD);

        Assertions.assertEquals("Sikeres regisztráció, kérjük erősítse meg email címét", webDriver.findElement(By.xpath("//simple-snack-bar/span")).getText());
    }
}
