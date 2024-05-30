package utils;

import Action.ScrollDown90Percent;
import Action.ScrollUp90Percent;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LaunchGmail {
    private AppiumDriver<MobileElement> appiumDriver;
    private WebDriverWait wait;
    private ScrollDown90Percent scrollDown90Percent;

    public LaunchGmail(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void openGmail() {
        appiumDriver.runAppInBackground(Duration.ofSeconds(3));
        // Open the Gmail
        appiumDriver.activateApp("com.google.android.gm");
    }

    // Get new OTP
    public String getnewOTP() {
        // Find email and open email received
        try {
            MobileElement newEmail = appiumDriver.findElementByXPath("//android.widget.TextView[@text = 'Your verification code']");
//            wait.until(ExpectedConditions.visibilityOf(newEmail));
            if (newEmail.isDisplayed()) {
                // Open email
                System.out.println("Click on new email!");
                Thread.sleep(3000);
                newEmail.click();
            } else {
                System.out.println("No new email found!");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Users not received email!");
        }

        // Có thể user nhận được nhiều email từ cùng một emailAdd trong cùng 1 ngày, nên email mới nhất là email ở cuối danh sách
        // Get tất cả email trong danh sách
        By emailListSel = MobileBy.xpath("//android.widget.TextView[contains(@text, 'no-reply@')]");
        List<MobileElement> emailList = appiumDriver.findElements(emailListSel);
        if (!emailList.isEmpty()) {
            MobileElement newEmailElment = emailList.get(emailList.size() - 1);
            System.out.println(newEmailElment.getText());
        } else {
            System.out.println("Email is empty!");
            return null;
        }

        scrollDown90Percent = new ScrollDown90Percent(appiumDriver);
        scrollDown90Percent.scrollDownTo();

        // Get OTP from email
        // Locate OTP
        MobileElement newOTPSel = appiumDriver.findElementByXPath("//android.widget.TextView[contains(@text, 'Your password reset code is')]");
        // Lấy giá trị số ngày từ thuộc tính textContent
        String text = newOTPSel.getText();
        System.out.println(text);
        // Trích xuất số ngày từ đoạn văn bản
        String newOtpLeft = text.replaceAll("[^\\d]", "");
        return newOtpLeft;
    }

    public void quitAppInstAdds() {
        appiumDriver.terminateApp("com.google.android.gm");
    }
}
