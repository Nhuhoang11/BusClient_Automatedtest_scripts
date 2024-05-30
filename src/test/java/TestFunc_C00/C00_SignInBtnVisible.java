package TestFunc_C00;

import Func.C00.LaunchApp;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import utils.ElementXPath;

public class C00_SignInBtnVisible {
    @Test
    public void signInBtnVisible() {
        AppiumDriver<MobileElement> appiumDriver = LaunchApp.getAppiumDriver();

        MobileElement signInBtn = appiumDriver.findElementByAccessibilityId(ElementXPath.signInBtnId);
        WebDriverWait wait = new WebDriverWait(appiumDriver, 10);
        wait.until(ExpectedConditions.visibilityOf(signInBtn));
        signInBtn.click();
        MobileElement emailTxtBox = appiumDriver.findElementByXPath(ElementXPath.emailTextBoxXpath);
        wait.until(ExpectedConditions.visibilityOf(emailTxtBox));

        System.out.println("PASS - Sign in button is visible!");

    }
}
