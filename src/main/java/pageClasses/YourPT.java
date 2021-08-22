package pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class YourPT {
    WebDriver webDriver;

    public YourPT(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private final By TRAINER_SEARCH_INPUT_FIELD = By.id("mat-input-2");
    private final By TRAINER_INFO_BUTTON = By.xpath("//mat-card-actions/button");

    public void searchForTrainer(String name){
        webDriver.findElement(TRAINER_SEARCH_INPUT_FIELD).sendKeys(name, Keys.ENTER);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getTrainerAllDetails(String name){
        webDriver.findElement(TRAINER_SEARCH_INPUT_FIELD).sendKeys(name, Keys.ENTER);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        webDriver.findElement(TRAINER_INFO_BUTTON).click();
    }
}
