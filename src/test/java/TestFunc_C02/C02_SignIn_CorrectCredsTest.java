package TestFunc_C02;

import Func.C00.LaunchApp;
import Func.C02.SignInForm;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utils.ElementXPath;

public class C02_SignIn_CorrectCredsTest {
    AppiumDriver<MobileElement> appiumDriver = null;
    private SignInForm signInForm;

    @BeforeSuite
    public void setup() {
        appiumDriver = LaunchApp.getAppiumDriver();
        signInForm = new SignInForm(appiumDriver);
        signInForm.clickSignInButton();
    }

    @Test
    public void signInWithCorrectEmailPassword() {
        signInForm.inputEmail(ElementXPath.validEmail);
        signInForm.inputPassword(ElementXPath.validPassword);
        signInForm.clickSubmitButton();

        WebDriverWait wait = new WebDriverWait(appiumDriver, 10);
        try {
            MobileElement HomeIcon = appiumDriver.findElementByAccessibilityId("Home");
            wait.until(ExpectedConditions.visibilityOf(HomeIcon));
            if (HomeIcon.isDisplayed()) {
                System.out.println("PASS - Login success!");
            } else {
                System.out.println("FAIL - Login failed!");
            }
        } catch (WebDriverException e) {
            //
        }

    }
}
