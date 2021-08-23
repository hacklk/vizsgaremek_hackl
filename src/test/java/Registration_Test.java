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
    @DisplayName("REG-01 REGISTRATION no email")
    public void testNoEmailRegistration(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.loginIconClick();
        loginLogout = new LoginLogout(webDriver);
        loginLogout.registrationButtonClick();
        registration = new Registration(webDriver);
        registration.noEmailRegistration(FinalConstants.VALID_PASSWORD);

        Assertions.assertEquals("https://igym-igym-dev.azurewebsites.net/authentication/registration", webDriver.getCurrentUrl());
    }

    @Test
    @Order(2)
    @DisplayName("REG-02 REGISTRATION no password")
    public void testNoPasswordRegistration(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.loginIconClick();
        loginLogout = new LoginLogout(webDriver);
        loginLogout.registrationButtonClick();
        registration = new Registration(webDriver);
        registration.noPasswordRegistration(FinalConstants.EMAIL);

        Assertions.assertEquals("https://igym-igym-dev.azurewebsites.net/authentication/registration", webDriver.getCurrentUrl());
    }

    @Test
    @Order(3)
    @DisplayName("REG-03 REGISTRATION wrong password")
    public void testNotValidPasswordRegistration(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.loginIconClick();
        loginLogout = new LoginLogout(webDriver);
        loginLogout.registrationButtonClick();
        registration = new Registration(webDriver);
        registration.notValidPasswordRegistration(FinalConstants.EMAIL, FinalConstants.NOT_VALID_PASSWORD);

        Assertions.assertEquals("https://igym-igym-dev.azurewebsites.net/authentication/registration", webDriver.getCurrentUrl());
    }

    @Test
    @Order(4)
    @DisplayName("REG-04 REGISTRATION no password validation")
    public void testNotMatchPasswordRegistration(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.loginIconClick();
        loginLogout = new LoginLogout(webDriver);
        loginLogout.registrationButtonClick();
        registration = new Registration(webDriver);
        registration.notMatchPasswordRegistration(FinalConstants.EMAIL, FinalConstants.VALID_PASSWORD, FinalConstants.NOT_CORRECT_PASSWORD);

        Assertions.assertEquals("https://igym-igym-dev.azurewebsites.net/authentication/registration", webDriver.getCurrentUrl());
    }

    @Test
    @Order(5)
    @DisplayName("REG-05 REGISTRATION no user agreement acceptance")
    public void testNoClickTermsCondtsRegistration(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.loginIconClick();
        loginLogout = new LoginLogout(webDriver);
        loginLogout.registrationButtonClick();
        registration = new Registration(webDriver);
        registration.noClickTermsCondtsRegistration(FinalConstants.EMAIL, FinalConstants.VALID_PASSWORD);

        Assertions.assertFalse(webDriver.findElement(By.xpath("//*/div/button[1]")).isEnabled());
    }

    @Test
    @Order(6)
    @DisplayName("REG-06 REGISTRATION no GDPR acceptance")
    public void testNoClickPrivacyPolicyRegistration(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.loginIconClick();
        loginLogout = new LoginLogout(webDriver);
        loginLogout.registrationButtonClick();
        registration = new Registration(webDriver);
        registration.noClickPrivacyPolicyRegistration(FinalConstants.EMAIL, FinalConstants.VALID_PASSWORD);

        Assertions.assertFalse(webDriver.findElement(By.xpath("//*/div/button[1]")).isEnabled());
    }

//    YOU LIVE ONLY ONCE
    @Test
    @Order(7)
    @Disabled // will not run this one
    @DisplayName("REG-07 REGISTRATION")
    public void testRegistration(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.loginIconClick();
        loginLogout = new LoginLogout(webDriver);
        loginLogout.registrationButtonClick();
        registration = new Registration(webDriver);
        registration.registration(FinalConstants.EMAIL, FinalConstants.VALID_PASSWORD);

        Assertions.assertEquals("Sikeres regisztráció, kérjük erősítse meg email címét", webDriver.findElement(By.xpath("//simple-snack-bar/span")).getText());
    }
}
