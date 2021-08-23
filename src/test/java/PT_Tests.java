
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageClasses.LandingCalendar;
import pageClasses.LoginLogout;
import pageClasses.YourPT;
import java.util.concurrent.TimeUnit;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PT_Tests {
    WebDriver webDriver;
    LandingCalendar landingCalendar;
    LoginLogout loginLogout;
    YourPT yourPT;

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

//    TESTS ON THE TRAINER

    @Test
    @Order(1)
    @DisplayName("PTRa-01 FILTER on Personal Trainer's name")
    public void testSearchTrainer() {
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.clickLoginButton();
        loginLogout = new LoginLogout(webDriver);
        loginLogout.userLogin(FinalConstants.EMAIL, FinalConstants.VALID_PASSWORD);
        landingCalendar.clickHamburgerButton();
        landingCalendar.clickTrainerButton();
        yourPT = new YourPT(webDriver);
        yourPT.searchTrainer(FinalConstants.TRAINER_NAME);

        Assertions.assertEquals(FinalConstants.TRAINER_NAME, webDriver.findElement(By.xpath("//*/mat-card-content/div[1]")).getText());

    }

    @Test
    @Order(2)
    @DisplayName("PTRa-02 OPEN Trainer's page by More Info button")
    public void testMoreTrainerInfo() {
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.clickLoginButton();
        loginLogout = new LoginLogout(webDriver);
        loginLogout.userLogin(FinalConstants.EMAIL, FinalConstants.VALID_PASSWORD);
        landingCalendar.clickHamburgerButton();
        landingCalendar.clickTrainerButton();
        yourPT = new YourPT(webDriver);
        yourPT.getTrainerAllDetails(FinalConstants.TRAINER_NAME);

        Assertions.assertTrue(webDriver.findElement(By.xpath("//*/app-rating-container/div/div[2]")).isDisplayed());
    }

//    TESTS on the TRAINING

    @Test
    @Order(3)
    @DisplayName("PTR-01 SAVE Notification data to txt file")
    public void testSaveTrainingDetailsToFile() {
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(FinalConstants.URL);

        landingCalendar.clickHamburgerButton();
        landingCalendar.clickLoginPageButton();
        loginLogout = new LoginLogout(webDriver);
        loginLogout.userLogin(FinalConstants.EMAIL, FinalConstants.VALID_PASSWORD);

        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.ringIconClick();

        String text = landingCalendar.saveNotificationsToFile();
        String result = landingCalendar.readNotifications();

        Assertions.assertEquals(text, result);
    }

    @Test
    @Order(4)
    @DisplayName("PTR-02 SEARCH on training type")
    public void testChooseTrainingType(){
        landingCalendar =new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(FinalConstants.URL);
        boolean isContains = landingCalendar.selectTrainingType(FinalConstants.TRAINING_TYPE_MEDITATON);

        Assertions.assertTrue(isContains);
    }

    @Test
    @Order(5)
    @DisplayName("PTR-03 SEARCH on wrong training type")
    public void testChooseNotValidTrainingType(){
        landingCalendar =new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(FinalConstants.URL);
        boolean isContains = landingCalendar.selectTrainingType(FinalConstants.TRAINING_TYPE_INVALID);

        Assertions.assertFalse(isContains);
    }

    @Test
    @Order(6)
    @DisplayName("PTR-04 TEST menu (Yoga)")
    public void testOfYogaButton(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.clickYogaButton();
        boolean isContains = landingCalendar.topMenuButtonChecker(FinalConstants.TRAINING_TYPE_JOGA);

        Assertions.assertTrue(isContains);
    }

    @Test
    @Order(7)
    @DisplayName("PTR-05 TEST menu (Streching)")
    public void testOfStrechingButton(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.clickStechingButton();
        boolean isContains = landingCalendar.topMenuButtonChecker(FinalConstants.TRAINING_TYPE_STRECHING);

        Assertions.assertTrue(isContains);
    }

    @Test
    @Order(8)
    @DisplayName("PTR-06 TEST menu (Meditation)")
    public void testOfMeditationButton(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.clickMeditationButton();
        boolean isContains = landingCalendar.topMenuButtonChecker(FinalConstants.TRAINING_TYPE_MEDITATON);

        Assertions.assertTrue(isContains);
    }

    @Test
    @Order(9)
    @DisplayName("PTR-07 TEST menu (Cardio)")
    public void testOfKardioButton(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.clickCardioButton();
        boolean isContains = landingCalendar.topMenuButtonChecker(FinalConstants.TRAINING_TYPE_KARDIO);

        Assertions.assertTrue(isContains);
    }

    @Test
    @Order(10)
    @DisplayName("PTR-08 TEST menu (PILATES)")
    public void testOfPilatesButton(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.clickPilatesButton();
        boolean isContains = landingCalendar.topMenuButtonChecker(FinalConstants.TRAINING_TYPE_PILATES);

        Assertions.assertTrue(isContains);
    }

    @Test
    @Order(11)
    @DisplayName("PTR-09 TEST menu (Shape)")
    public void testOfBodyFitButton(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.clickBodyFitButton();
        boolean isContains = landingCalendar.topMenuButtonChecker(FinalConstants.TRAINING_TYPE_BODYFIT);

        Assertions.assertTrue(isContains);
    }

    @Test
    @Order(12)
    @DisplayName("PTR-10 TEST menu (Strength)")
    public void testOfMuscleButton(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.clickMuscleButton();
        boolean isContains = landingCalendar.topMenuButtonChecker(FinalConstants.TRAINING_TYPE_MUSCLE);

        Assertions.assertTrue(isContains);
    }

    @Test
    @Order(13)
    @DisplayName("PTR-11 TEST menu (Other)")
    public void testOfOtherButton(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.clickOtherButton();
        boolean isContains = landingCalendar.topMenuButtonChecker(FinalConstants.TRAINING_TYPE_OTHER);

        Assertions.assertTrue(isContains);
    }

    @Test
    @Order(14)
    @DisplayName("PTR-12 TEST the SUM of ALL FREE Trainings by CSS SELECTOR")
    public void testFindFreeTrainings(){
        landingCalendar = new LandingCalendar(webDriver);
        landingCalendar.navigateToURL(FinalConstants.URL);
        int sumTrainings = landingCalendar.sumFreeTraining();
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.clickYogaButton();
        int sumJoga = landingCalendar.sumFreeTraining();
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.clickStechingButton();
        int sumStreching = landingCalendar.sumFreeTraining();
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.clickMeditationButton();
        int sumMeditation = landingCalendar.sumFreeTraining();
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.clickCardioButton();
        int sumKardio = landingCalendar.sumFreeTraining();
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.clickPilatesButton();
        int sumPilates = landingCalendar.sumFreeTraining();
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.clickBodyFitButton();
        int sumBodyFit = landingCalendar.sumFreeTraining();
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.clickMuscleButton();
        int sumMuscle = landingCalendar.sumFreeTraining();
        landingCalendar.navigateToURL(FinalConstants.URL);
        landingCalendar.clickOtherButton();
        int sumOther = landingCalendar.sumFreeTraining();

        Assertions.assertEquals(sumTrainings, sumJoga + sumStreching + sumMeditation + sumKardio + sumPilates + sumBodyFit + sumMuscle + sumOther);
    }

}
