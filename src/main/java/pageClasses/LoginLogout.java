package pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginLogout {

    WebDriver webDriver;

    public LoginLogout(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private final By INPUT_EMAIL = By.id("emailOrUserName");
    private final By INPUT_PASSWORD = By.id("password");
    private final By LOGIN_BUTTON = By.xpath("//div/button[1]");
    private final By REGISTRATION_BUTTON = By.xpath("//app-login-form/div/form/div/button[2]");

    public void registrationButtonClick(){
        webDriver.findElement(REGISTRATION_BUTTON).click();
        new Registration(webDriver);
    }


    public void userLoginNoEmail(String password){
        webDriver.findElement(INPUT_EMAIL).sendKeys("");
        webDriver.findElement(INPUT_PASSWORD).sendKeys(password);
        webDriver.findElement(LOGIN_BUTTON).click();
    }

    public void userLoginNoPassword(String email){
        webDriver.findElement(INPUT_EMAIL).sendKeys(email);
        webDriver.findElement(INPUT_PASSWORD).sendKeys("");
        webDriver.findElement(LOGIN_BUTTON).click();
    }

    public void userLoginWrongPassword(String email, String wrongPassword){
        webDriver.findElement(INPUT_EMAIL).sendKeys(email);
        webDriver.findElement(INPUT_PASSWORD).sendKeys(wrongPassword);
        webDriver.findElement(LOGIN_BUTTON).click();
    }

    public void userLogin(String email, String password){
        webDriver.findElement(INPUT_EMAIL).sendKeys(email);
        webDriver.findElement(INPUT_PASSWORD).sendKeys(password);
        webDriver.findElement(LOGIN_BUTTON).click();
        try {
            Thread.sleep(1800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new LandingCalendar(webDriver);
    }
}
