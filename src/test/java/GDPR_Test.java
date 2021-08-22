import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageClasses.GDPR;
import pageClasses.LandingCalendar;

import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class GDPR_Test {
    WebDriver webDriver;
    LandingCalendar landingCalendar;
    GDPR gdpr;

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
    public void tearDown() {
        webDriver.quit();
    }

    @Test
    @Order(1)
    public void TestOpenGDPR() {
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(Constraints.URL);
        landingCalendar.openGDPR();
        gdpr = new GDPR(webDriver);
        gdpr.windowChange();
        landingCalendar.clickCookiesAcceptButton();

        try {
            Assertions.assertFalse(webDriver.findElement(By.xpath("//app-accept-cookie/section")).isDisplayed());
        } catch (Exception e) {
        }
    }
}
