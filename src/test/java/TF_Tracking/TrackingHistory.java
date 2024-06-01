package TF_Tracking;

import DB.Parent_GetTracking_Child_History;
import Func.C00.LaunchApp;
import Func.C02.SignInForm;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.List;


public class TrackingHistory {
    private AppiumDriver<MobileElement> appiumDriver;
    private WebDriverWait wait;
    private SignInForm signInForm;
    private String checkAccount = "1obmd@fthcapital.com";
    private String passwordAcc = "@Test123";

    @BeforeClass
    public void setup() {
        appiumDriver = LaunchApp.getAppiumDriver();
        wait = new WebDriverWait(appiumDriver, 10);
        signInForm = new SignInForm(appiumDriver);
        // Sign in
        signInForm.clickSignInButton();
        signInForm.inputEmail(checkAccount);
        signInForm.inputPassword(passwordAcc);
        signInForm.clickSubmitButton();
        // Click on TRACKING on navigation bar
        MobileElement trackingNav = appiumDriver.findElementByAccessibilityId("Tracking");
        trackingNav.click();
    }

    @Test
    public void TC001() {
        String email = checkAccount;
        int count = Parent_GetTracking_Child_History.countTrackingOfChild(email);
        System.out.println("Total number of history IDs: " + count);

        String xpathExpression = "//android.widget.TextView[contains(@text, ',')]";
        By countTrackingOnDeviceSel = By.xpath(xpathExpression);
        List<MobileElement> countTrackingOnDevice = appiumDriver.findElements(countTrackingOnDeviceSel);
        int quanityTracking = countTrackingOnDevice.size();
        System.out.println("Số lượng tracking trên device: " + quanityTracking);
        if (count == quanityTracking) {
            System.out.println("PASS - Số lượng tracking trên device và database khớp nhau!");
        } else {
            System.out.println("FAIL - Số lượng tracking trên device và database không khớp nhau!");
        }
    }

}
