package TF_C03_ForgotPassword;

import Func.C00.LaunchApp;
import Func.C02.LogoutForm;
import Func.C02.SignInForm;
import Func.C03_ForgotPassword.ForgotPasswordForm;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.ElementXPath;
import utils.LaunchGmail;

import java.util.List;

public class C03_ForgotPassword_ResendOTP {
    AppiumDriver<MobileElement> appiumDriver = null;
    private SignInForm signInForm;
    private LaunchGmail launchGmail;
    private WebDriverWait wait;
    private ForgotPasswordForm forgotPasswordForm;
    private LogoutForm logoutForm;

    @BeforeClass
    public void setup() {
        appiumDriver = LaunchApp.getAppiumDriver();
        wait = new WebDriverWait(appiumDriver, 10);
        signInForm = new SignInForm(appiumDriver);
        signInForm.clickSignInButton();
    }

    @Test(priority = 0)
    public void TC001_inputOldOTP() {
        // Click on 'Forgot password'
        MobileElement forgotPassword = appiumDriver.findElementByAccessibilityId(ElementXPath.forgotPasswordId);
        forgotPassword.click();
        // Input email
        MobileElement inputEmail = appiumDriver.findElementByClassName("android.widget.EditText");
        inputEmail.sendKeys("nhu.hoanglu@gmail.com");
        // Click on Submit
        MobileElement submitBtn = appiumDriver.findElementByXPath("//android.widget.TextView[@text = 'SUBMIT']");
        submitBtn.click();
        // Click resend
        MobileElement resend = appiumDriver.findElementByXPath("//android.widget.TextView[@text='Resend']");
        wait.until(ExpectedConditions.visibilityOf(resend));
        resend.click();
        System.out.println("Click on resend OTP");

        // Get OLD OTP from Gmail
        launchGmail = new LaunchGmail(appiumDriver);
        launchGmail.openGmail();
        // GET OLD OTP
        String oldOTP = launchGmail.getOldOTP();
        System.out.println(oldOTP);
        // Relaunch BusClient
        appiumDriver.activateApp("com.metadata.busnoti_client");
        // Input OTP
        forgotPasswordForm = new ForgotPasswordForm(appiumDriver);
        forgotPasswordForm.inputOTP(oldOTP);
        // Input new password & confirm password
        By PasswordFieldSel = MobileBy.className("android.widget.EditText");
        List<MobileElement> PasswordFields = appiumDriver.findElements(PasswordFieldSel);
        int PASSWORD_FIELD = 6;
        int REPEAT_PASSWORD_FIELD = 7;
        String newPassword = "@Test123@";
        PasswordFields.get(PASSWORD_FIELD).sendKeys(newPassword);
        PasswordFields.get(REPEAT_PASSWORD_FIELD).sendKeys(newPassword);
        System.out.println("Input new password (1st): " + newPassword);
        // Click submit => SUBMIT FAIL
        MobileElement submitNewPasswordBtn = appiumDriver.findElementByAccessibilityId("SUBMIT");
        submitNewPasswordBtn.click();
        if (submitNewPasswordBtn.isDisplayed()) {
            System.out.println("PASS - Cannot reset password with old OTP!");
        } else {
            System.out.println("FAIL!");
        }
    }

    @Test(priority = 1)
    public void TC002_inputNewOTP() {
        // Get NEW OTP from Gmail
        launchGmail = new LaunchGmail(appiumDriver);
        launchGmail.openGmail();
        // GET NEW OTP
        String newOTP = launchGmail.getnewOTP();
        System.out.println(newOTP);
        // Relaunch BusClient
        appiumDriver.activateApp("com.metadata.busnoti_client");
        // Input OTP
        forgotPasswordForm = new ForgotPasswordForm(appiumDriver);
        forgotPasswordForm.inputOTP(newOTP);
        // Input new password & confirm password
        By PasswordFieldSel = MobileBy.className("android.widget.EditText");
        List<MobileElement> PasswordFields = appiumDriver.findElements(PasswordFieldSel);
        int PASSWORD_FIELD = 6;
        int REPEAT_PASSWORD_FIELD = 7;
        String newPassword = "@Test123@";
        PasswordFields.get(PASSWORD_FIELD).sendKeys(newPassword);
        PasswordFields.get(REPEAT_PASSWORD_FIELD).sendKeys(newPassword);
        System.out.println("Input new password (1st): " + newPassword);
        // Click submit => SUBMIT FAIL
        MobileElement submitNewPasswordBtn = appiumDriver.findElementByAccessibilityId("SUBMIT");
        submitNewPasswordBtn.click();
        // Verify: Sign In by new password
        signInForm.isSignInScreedDisplayed();
        signInForm.tapToggleIconSignInForm();
        signInForm.inputEmail("nhu.hoanglu@gmail.com");
        signInForm.inputPassword(newPassword);
        signInForm.clickSubmitButton();
    }
}
