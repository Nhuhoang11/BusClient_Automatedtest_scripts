package TestFunc_C01_Register.Success;

import Func.C00.LaunchApp;
import Func.C01.SignUpForm;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.ElementXPath;
import utils.LaunchInstAddr_ComparewithExistingEMail;

import java.io.IOException;
import java.time.Duration;

public class C01_Register_ValidCreds {
    AppiumDriver<MobileElement> appiumDriver = null;
    private LaunchInstAddr_ComparewithExistingEMail launchInstAddrComparewithExistingEMail;
    private WebDriverWait wait;
    private SignUpForm signUpForm;

    @BeforeClass
    public void setup() {
        appiumDriver = LaunchApp.getAppiumDriver();
        wait = new WebDriverWait(appiumDriver, 10);
        signUpForm = new SignUpForm(appiumDriver);
        signUpForm.clickSignUpText();
        launchInstAddrComparewithExistingEMail = new LaunchInstAddr_ComparewithExistingEMail(appiumDriver);
    }

    @Test
    // Valid credentials
    public void TC001_inputValidCreds() throws IOException {
        // Open InstAddr app
        launchInstAddrComparewithExistingEMail.launchInstAddress();
        // Get email
        String email = launchInstAddrComparewithExistingEMail.getEmail();
        System.out.println("Email: " + email);

        // Relaunch BusClient
        appiumDriver.activateApp("com.metadata.busnoti_client");
        // Input credentials & Submit
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
        String OTP = launchInstAddrComparewithExistingEMail.getNewOTP();

        // Relaunch BusClient
        appiumDriver.activateApp("com.metadata.busnoti_client");
        // Input OTP & Submit
        signUpForm.inputOTPandSubmit(OTP);

        //Verify successfully register
        signUpForm.verifyRegisterSuccess();
        if (signUpForm.verifyRegisterSuccess()) {
            System.out.println("PASS - Successfully register!");
        } else {
            System.out.println("FAIL - Register failed even if input valid credentials!");
        }
    }
}
