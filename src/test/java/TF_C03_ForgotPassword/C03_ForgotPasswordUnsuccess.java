package TF_C03_ForgotPassword;

import Func.C00.LaunchApp;
import Func.C01.SignUpForm;
import Func.C02.SignInForm;
import Func.C03_ForgotPassword.ForgotPasswordForm;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.ElementXPath;
import utils.LaunchGmail;

public class C03_ForgotPasswordUnsuccess {
    AppiumDriver<MobileElement> appiumDriver = null;
    private SignInForm signInForm;
    private LaunchGmail launchGmail;
    private WebDriverWait wait;
    private SignUpForm signUpForm; // Get method input OTP
    private String newOTP;
    private ForgotPasswordForm forgotPasswordForm;
    By submitResetPasswordBtnSel = MobileBy.AccessibilityId("SUBMIT");
    private String newValidPassword = "@Test123@";
    private String newInvalidPassword = "@Test12";

    @BeforeClass
    public void setup() {
        appiumDriver = LaunchApp.getAppiumDriver();
        wait = new WebDriverWait(appiumDriver, 10);
        signInForm = new SignInForm(appiumDriver);
        signInForm.clickSignInButton();
        forgotPasswordForm = new ForgotPasswordForm(appiumDriver);
        // Click on 'Forgot password'
        MobileElement forgotPassword = appiumDriver.findElementByAccessibilityId(ElementXPath.forgotPasswordId);
        forgotPassword.click();
        // Input email
        MobileElement inputEmail = appiumDriver.findElementByClassName("android.widget.EditText");
        inputEmail.sendKeys("nhu.hoanglu@gmail.com");
        // Click on Submit
        MobileElement submitBtn = appiumDriver.findElementByXPath("//android.widget.TextView[@text = 'SUBMIT']");
        submitBtn.click();
        // Get OTP from Gmail
        launchGmail = new LaunchGmail(appiumDriver);
        launchGmail.openGmail();
        newOTP = launchGmail.getnewOTP();
        System.out.println(newOTP);
    }

    @Test
    // Submit without input all fields
    public void TC001_ResetPasswordWithoutInputCreds() {
        // Relaunch BusClient
        appiumDriver.activateApp("com.metadata.busnoti_client");
        // Click submit
        forgotPasswordForm.submitReset();
        // Verify unsuccess submit
        MobileElement submitBtn = appiumDriver.findElement(submitResetPasswordBtnSel);
        if (submitBtn.isDisplayed()) {
            System.out.println("PASS - Cannot submit new password without input OTP - Password - Repeat Password!");
        } else {
            System.out.println("FAIL - The user can successfully reset password even if left all fields empty!");
        }
    }

    @Test
    // Submit with input OTP & left Password and Repeat password empty
    public void TC002_ResetPasswordWithoutInputPasswordAndRePassword() {
        // Input OTP
        forgotPasswordForm.submitReset();
        // Click submit
        forgotPasswordForm.submitReset();
        // Verify unsuccess submit
        MobileElement submitBtn = appiumDriver.findElement(submitResetPasswordBtnSel);
        if (submitBtn.isDisplayed()) {
            System.out.println("PASS - Cannot submit new password without input Password - Repeat Password!");
        } else {
            System.out.println("FAIL - The user can successfully reset password even if left Password and Repeat password fields empty!");
        }
    }

    @Test
    // Submit without input OTP; Input Password & Repeat password
    public void TC003_ResetPasswordWithoutInputOTP() {
        forgotPasswordForm.clear();
        // Left OTP field empty
        // Input Password and Repeat password
        forgotPasswordForm.inputNewPassword(newValidPassword);
        forgotPasswordForm.inputRepeatNewPassword(newValidPassword);
        // Click submit
        forgotPasswordForm.submitReset();
        // Close error message
        MobileElement closeErrorBtn = appiumDriver.findElementByXPath("//android.widget.TextView[@text = 'OK']");
        closeErrorBtn.click();
        // Verify unsuccess submit
        MobileElement submitBtn = appiumDriver.findElement(submitResetPasswordBtnSel);
        if (submitBtn.isDisplayed()) {
            System.out.println("PASS - Cannot submit new password without input Password - Repeat Password!");
        } else {
            System.out.println("FAIL - The user can successfully reset password even if left Password and Repeat password fields empty!");
        }
    }

    @Test
    // Submit with incorrect OTP - Valid password and Repeat password
    public void TC004_ResetPasswordWithIncorrectOTP() {
        forgotPasswordForm.clear();
        // Input incorrect OTP
        forgotPasswordForm.inputOTP("123456");
        // Input Password and Repeat password
        forgotPasswordForm.inputNewPassword(newValidPassword);
        forgotPasswordForm.inputRepeatNewPassword(newValidPassword);
        // Click submit
        forgotPasswordForm.submitReset();
        // Verify unsuccess submit
        MobileElement submitBtn = appiumDriver.findElement(submitResetPasswordBtnSel);
        if (submitBtn.isDisplayed()) {
            System.out.println("PASS - Cannot submit new password with incorrect OTP!");
        } else {
            System.out.println("FAIL - The user can successfully reset password even if input incorrect OTP!");
        }
    }

    @Test
    // Submit with Correct OTP - Password and Repeat password do not match
    public void TC005_ResetPasswordWithPasswordFieldsNotMatch() {
        forgotPasswordForm.clear();
        // Input correct OTP
        forgotPasswordForm.inputOTP(newOTP);
        // Input Password and Repeat password
        forgotPasswordForm.inputNewPassword(newValidPassword);
        forgotPasswordForm.inputRepeatNewPassword(newValidPassword + "@");
        // Click submit
        forgotPasswordForm.submitReset();
        // Verify unsuccess submit
        MobileElement submitBtn = appiumDriver.findElement(submitResetPasswordBtnSel);
        if (submitBtn.isDisplayed()) {
            System.out.println("PASS - Cannot submit new password if Password and Repeat Password not match!");
        } else {
            System.out.println("FAIL - The user can successfully reset password even if input Password and Repeat Password not match!");
        }
    }

    @Test
    // Submit with Correct OTP - Invalid Password and Repeat password
    public void TC006_ResetPasswordWithInvalidPassword() {
        forgotPasswordForm.clear();
        // Input correct OTP
        forgotPasswordForm.inputOTP(newOTP);
        // Input Password and Repeat password
        forgotPasswordForm.inputNewPassword(newInvalidPassword);
        forgotPasswordForm.inputRepeatNewPassword(newInvalidPassword);
        // Click submit
        forgotPasswordForm.submitReset();
        // Verify unsuccess submit
        MobileElement submitBtn = appiumDriver.findElement(submitResetPasswordBtnSel);
        if (submitBtn.isDisplayed()) {
            System.out.println("PASS - Cannot submit new password with invalid password!");
        } else {
            System.out.println("FAIL - The user can successfully reset password even if input invalid password!");
        }
    }
}
