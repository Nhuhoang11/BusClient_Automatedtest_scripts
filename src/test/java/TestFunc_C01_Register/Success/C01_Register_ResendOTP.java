package TestFunc_C01_Register.Success;

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

public class C01_Register_ResendOTP {
    AppiumDriver<MobileElement> appiumDriver;
    private SignUpForm signUpForm;
    private LaunchInstAddr launchInstAddr;
    private WebDriverWait wait;
    private ScrollDown90Percent scrollDown90Percent;

    @BeforeClass
    public void setup() {
        appiumDriver = LaunchApp.getAppiumDriver();
        signUpForm = new SignUpForm(appiumDriver);
        launchInstAddr = new LaunchInstAddr(appiumDriver);
        wait = new WebDriverWait(appiumDriver, 10);
    }

    @Test
    // Case 1: Không thể register thành công nếu nhập OTP cũ
    // 1. Launch busClient và Chrome, get email đăng ký
    public void inputOldOTP() throws Exception {
        signUpForm.clickSignUpText();
        launchInstAddr.launchInstAdd();
        launchInstAddr.getEmail();
        String email = launchInstAddr.getEmail();
        System.out.println("Email: " + email);

        // 2. Relaunch busClient, input thông tin đăng nhập
        appiumDriver.activateApp("com.metadata.busnoti_client");

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

        // 3. Click resend để app gửi OTP mới
        MobileElement resend = appiumDriver.findElementByXPath("//android.widget.TextView[@text='Resend']");
        wait.until(ExpectedConditions.visibilityOf(resend));
        resend.click();

        // 4. Relaunch InstAddr, get OTP đầu tiên (OTP cũ)
        appiumDriver.activateApp("air.kukulive.mailnow");
        String oldOTP = launchInstAddr.getOldOTP();
        System.out.println("Old OTP: " + oldOTP);

        // 5. Relaunch BusClient, input OTP vừa lấy được và submit -> Register thất bại
        appiumDriver.activateApp("com.metadata.busnoti_client");
        signUpForm.inputOTPandSubmit(oldOTP);

        if (resend.isDisplayed()) {
            System.out.println("PASS - Input old OTP, users cannot register!");
        } else {
            System.out.println("FAIL - Successfully regiter even if users input old OTP!");
        }
        // Clear old OTP
        signUpForm.clearOldOTP();

        // Case 2: Register thành công bằng OTP mới
        // 6. Relaunch InstAddr, open and get new email
        appiumDriver.activateApp("air.kukulive.mailnow");
        String newOTP = launchInstAddr.getNewOTP();
        System.out.println("New OTP: " + newOTP);
        // 7. Relaunch BusClient, input OTP vừa lấy được và submit -> Register thành công
        appiumDriver.activateApp("com.metadata.busnoti_client");
        signUpForm.inputOTPandSubmit(newOTP);
    }


}

/*
// get OTP bang Chrome
public class C01_Register_ResendOTP {
    AppiumDriver<MobileElement> appiumDriver;
    private SignUpForm signUpForm;
    private LaunchChrome launchChrome;
    private WebDriverWait wait;
    private ScrollDown90Percent scrollDown90Percent;

    @BeforeClass
    public void setup() {
        appiumDriver = LaunchApp.getAppiumDriver();
        signUpForm = new SignUpForm(appiumDriver);
        launchChrome = new LaunchChrome(appiumDriver);
        wait = new WebDriverWait(appiumDriver, 10);
    }

    @Test
    // Case 1: Không thể register thành công nếu nhập OTP cũ
    // 1. Launch busClient và Chrome, get email đăng ký
    public void inputOldOTP() throws Exception {
        signUpForm.clickSignUpText();
        launchChrome.launchChrome();
        String email = launchChrome.getEmail();
        System.out.println("Email: " + email);

        // 2. Relaunch busClient, input thông tin đăng nhập
        appiumDriver.activateApp("com.metadata.busnoti_client");

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

        // 3. Click resend để app gửi OTP mới
        MobileElement resend = appiumDriver.findElementByXPath("android.widget.TextView[@text='Resend']");
        resend.click();

        // 4. Relaunch Chrome, get OTP đầu tiên (OTP cũ)
        appiumDriver.activateApp("com.android.chrome");
        scrollDown90Percent = new ScrollDown90Percent(appiumDriver);
        scrollDown90Percent.scrollDownTo();

        String oldOTP = launchChrome.getOldOTP();
        // 5. Relaunch BusClient, input OTP vừa lấy được và submit -> Register thất bại
        appiumDriver.activateApp("com.metadata.busnoti_client");
        signUpForm.inputOTPandSubmit(oldOTP);

        if (resend.isDisplayed()) {
            System.out.println("Input old OTP, users cannot register!");
        } else {
            System.out.println("Successfully regiter even if users input old OTP!");
        }

        // Case 2: Register thành công bằng OTP mới
        // 6. Relaunch Chrome, back to prev screen and get OTP mới
        appiumDriver.activateApp("com.android.chrome");
        MobileElement backToListSel = appiumDriver.findElementByXPath("//android.widget.TextView[contains(@text, 'BACK')]");
        MobileElement inbox = appiumDriver.findElementByXPath("//android.widget.TextView[@text = 'INBOX']");
        wait.until(ExpectedConditions.visibilityOf(inbox));
        String newOTP = launchChrome.getOTP();
        // 7. Relaunch BusClient, input OTP vừa lấy được và submit -> Register thành công
        appiumDriver.activateApp("com.metadata.busnoti_client");
        signUpForm.inputOTPandSubmit(newOTP);
    }


}
 */
