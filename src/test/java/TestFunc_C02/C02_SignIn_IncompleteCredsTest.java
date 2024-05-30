package TestFunc_C02;

import Func.C00.LaunchApp;
import Func.C02.SignInForm;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utils.ElementXPath;

public class C02_SignIn_IncompleteCredsTest {
    AppiumDriver<MobileElement> appiumDriver = null;
    private SignInForm signInForm;

    @BeforeSuite
    public void setup() {
        appiumDriver = LaunchApp.getAppiumDriver();
        signInForm = new SignInForm(appiumDriver);
        signInForm.clickSignInButton();
    }

    @Test
    // Click without input email and password
    public void TC001_LoginWithoutInputEmailPassword() {
        signInForm.clickSubmitButton();
        signInForm.isAnyHelperTextDisplayed();

        if (signInForm.isSignInScreedDisplayed()) {
            System.out.println("PASS - Cannot login without input Email and password!");
        } else {
            System.out.println("FAIL - Error messages are not displayed correctly.");
        }
    }

    //
    @Test
    public void TC002_LoginWithoutInputEmail() {
        // Chỉ nhập password, để trống email
        signInForm.clearEmail();
        signInForm.inputPassword(ElementXPath.validPassword);

        signInForm.clickSubmitButton();

        if (signInForm.isSignInScreedDisplayed()) {
            System.out.println("PASS - Cannot login without input Email!");
        } else {
            System.out.println("FAIL - Error messages are not displayed correctly.");
        }
    }

    //
    @Test
    public void TC003_LoginWithoutInputPassword() {
        signInForm.clearSignInForm();
        // Chỉ nhập email, để trống password
        signInForm.inputEmail(ElementXPath.validEmail);
        signInForm.clearPassword();

        signInForm.clickSubmitButton();
        if (signInForm.isSignInScreedDisplayed()) {
            System.out.println("PASS - Cannot login without input Password!");
        } else {
            System.out.println("FAIL - Error messages are not displayed correctly.");
        }
    }

    //
    @Test
    public void TC004_LoginWithIncorrectEmailPassword() {
        signInForm.clearSignInForm();
        signInForm.inputEmail("incorrect@gmail.com");
        signInForm.inputPassword("Test1234@");

        signInForm.clickSubmitButton();
        if (signInForm.isSignInScreedDisplayed()) {
            System.out.println("PASS - Cannot login with invalid Email or password!");
        } else {
            System.out.println("FAIL - Error messages are not displayed correctly.");
        }
    }
}
