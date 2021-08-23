package pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginLogout {

    WebDriver webDriver;

    public LoginLogout(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private final By EMAIL_INPUT = By.id("emailOrUserName");
    private final By PASSWORD_INPUT = By.id("password");
    private final By LOGIN_BUTTON = By.xpath("//div/button[1]");
    private final By REGISTRATION_BUTTON = By.xpath("//app-login-form/div/form/div/button[2]");

    public void registrationButtonClick(){
        webDriver.findElement(REGISTRATION_BUTTON).click();
        new Registration(webDriver);
    }


    public void userLoginNoEmail(String password){
        webDriver.findElement(EMAIL_INPUT).sendKeys("");
        webDriver.findElement(PASSWORD_INPUT).sendKeys(password);
        webDriver.findElement(LOGIN_BUTTON).click();
    }

    public void userLoginNoPassword(String email){
        webDriver.findElement(EMAIL_INPUT).sendKeys(email);
        webDriver.findElement(PASSWORD_INPUT).sendKeys("");
        webDriver.findElement(LOGIN_BUTTON).click();
    }

    public void userLoginWrongPassword(String email, String wrongPassword){
        webDriver.findElement(EMAIL_INPUT).sendKeys(email);
        webDriver.findElement(PASSWORD_INPUT).sendKeys(wrongPassword);
        webDriver.findElement(LOGIN_BUTTON).click();
    }

    public void userLogin(String email, String password){
        webDriver.findElement(EMAIL_INPUT).sendKeys(email);
        webDriver.findElement(PASSWORD_INPUT).sendKeys(password);
        webDriver.findElement(LOGIN_BUTTON).click();
        try {
            Thread.sleep(1800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        new CalendarPage(webDriver);
    }
}
