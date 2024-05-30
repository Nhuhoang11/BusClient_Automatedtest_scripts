package TestFunc_C00;

import Func.C00.LaunchApp;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import utils.ElementXPath;

public class C00_SignUpVisible {
    @Test
    public void signUpLinkVisible() {
        AppiumDriver<MobileElement> appiumDriver = LaunchApp.getAppiumDriver();

        MobileElement signUpBtn = appiumDriver.findElementByAccessibilityId(ElementXPath.signUpTxtId);
        WebDriverWait wait = new WebDriverWait(appiumDriver, 10);
        wait.until(ExpectedConditions.visibilityOf(signUpBtn));
        signUpBtn.click();

        MobileElement verifyTxt = appiumDriver.findElementByXPath("//android.widget.TextView[contains(@text, 'Already have an account?')]");
        wait.until(ExpectedConditions.visibilityOf(verifyTxt));

        System.out.println("PASS - Sign up link is visible!");
    }
}
