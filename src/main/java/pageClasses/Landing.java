package pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Landing {
    WebDriver webDriver;

    public Landing(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    private final By HAMBURGER_BUTTON = By.xpath("//mat-icon");
    private final By LOGIN_BUTTON = By.xpath("//mat-toolbar-row/div/div/span");
    private final By LOGIN_PAGE_BUTTON = By.xpath("//mat-nav-list/a[1]/div/span/div[1]");
    private final By REGISTRATION_PAGE_BUTTON = By.xpath("//mat-nav-list/a[2]/div/span/div[1]");
    private final By GDPR_BUTTON = By.xpath("//section//a");
    private final By ACCEPT_COOKIES_BUTTON = By.xpath("//section/button");

    private final By TRAINING_LIST = By.xpath("//*[contains(@class,'container ng-star-inserted')]");
    private final By TRAININGS = By.cssSelector(".mat-card");
    private final By CALENDAR_RIGHT_ARROW_BUTTON = By.xpath("//section/div/span[2]");
    private final By UPPER_MENU_JOGA_BUTTON = By.cssSelector("div:nth-child(1) > button > mat-icon > svg");
    private final By UPPER_MENU_STRECHING_BUTTON = By.cssSelector("div:nth-child(2) > button > mat-icon > svg");
    private final By UPPER_MENU_MEDITATION_BUTTON = By.cssSelector("div:nth-child(3) > button > mat-icon > svg");
    private final By UPPER_MENU_KARDIO_BUTTON = By.cssSelector("div:nth-child(4) > button > mat-icon > svg");
    private final By UPPER_MENU_PILATES_BUTTON = By.cssSelector("div:nth-child(5) > button > mat-icon > svg");
    private final By UPPER_MENU_BODYFIT_BUTTON = By.cssSelector("div:nth-child(6) > button > mat-icon > svg");
    private final By UPPER_MENU_MUSCLE_BUTTON = By.cssSelector("div:nth-child(7) > button > mat-icon > svg");
    private final By UPPER_MENU_OTHER_BUTTON = By.cssSelector("div:nth-child(8) > button > mat-icon > svg");

    public void navigateToURL(String url) {
        webDriver.get(url);
    }

    public void clickHamburgerButton() {
        webDriver.findElement(HAMBURGER_BUTTON).click();
    }

    public void clickLoginPageButton() {
        webDriver.findElement(LOGIN_PAGE_BUTTON).click();
//        new LoginPage(webDriver);
    }

    public void clickRegistrationButton() {
        webDriver.findElement(REGISTRATION_PAGE_BUTTON).click();
//        new RegistrationPage(webDriver);
    }

    public void loginButtonClick(){
        webDriver.findElement(LOGIN_BUTTON).click();
//        new LoginPage(webDriver);
    }

    public void clickCookiesAcceptButton() {
        webDriver.findElement(ACCEPT_COOKIES_BUTTON).click();
    }

    public void jogaButtonClick() {
        webDriver.findElement(UPPER_MENU_JOGA_BUTTON).click();
    }

    public void stechingButtonClick() {
        webDriver.findElement(UPPER_MENU_STRECHING_BUTTON).click();
    }

    public void meditationButtonClick() {
        webDriver.findElement(UPPER_MENU_MEDITATION_BUTTON).click();
    }

    public void kardioButtonClick() {
        webDriver.findElement(UPPER_MENU_KARDIO_BUTTON).click();
    }

    public void pilatesButtonClick() {
        webDriver.findElement(UPPER_MENU_PILATES_BUTTON).click();
    }

    public void bodyFitButtonClick() {
        webDriver.findElement(UPPER_MENU_BODYFIT_BUTTON).click();
    }

    public void muscleButtonClick() {
        webDriver.findElement(UPPER_MENU_MUSCLE_BUTTON).click();
    }

    public void otherButtonClick() {
        webDriver.findElement(UPPER_MENU_OTHER_BUTTON).click();
    }

    public void calendarRightArrowButtonClick() {
        webDriver.findElement(CALENDAR_RIGHT_ARROW_BUTTON).click();
    }

    public void openGDPR() {
        webDriver.findElement(GDPR_BUTTON).click();
        new GDPR(webDriver);
    }

    //save a training data's to trainingResult.txt file
    public String saveTrainingDatasToFile() {
        List<WebElement> trainings = webDriver.findElements(TRAINING_LIST);
        String text = "";
        if (trainings.size() > 0) {
            text = trainings.get(1).getText();
            try {
                FileWriter textFile = new FileWriter("trainingResult.txt");
                textFile.append(text);
                textFile.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return text;
    }

    //read the data's from trainingResult.txt
    public String readTrainingDetailsFile() {
        String result = "";
        try {
            File file = new File("trainingResult.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                result += scanner.nextLine() + "\n";
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        if (result.length() > 0) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }


    //gives back that in that week there is training type that we are searching
    public boolean chooseTrainingType(String type) {
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

    //checking that the upper menu buttons working correctly
    public boolean upperMenuButtonChecker(String type) {
        boolean isContains = false;
        List<WebElement> trainings = webDriver.findElements(TRAINING_LIST);
        if(trainings.size() == 0){
            isContains = true;
        }
        for (WebElement training : trainings) {
            List<WebElement> currentTrainings = training.findElements(TRAININGS);
            for (WebElement current : currentTrainings) {
                if (current.getText().toUpperCase().contains(type.toUpperCase())) {
                    isContains = true;
                } else {
                    isContains = false;
                }
            }
        }
        return isContains;
    }

    //sum all of the trainings from calendar
    public int sumTraining() {
        List<String> allOfActiveTrainings = new ArrayList<>();
        int sum = 0;
        List<WebElement> trainingsList;
        do {
            trainingsList = webDriver.findElements(TRAINING_LIST);
            for (WebElement weeklyTrainings : trainingsList) {
                List<WebElement> weeklyTrainingsList = weeklyTrainings.findElements(TRAININGS);
                for (WebElement training : weeklyTrainingsList) {
                    String newTrainingToList = training.getText();
                    allOfActiveTrainings.add(newTrainingToList);
                    sum = allOfActiveTrainings.size();
                }
            }
            calendarRightArrowButtonClick();
        }
        while (trainingsList.size() > 0);
        return sum;
    }
}
