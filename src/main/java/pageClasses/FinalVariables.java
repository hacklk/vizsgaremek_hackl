package pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public enum FinalVariables {

    INPUT, BUTTON;

//    FinalVariables() {
//    }

    //    ACCOUNT
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

    //  LANDING VARIABLES
    private final By HAMBURGER_BUTTON = By.xpath("//mat-icon");
    private final By LOGIN_BUTTON_LANDING = By.xpath("//mat-toolbar-row/div/div/span");
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
    private final By LOGOUT_ICON = By.xpath("//mat-icon[2]");
    private final By MY_TICKETS_MENU = By.xpath("//mat-nav-list/a[3]/div/span/div[1]");
    private final By BUY_TICKET_BUTTON = By.xpath("//mat-toolbar-row[1]/div/div/button");
    private final By SIX_TIME_TRAINING = By.xpath("//*[@id=\"mat-option-0\"]/span");
    private final By TEN_TIME_TRAINING = By.xpath("//*[@id=\"mat-option-1\"]/span");
    private final By FOURTEEN_TIME_TRAINING = By.xpath("//*[@id=\"mat-option-2\"]/span");
    private final By NUMBER_OF_TICKETS_DROPDOWN = By.xpath("//*[contains(@role,'listbox')]");
    private final By APPLY_BUTTON = By.xpath("//mat-card/button[2]");

    private final By TRAINER_BUTTON = By.xpath("//mat-nav-list/a[2]/div/span/div[1]");

//    LOGIN LOGOUT
    private final By EMAIL_INPUT = By.id("emailOrUserName");
    private final By PASSWORD_INPUT = By.id("password");
    private final By LOGIN_BUTTON = By.xpath("//div/button[1]");

//    REGISTRATION
//    private final By EMAIL_INPUT = By.id("email");
//    private final By PASSWORD_INPUT = By.id("password");
    private final By CONFIRM_PASSWORD_INPUT = By.id("confirmPassword");
    private final By TERMS_CONDTS_CLICKBOX = By.xpath("//*[@id=\"mat-checkbox-1\"]/label/div");
    private final By PRIVACY_POLICY_CLICKBOX = By.xpath("//*[@id=\"mat-checkbox-2\"]/label/div");
    private final By REGISTER_BUTTON = By.xpath("//div/button[1]");

//    PERSONAL TRAINER
    private final By TRAINER_SEARCH_INPUT_FIELD = By.id("mat-input-2");
    private final By TRAINER_INFO_BUTTON = By.xpath("//mat-card-actions/button");
}
