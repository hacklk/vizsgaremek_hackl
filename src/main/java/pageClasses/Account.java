package pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class Account {
    WebDriver webDriver;

    public Account(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    //user personal elements
    private final By INPUT_FIRSTNAME = By.id("firstName");
    private final By INPUT_SURNAME = By.id("surName");
    private final By INPUT_NICKNAME = By.id("nickName");

    //user address elements
    private final By INPUT_FULLNAME = By.xpath("//*[@id=\"mat-input-9\"]");
    private final By INPUT_COUNTRY = By.xpath("//*[@id=\"mat-input-10\"]");
    private final By INPUT_PO_CODE = By.xpath("//*[@id=\"mat-input-11\"]");
    private final By INPUT_CITY = By.xpath("//*[@id=\"mat-input-12\"]");
    private final By INPUT_ADDRESS = By.xpath("//*[@id=\"mat-input-13\"]");

    //user change password elements
    private final By INPUT_PASSWORD = By.id("oldPassword");
    private final By INPUT_NEW_PASSWORD = By.id("password");
    private final By INPUT_CONFIRM_PASSWORD = By.id("confirmPassword");

    public void uploadPersonalDetails(String firstname, String surname, String nickname){
        webDriver.findElement(INPUT_FIRSTNAME).clear();
        webDriver.findElement(INPUT_SURNAME).clear();
        webDriver.findElement(INPUT_NICKNAME).clear();
        webDriver.findElement(INPUT_FIRSTNAME).sendKeys(firstname);
        webDriver.findElement(INPUT_SURNAME).sendKeys(surname);
        webDriver.findElement(INPUT_NICKNAME).sendKeys(nickname, Keys.ENTER);
    }

    public void changePassword(String password, String newpassword, String confirmpassword) {
        webDriver.findElement(INPUT_PASSWORD).sendKeys(password);
        webDriver.findElement(INPUT_NEW_PASSWORD).sendKeys(newpassword);
        webDriver.findElement(INPUT_CONFIRM_PASSWORD).sendKeys(confirmpassword, Keys.ENTER);
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void uploadBillingDetails(String name, String country, String postalcode, String city, String address) {
        webDriver.findElement(INPUT_FULLNAME).clear();
        webDriver.findElement(INPUT_COUNTRY).clear();
        webDriver.findElement(INPUT_PO_CODE).clear();
        webDriver.findElement(INPUT_CITY).clear();
        webDriver.findElement(INPUT_ADDRESS).clear();
        webDriver.findElement(INPUT_FULLNAME).sendKeys(name);
        webDriver.findElement(INPUT_COUNTRY).sendKeys(country);
        webDriver.findElement(INPUT_PO_CODE).sendKeys(postalcode);
        webDriver.findElement(INPUT_CITY).sendKeys(city);
        webDriver.findElement(INPUT_ADDRESS).sendKeys(address, Keys.ENTER);
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteBillingDetails(String name) {
        webDriver.findElement(INPUT_FULLNAME).clear();
        webDriver.findElement(INPUT_COUNTRY).clear();
        webDriver.findElement(INPUT_PO_CODE).clear();
        webDriver.findElement(INPUT_CITY).clear();
        webDriver.findElement(INPUT_ADDRESS).clear();
        webDriver.findElement(INPUT_FULLNAME).sendKeys(name, Keys.ENTER);
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
