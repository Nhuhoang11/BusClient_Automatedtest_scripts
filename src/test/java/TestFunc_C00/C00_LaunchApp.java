package TestFunc_C00;

import Func.C00.LaunchApp;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import utils.ElementXPath;

public class C00_LaunchApp {
    @Test
    public void launchApp() {
        AppiumDriver<MobileElement> appiumDriver = LaunchApp.getAppiumDriver();

        // Verify
        MobileElement signInBtn = appiumDriver.findElementByAccessibilityId(ElementXPath.signInBtnId);
        MobileElement signUpBtn = appiumDriver.findElementByAccessibilityId(ElementXPath.signUpTxtId);
        WebDriverWait wait = new WebDriverWait(appiumDriver, 10);
        wait.until(ExpectedConditions.visibilityOf(signInBtn));
        wait.until(ExpectedConditions.visibilityOf(signUpBtn));
        System.out.println("Launching app success!");
    }
}
