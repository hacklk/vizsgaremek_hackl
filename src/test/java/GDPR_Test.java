import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageClasses.GDPR;
import pageClasses.Landing;

import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class GDPR_Test {
    WebDriver webDriver;
    Landing landing;
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
        landing = new Landing(webDriver);
//        landing.navigateToURL(Constraints.URL);  // ez honnan import ???
        landing.openGDPR();
        gdpr = new GDPR(webDriver);
        gdpr.windowChange();
        landing.clickCookiesAcceptButton();

        try {
            Assertions.assertFalse(webDriver.findElement(By.xpath("//app-accept-cookie/section")).isDisplayed());
        } catch (Exception e) {
        }
    }
}
