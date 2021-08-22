import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

public class TestTest {

    WebDriver webdriver;

//    @BeforeAll
//    public static void Init() {
//        WebDriverManager.chromedriver().setup();
//    }

    @BeforeEach
    public void SetDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        webdriver = new ChromeDriver(options);

        webdriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        webdriver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown(){
        webdriver.quit();
    }

    @Test
    public void goToUrl(){
        webdriver.get("https://igym.hu/");

        Assertions.assertEquals("https://igym.hu/", webdriver.getCurrentUrl());
    }

}
