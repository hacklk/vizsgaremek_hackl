package pageClasses;

import org.openqa.selenium.WebDriver;
import java.util.ArrayList;

public class GDPR {
    WebDriver webDriver;

    public GDPR(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void windowChange() {
        ArrayList<String> tabTwo = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabTwo.get(1));
        webDriver.close();
        webDriver.switchTo().window(tabTwo.get(0)); // ez miert kell?
    }
}


