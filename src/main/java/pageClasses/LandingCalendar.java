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

//  LANDING VARIABLES
    private final By HAMBURGER_BUTTON = By.xpath("//mat-icon");
    private final By LOGIN_ICON = By.xpath("//mat-toolbar-row/div/div/span");
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

//    CALENDAR VARIABLES
private final By PROFILE_BUTTON = By.xpath("//mat-nav-list/a[4]/div/span/div[1]");
//    private final By HAMBURGER_BUTTON = By.xpath("//span/mat-icon");
    private final By LOGOUT_ICON = By.xpath("//mat-icon[2]");
    private final By MY_TICKETS_MENU = By.xpath("//mat-nav-list/a[3]/div/span/div[1]");
    private final By BUY_TICKET_BUTTON = By.xpath("//mat-toolbar-row[1]/div/div/button");
    private final By SIX_TIME_TRAINING = By.xpath("//*[@id=\"mat-option-0\"]/span");
    private final By TEN_TIME_TRAINING = By.xpath("//*[@id=\"mat-option-1\"]/span");
    private final By FOURTEEN_TIME_TRAINING = By.xpath("//*[@id=\"mat-option-2\"]/span");
//    private final By UPPER_MENU_JOGA_BUTTON = By.cssSelector("div:nth-child(1) > button > mat-icon > svg");
//    private final By UPPER_MENU_PILATES_BUTTON = By.cssSelector("div:nth-child(5) > button > mat-icon > svg");
//    private final By UPPER_MENU_STRECHING_BUTTON = By.cssSelector("div:nth-child(2) > button > mat-icon > svg");
//    private final By UPPER_MENU_OTHER_BUTTON = By.cssSelector("div:nth-child(8) > button > mat-icon > svg");
    private final By NUMBER_OF_TICKETS_DROPDOWN = By.xpath("//*[contains(@role,'listbox')]");
    private final By APPLY_BUTTON = By.xpath("//mat-card/button[2]");

    private final By TRAINER_BUTTON = By.xpath("//mat-nav-list/a[2]/div/span/div[1]");
//    private final By TRAINING_LIST = By.xpath("//app-training-calendar-list-view");

// LANDING PAGE FUNCTIONALITIES

    public void navigateToURL(String url) {
        webDriver.get(url);
    }

    public void clickHamburgerButton() {
        webDriver.findElement(HAMBURGER_BUTTON).click();
    }

    public void clickLoginPageButton() {
        webDriver.findElement(LOGIN_PAGE_BUTTON).click();
        new LoginLogout(webDriver);
    }

    public void clickRegistrationButton() {
        webDriver.findElement(REGISTRATION_PAGE_BUTTON).click();
//        new RegistrationPage(webDriver);
    }

    public void loginButtonClick(){
        webDriver.findElement(LOGIN_BUTTON).click();
        new LoginLogout(webDriver);
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

//    CALENDAR PAGE FUNCTIONALITIES
public void logout() {
    webDriver.findElement(LOGOUT_ICON).click();
}

    public void profileButtonClick() {
        webDriver.findElement(PROFILE_BUTTON).click();
//        new PersonalDetails(webDriver);
    }

    public void clickMyTickets(){
        webDriver.findElement(MY_TICKETS_MENU).click();
//        new MyTicketsPage(webDriver);
    }

    public void clickTrainerButton() {
        webDriver.findElement(TRAINER_BUTTON).click();
    }

    public void buyTicketButtonClick() {
        webDriver.findElement(BUY_TICKET_BUTTON).click();
    }

    public void checkTrainingLiteTicketPrice() {
        buyTicketButtonClick();
        webDriver.findElement(NUMBER_OF_TICKETS_DROPDOWN).click();
        webDriver.findElement(SIX_TIME_TRAINING).click();
    }

    public void checkTrainingPlusTicketPrice() {
        buyTicketButtonClick();
        webDriver.findElement(NUMBER_OF_TICKETS_DROPDOWN).click();
        webDriver.findElement(FOURTEEN_TIME_TRAINING).click();
    }

    public void checkTrainingProTicketPricePerTraining() {
        buyTicketButtonClick();
        webDriver.findElement(NUMBER_OF_TICKETS_DROPDOWN).click();
        webDriver.findElement(SIX_TIME_TRAINING).click();
    }

    public void checkTrainingUltraTicketPricePerTraining() {
        buyTicketButtonClick();
        webDriver.findElement(NUMBER_OF_TICKETS_DROPDOWN).click();
        webDriver.findElement(TEN_TIME_TRAINING).click();
    }

    public void applyOnTraining() {
        List<WebElement> trainings = webDriver.findElements(TRAINING_LIST);

        for (WebElement training : trainings) {
            List<WebElement> trainingDetails = training.findElements(APPLY_BUTTON);
            for (WebElement apply : trainingDetails) {
                if (apply.getText().toUpperCase().contains("JELENTKEZEM!")) {
                    apply.click();
                    break;
                }
            }
        }
    }

    public void deleteTraining() {
        List<WebElement> trainings = webDriver.findElements(TRAINING_LIST);

        for (WebElement training : trainings) {
            List<WebElement> trainingDetails = training.findElements(APPLY_BUTTON);
            for (WebElement apply : trainingDetails) {
                if (apply.getText().toUpperCase().contains("LEMONDÁS")) {
                    apply.click();
                    break;
                }
            }
        }
    }


}
