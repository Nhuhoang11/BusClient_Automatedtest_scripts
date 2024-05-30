package Drafts;

import Action.ScrollDown90Percent;
import Func.C00.LaunchApp;
import Func.C01.SignUpForm;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.ElementXPath;
import utils.LaunchInstAddr;

import java.io.IOException;
import java.time.Duration;

public class C01_Register_ValidCreds_InstAddApp {
    AppiumDriver<MobileElement> appiumDriver = null;
    private WebDriverWait wait;
    private SignUpForm signUpForm;
    private LaunchInstAddr launchInstAddr;
    private ScrollDown90Percent scrollDown;

    @BeforeClass
    public void setup() {
        appiumDriver = LaunchApp.getAppiumDriver();
        wait = new WebDriverWait(appiumDriver, 10);
        signUpForm = new SignUpForm(appiumDriver);
        signUpForm.clickSignUpText();
        launchInstAddr = new LaunchInstAddr(appiumDriver);
    }

    @Test
    public void inputValidCreds() throws IOException {
        launchInstAddr.launchInstAdd();
        // Get Email
        String email = launchInstAddr.getEmail();
        System.out.println("Email: " + email);

        // Relaunch app BusClient
        appiumDriver.activateApp("com.metadata.busnoti_client");
        MobileElement alreadyHaveAnAcc = appiumDriver.findElementByXPath("//android.widget.TextView[contains(@text, 'Already have an account?')]");
        wait.until(ExpectedConditions.visibilityOf(alreadyHaveAnAcc));

        // Input credentials
        signUpForm.inputName("Client test");
        System.out.println("Input name");
        signUpForm.inputEmail(email);
        System.out.println("Input email");
        signUpForm.inputPassword(ElementXPath.validPassword);
        System.out.println("Input password");
        signUpForm.inputRepeatPassword(ElementXPath.validPassword);
        System.out.println("Input repeat password");
        System.out.println("Click on submit");
        signUpForm.clickSubmitButton();

        // Verify OTP screen hiển thị sau khi users nhập các thông tin chính xác:
        if (signUpForm.isOTPScreenDisplaying()) {
            System.out.println("PASS - OTP screen is displaying!");
        } else {
            System.out.println("FAIL - Cannot register even if input valid credentials");
        }
        System.out.println("---------------------------------------------------------------------------");

        // Relaunch InstAddr app:
        appiumDriver.runAppInBackground(Duration.ofSeconds(3));
        appiumDriver.activateApp("air.kukulive.mailnow");
        // Get OTP
        String otp = launchInstAddr.getNewOTP();
        System.out.println("OTP: " + otp);

        // Relaunch app BusClient
        appiumDriver.activateApp("com.metadata.busnoti_client");
        // Input OTP & Submit OTP
        signUpForm.inputOTPandSubmit(otp);

        //Verify successfully register
        signUpForm.verifyRegisterSuccess();
        if (signUpForm.verifyRegisterSuccess()) {
            System.out.println("PASS - Successfully register!");
        } else {
            System.out.println("FAIL - Register failed even if input valid credentials!");
        }
    }
}
