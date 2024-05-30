package TF_C02_SignIn;

import Func.C00.LaunchApp;
import Func.C02.LogoutForm;
import Func.C02.SignInForm;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import utils.ElementXPath;

public class C02_SignIn_CorrectCredsTest {
    AppiumDriver<MobileElement> appiumDriver = null;
    private SignInForm signInForm;
    private LogoutForm logoutForm;

    @BeforeClass
    public void setup() {
        appiumDriver = LaunchApp.getAppiumDriver();
        WebDriverWait wait = new WebDriverWait(appiumDriver, 10);
        signInForm = new SignInForm(appiumDriver);
        signInForm.clickSignInButton();
    }

    @Test
    public void signInWithCorrectEmailPassword() {
        signInForm.inputEmail(ElementXPath.validEmail);
        signInForm.inputPassword(ElementXPath.validPassword);
        signInForm.clickSubmitButton();

        // Verify sign in success
        if (signInForm.isSignInSuccess()) {
            System.out.println("PASS - Success Login!");
        } else {
            System.out.println("FAIL - Login failed!");
        }
//        try {
//            MobileElement HomeIcon = appiumDriver.findElementByAccessibilityId("Home");
//            wait.until(ExpectedConditions.visibilityOf(HomeIcon));
//            if (HomeIcon.isDisplayed()) {
//                System.out.println("PASS - Login success!");
//            } else {
//                System.out.println("FAIL - Login failed!");
//            }
//        } catch (WebDriverException e) {
//            //
//        }

    }

    @AfterMethod
    public void logout() {
        logoutForm = new LogoutForm(appiumDriver);
        logoutForm.logout();
    }
}
