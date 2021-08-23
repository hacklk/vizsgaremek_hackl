package pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Registration {

    WebDriver webDriver;

    public Registration(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    private final By INPUT_EMAIL = By.id("email");
    private final By INPUT_PASSWORD = By.id("password");
    private final By INPUT_CONFIRM_PASSWORD = By.id("confirmPassword");
    private final By TERMS_CLICKBOX = By.xpath("//*[@id=\"mat-checkbox-1\"]/label/div");
    private final By PRIVACY_POLICY_CLICKBOX = By.xpath("//*[@id=\"mat-checkbox-2\"]/label/div");
    private final By REGISTER_BUTTON = By.xpath("//div/button[1]");

    public void noEmailRegistration(String password){
        webDriver.findElement(INPUT_EMAIL).sendKeys("");
        webDriver.findElement(INPUT_PASSWORD).sendKeys(password);
        webDriver.findElement(INPUT_CONFIRM_PASSWORD).sendKeys(password);
        webDriver.findElement(TERMS_CLICKBOX).click();
        webDriver.findElement(PRIVACY_POLICY_CLICKBOX).click();
        webDriver.findElement(REGISTER_BUTTON).click();
    }

    public void noPasswordRegistration(String email){
        webDriver.findElement(INPUT_EMAIL).sendKeys(email);
        webDriver.findElement(INPUT_PASSWORD).sendKeys("");
        webDriver.findElement(INPUT_CONFIRM_PASSWORD).sendKeys("");
        webDriver.findElement(TERMS_CLICKBOX).click();
        webDriver.findElement(PRIVACY_POLICY_CLICKBOX).click();
        webDriver.findElement(REGISTER_BUTTON).click();
    }

    public void notValidPasswordRegistration(String email, String notValidPassword){
        webDriver.findElement(INPUT_EMAIL).sendKeys(email);
        webDriver.findElement(INPUT_PASSWORD).sendKeys(notValidPassword);
        webDriver.findElement(INPUT_CONFIRM_PASSWORD).sendKeys(notValidPassword);
        webDriver.findElement(TERMS_CLICKBOX).click();
        webDriver.findElement(PRIVACY_POLICY_CLICKBOX).click();
        webDriver.findElement(REGISTER_BUTTON).click();
    }

    public void notMatchPasswordRegistration(String email, String password, String notMatchPassword){
        webDriver.findElement(INPUT_EMAIL).sendKeys(email);
        webDriver.findElement(INPUT_PASSWORD).sendKeys(password);
        webDriver.findElement(INPUT_CONFIRM_PASSWORD).sendKeys(notMatchPassword);
        webDriver.findElement(TERMS_CLICKBOX).click();
        webDriver.findElement(PRIVACY_POLICY_CLICKBOX).click();
        webDriver.findElement(REGISTER_BUTTON).click();
    }

    public void noClickTermsCondtsRegistration(String email, String password){
        webDriver.findElement(INPUT_EMAIL).sendKeys(email);
        webDriver.findElement(INPUT_PASSWORD).sendKeys(password);
        webDriver.findElement(INPUT_CONFIRM_PASSWORD).sendKeys(password);
        webDriver.findElement(PRIVACY_POLICY_CLICKBOX).click();
        webDriver.findElement(REGISTER_BUTTON).click();
    }

    public void noClickPrivacyPolicyRegistration(String email, String password){
        webDriver.findElement(INPUT_EMAIL).sendKeys(email);
        webDriver.findElement(INPUT_PASSWORD).sendKeys(password);
        webDriver.findElement(INPUT_CONFIRM_PASSWORD).sendKeys(password);
        webDriver.findElement(TERMS_CLICKBOX).click();
        webDriver.findElement(REGISTER_BUTTON).click();
    }

    public void registration(String email, String password){
        webDriver.findElement(INPUT_EMAIL).sendKeys(email);
        webDriver.findElement(INPUT_PASSWORD).sendKeys(password);
        webDriver.findElement(INPUT_CONFIRM_PASSWORD).sendKeys(password);
        webDriver.findElement(TERMS_CLICKBOX).click();
        webDriver.findElement(PRIVACY_POLICY_CLICKBOX).click();
        webDriver.findElement(REGISTER_BUTTON).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
