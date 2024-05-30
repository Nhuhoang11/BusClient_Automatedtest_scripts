package Func.C02;

import Action.ScrollDown90Percent;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutForm {
    AppiumDriver<MobileElement> appiumDriver;
    private WebDriverWait wait;
    private ScrollDown90Percent scrollDown;

    public LogoutForm(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void logout() {
        MobileElement profileNavBar = appiumDriver.findElementByAccessibilityId("Profile");
        profileNavBar.click();

        try {
            Thread.sleep(3000);
            scrollDown = new ScrollDown90Percent(appiumDriver);
            scrollDown.scrollDownTo();
            MobileElement logout = appiumDriver.findElementByXPath("//android.widget.TextView[@text = 'Logout']");
            logout.click();
        } catch (Exception e) {
            //
        }
    }
}
