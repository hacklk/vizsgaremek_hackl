package pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;
import java.io.*;
import java.util.List;

public class LandingCalendar {
    WebDriver webDriver;

    public LandingCalendar(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

// WELCOME/LANDING PAGE VARIABLES
    private final By HAMBURGER_BUTTON = By.xpath("//mat-icon");
    private final By LOGIN_ICON = By.xpath("//mat-toolbar-row/div/div/span");
    private final By LOGIN_BUTTON = By.xpath("//mat-toolbar-row/div/div/span");
    private final By LOGIN_PAGE_BUTTON = By.xpath("//mat-nav-list/a[1]/div/span/div[1]");
    private final By GDPR_BUTTON = By.xpath("//section//a");
    private final By ACCEPT_COOKIES_BUTTON = By.xpath("//section/button");

    public static final String TRAINING_TYPE_FREE = "INGYENES";
    private final By TRAINING_LIST = By.xpath("//*[contains(@class,'container ng-star-inserted')]");
    private final By TRAININGS = By.cssSelector(".mat-card");
    private final By CALENDAR_RIGHT_ARROW_BUTTON = By.xpath("//section/div/span[2]");
    private final By TOP_MENU_JOGA_BUTTON = By.cssSelector("div:nth-child(1) > button > mat-icon > svg");
    private final By TOP_MENU_STRECHING_BUTTON = By.cssSelector("div:nth-child(2) > button > mat-icon > svg");
    private final By TOP_MENU_MEDITATION_BUTTON = By.cssSelector("div:nth-child(3) > button > mat-icon > svg");
    private final By TOP_MENU_KARDIO_BUTTON = By.cssSelector("div:nth-child(4) > button > mat-icon > svg");
    private final By TOP_MENU_PILATES_BUTTON = By.cssSelector("div:nth-child(5) > button > mat-icon > svg");
    private final By TOP_MENU_BODYFIT_BUTTON = By.cssSelector("div:nth-child(6) > button > mat-icon > svg");
    private final By TOP_MENU_MUSCLE_BUTTON = By.cssSelector("div:nth-child(7) > button > mat-icon > svg");
    private final By TOP_MENU_OTHER_BUTTON = By.cssSelector("div:nth-child(8) > button > mat-icon > svg");

// CALENDAR VARIABLES
    private final By PROFILE_BUTTON = By.xpath("//mat-nav-list/a[4]/div/span/div[1]");
    private final By LOGOUT_ICON = By.xpath("//mat-icon[2]");
    private final By BUY_TICKET_BUTTON = By.xpath("//mat-toolbar-row[1]/div/div/button");
    private final By TRAINER_BUTTON = By.xpath("//mat-nav-list/a[2]/div/span/div[1]");
    private final By RING_ICON = By.xpath("//div[2]/mat-icon[1]");
    private final By NOTIFICATIONS = By.xpath("//notification-list/div[2]");

// LANDING PAGE FUNCTIONALITIES
    public void navigateToURL(String url) {
        webDriver.get(url);
    }

    public void ringIconClick(){
        webDriver.findElement(RING_ICON).click();
    }

    public void loginIconClick(){
        webDriver.findElement(LOGIN_ICON).click();
        new LoginLogout(webDriver);
    }

    public String saveNotificationsToFile() {
        WebElement notifications = webDriver.findElement(NOTIFICATIONS);
        String text = notifications.getText();
        try {
            FileWriter textFile = new FileWriter("notifications.txt");
            textFile.append(text);
            textFile.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return text;
    }

    public String readNotifications() {
        StringBuilder text = new StringBuilder();
        try {
            File file = new File("notifications.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                text.append(scanner.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        if (text.length() > 0) {
            text = new StringBuilder(text.substring(0, text.length() - 1));
        }
        return text.toString();
    }

    public void clickHamburgerButton() {
        webDriver.findElement(HAMBURGER_BUTTON).click();
    }

    public void clickLoginPageButton() {
        webDriver.findElement(LOGIN_PAGE_BUTTON).click();
        new LoginLogout(webDriver);
    }

    public void clickLoginButton(){
        webDriver.findElement(LOGIN_BUTTON).click();
        new LoginLogout(webDriver);
    }

    public void clickCookiesAcceptButton() {
        webDriver.findElement(ACCEPT_COOKIES_BUTTON).click();
    }

    public void clickYogaButton() {
        webDriver.findElement(TOP_MENU_JOGA_BUTTON).click();
    }

    public void clickStechingButton() {
        webDriver.findElement(TOP_MENU_STRECHING_BUTTON).click();
    }

    public void clickMeditationButton() {
        webDriver.findElement(TOP_MENU_MEDITATION_BUTTON).click();
    }

    public void clickCardioButton() {
        webDriver.findElement(TOP_MENU_KARDIO_BUTTON).click();
    }

    public void clickPilatesButton() {
        webDriver.findElement(TOP_MENU_PILATES_BUTTON).click();
    }

    public void clickBodyFitButton() {
        webDriver.findElement(TOP_MENU_BODYFIT_BUTTON).click();
    }

    public void clickMuscleButton() {
        webDriver.findElement(TOP_MENU_MUSCLE_BUTTON).click();
    }

    public void clickOtherButton() {
        webDriver.findElement(TOP_MENU_OTHER_BUTTON).click();
    }

    public void clickCalendarRightArrowButton() {
        webDriver.findElement(CALENDAR_RIGHT_ARROW_BUTTON).click();
    }

    public void openGDPR() {
        webDriver.findElement(GDPR_BUTTON).click();
        new GDPR(webDriver);
    }

    public boolean selectTrainingType(String type) {
        boolean isContains = false;
        List<WebElement> trainings = webDriver.findElements(TRAINING_LIST);
        for (WebElement training : trainings) {
            List<WebElement> currentTrainings = training.findElements(TRAININGS);
            for(WebElement current : currentTrainings){
                if (current.getText().toUpperCase().contains(type.toUpperCase())) {
                    isContains = true;
                }
            }
        }
        return isContains;
    }

    public boolean topMenuButtonChecker(String type) {
        boolean isContains = false;
        List<WebElement> trainings = webDriver.findElements(TRAINING_LIST);
        if(trainings.size() == 0){
            isContains = true;
        }
        for (WebElement training : trainings) {
            List<WebElement> currentTrainings = training.findElements(TRAININGS);
            for (WebElement current : currentTrainings) {
                isContains = current.getText().toUpperCase().contains(type.toUpperCase());
            }
        }
        return isContains;
    }

    public int sumFreeTraining() {
        List<String> allFreeTrainings = new ArrayList<>();
        int sum = 0;
        List<WebElement> trainingsList;
        do {
            trainingsList = webDriver.findElements(TRAINING_LIST);
            for (WebElement weeklyTrainings : trainingsList) {
                List<WebElement> weeklyTrainingsList = weeklyTrainings.findElements(TRAININGS);
                for (WebElement training : weeklyTrainingsList) {
                    String newTrainingToList = training.getText();
                    if (newTrainingToList.toUpperCase().contains(TRAINING_TYPE_FREE)){
                        allFreeTrainings.add(newTrainingToList);
                        sum = allFreeTrainings.size();
                    }

                }
            }
            clickCalendarRightArrowButton();
        }
        while (trainingsList.size() > 0);
        return sum;
    }

// CALENDAR PAGE FUNCTIONALITIES
    public void logout() {
    webDriver.findElement(LOGOUT_ICON).click();
}

    public void clickProfileButton() {
        webDriver.findElement(PROFILE_BUTTON).click();
        new Account(webDriver);
    }

    public void clickTrainerButton() {
        webDriver.findElement(TRAINER_BUTTON).click();
    }

}
