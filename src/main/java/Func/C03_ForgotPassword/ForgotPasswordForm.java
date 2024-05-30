package Func.C03_ForgotPassword;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementXPath;

import java.util.List;

public class ForgotPasswordForm {
    AppiumDriver<MobileElement> appiumDriver;
    private WebDriverWait wait;
    // Locate câu hỏi hiển thị trên màn hình nhập OTP, màn hình này hiển thị đồng nghĩa step trước đã pass.
    By verifyQuestionSel = MobileBy.xpath("//android.widget.TextView[@text = 'Check your email']");
    By PasswordFieldSel = MobileBy.className("android.widget.EditText");
    By toggelSel = MobileBy.className("android.widget.ImageView");

    public ForgotPasswordForm(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
        this.wait = new WebDriverWait(appiumDriver, 10);
    }

    public void inputOTP(String otp) {
        MobileElement verifyQuestion = appiumDriver.findElement(verifyQuestionSel);
        By OTPBoxSel = By.className("android.widget.EditText");
        List<MobileElement> OTPBox = appiumDriver.findElements(OTPBoxSel);
//        int FIRST_OTP_BOX = 0;
//        int SECOND_OTP_BOX = 1;
//        int THIRD_OTP_BOX = 2;
//        int FOURTH_OTP_BOX = 3;
//        int FIFTH_OTP_BOX = 4;
//        int SIXTH_OTP_BOX = 5;
        for (int i = 0; i < otp.length(); i++) {
            OTPBox.get(i).sendKeys(Character.toString(otp.charAt(i)));
        }
        verifyQuestion.click();
        MobileElement submitOTPBtn = appiumDriver.findElementByAccessibilityId("SUBMIT");
        submitOTPBtn.click();
    }

    public void inputNewPassword(String newPassword) {
        List<MobileElement> textBoxElements = appiumDriver.findElements(PasswordFieldSel);
        final int PASSWORD_FIELD = 6;
        textBoxElements.get(PASSWORD_FIELD).sendKeys(newPassword);
        // toggle
        List<MobileElement> toggleElements = appiumDriver.findElements(toggelSel);
        int FIRST_TOGGLE = 2;
        textBoxElements.get(FIRST_TOGGLE).click();
    }

    public void inputRepeatNewPassword(String newPassword) {
        List<MobileElement> textBoxElements = appiumDriver.findElements(PasswordFieldSel);
        final int REPEAT_PASSWORD_FIELD = 7;
        textBoxElements.get(REPEAT_PASSWORD_FIELD).sendKeys(newPassword);
        // toggle
        List<MobileElement> toggleElements = appiumDriver.findElements(toggelSel);
        int SECOND_TOGGLE = 3;
        textBoxElements.get(SECOND_TOGGLE).click();
    }

    public void submitReset() {
        MobileElement submitBtn = appiumDriver.findElementByXPath("//android.widget.TextView[@text = 'SUBMIT']");
        submitBtn.click();
    }

    public void clear() {
        List<MobileElement> textBoxElements = appiumDriver.findElements(PasswordFieldSel);
        final int FIRST_OTP_BOX = 0;
        textBoxElements.get(FIRST_OTP_BOX).clear();
        final int SECOND_OTP_BOX = 1;
        textBoxElements.get(FIRST_OTP_BOX).clear();
        final int THIRD_OTP_BOX = 2;
        textBoxElements.get(FIRST_OTP_BOX).clear();
        final int FOURTH_OTP_BOX = 3;
        textBoxElements.get(FIRST_OTP_BOX).clear();
        final int FIFTH_OTP_BOX = 4;
        textBoxElements.get(FIRST_OTP_BOX).clear();
        final int SIX_OTP_BOX = 5;
        textBoxElements.get(SIX_OTP_BOX).clear();
        final int PASSWORD = 6;
        textBoxElements.get(FIRST_OTP_BOX).clear();
        final int REPEAT_PASSWORD = 7;
        textBoxElements.get(FIRST_OTP_BOX).clear();
    }


}
