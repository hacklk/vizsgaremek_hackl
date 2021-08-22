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
    private final By USER_FIRSTNAME_INPUT = By.id("firstName");
    private final By USER_SURNAME_INPUT = By.id("surName");
    private final By USER_NICKNAME_INPUT = By.id("nickName");
    private final By NEWSLETTER_CLICKBOX = By.xpath("//*[@id=\"mat-checkbox-1\"]/label/div");
    private final By SAVE_USER_PERSONAL_DETAILS_BUTTON = By.xpath("//div/form/div/div/button");

    //user address elements
    private final By USER_FULLNAME_INPUT = By.xpath("//*[@id=\"mat-input-9\"]");
    private final By USER_COUNTRY_INPUT = By.xpath("//*[@id=\"mat-input-10\"]");
    private final By USER_POSTAL_CODE_INPUT = By.xpath("//*[@id=\"mat-input-11\"]");
    private final By USER_CITY_INPUT = By.xpath("//*[@id=\"mat-input-12\"]");
    private final By USER_ADDRESS_INPUT = By.xpath("//*[@id=\"mat-input-13\"]");
    private final By SAVE_USER_BILLING_DETAILS_BUTTON = By.xpath("//app-spinner-button/button/span");

    //user change password elements
    private final By USER_PASSWORD_INPUT = By.id("oldPassword");
    private final By USER_NEW_PASSWORD_INPUT = By.id("password");
    private final By USER_CONFIRM_PASSWORD_INPUT = By.id("confirmPassword");
    private final By SAVE_NEW_PASSWORD_BUTTON = By.xpath("//div[3]/change-password/form/div/div/button");


    public void uploadPersonalDetails(String firstname, String surname, String nickname){
        webDriver.findElement(USER_FIRSTNAME_INPUT).clear();
        webDriver.findElement(USER_SURNAME_INPUT).clear();
        webDriver.findElement(USER_NICKNAME_INPUT).clear();
        webDriver.findElement(USER_FIRSTNAME_INPUT).sendKeys(firstname);
        webDriver.findElement(USER_SURNAME_INPUT).sendKeys(surname);
        webDriver.findElement(USER_NICKNAME_INPUT).sendKeys(nickname, Keys.ENTER);

    }

    public void changePassword(String password, String newpassword, String confirmpassword) {
        webDriver.findElement(USER_PASSWORD_INPUT).sendKeys(password);
        webDriver.findElement(USER_NEW_PASSWORD_INPUT).sendKeys(newpassword);
        webDriver.findElement(USER_CONFIRM_PASSWORD_INPUT).sendKeys(confirmpassword, Keys.ENTER);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void uploadBillingDetails(String name, String country, String postalcode, String city, String address) {
        webDriver.findElement(USER_FULLNAME_INPUT).clear();
        webDriver.findElement(USER_COUNTRY_INPUT).clear();
        webDriver.findElement(USER_POSTAL_CODE_INPUT).clear();
        webDriver.findElement(USER_CITY_INPUT).clear();
        webDriver.findElement(USER_ADDRESS_INPUT).clear();
        webDriver.findElement(USER_FULLNAME_INPUT).sendKeys(name);
        webDriver.findElement(USER_COUNTRY_INPUT).sendKeys(country);
        webDriver.findElement(USER_POSTAL_CODE_INPUT).sendKeys(postalcode);
        webDriver.findElement(USER_CITY_INPUT).sendKeys(city);
        webDriver.findElement(USER_ADDRESS_INPUT).sendKeys(address, Keys.ENTER);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void deleteBillingDetails(String name) {
        webDriver.findElement(USER_FULLNAME_INPUT).clear();
        webDriver.findElement(USER_COUNTRY_INPUT).clear();
        webDriver.findElement(USER_POSTAL_CODE_INPUT).clear();
        webDriver.findElement(USER_CITY_INPUT).clear();
        webDriver.findElement(USER_ADDRESS_INPUT).clear();
        webDriver.findElement(USER_FULLNAME_INPUT).sendKeys(name, Keys.ENTER);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
