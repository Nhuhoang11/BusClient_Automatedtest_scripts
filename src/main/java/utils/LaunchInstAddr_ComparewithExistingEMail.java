package utils;

import Action.ScrollDown50Percent;
import Action.ScrollDown90Percent;
import Action.ScrollUp90Percent;
import DB.GetAllEmailOfTableParent;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class LaunchInstAddr_ComparewithExistingEMail {
    private AppiumDriver<MobileElement> appiumDriver;
    private WebDriverWait wait;
    private ScrollDown90Percent scrollDown;
    private ScrollDown50Percent scrollDown50Percent;
    private ScrollUp90Percent scrollUp90Percent;
    private GetAllEmailOfTableParent getAllEmailOfTableParent;

    public LaunchInstAddr_ComparewithExistingEMail(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
        this.wait = new WebDriverWait(appiumDriver, 15);
    }

    public void launchInstAddress() throws IOException {
        // Put BusCLient in background till call again
        appiumDriver.runAppInBackground(Duration.ofSeconds(3));
        // Open the App Email
        appiumDriver.activateApp("air.kukulive.mailnow");
    }

    // Get email
    public String getEmail() {
        // Tap chọn icon để mở danh sách email
        By emailElementsSel = MobileBy.className("android.widget.ImageView"); // second class
        List<MobileElement> getEmailSel = appiumDriver.findElements(emailElementsSel);
        int EMAIL_ADD = 1;
        getEmailSel.get(EMAIL_ADD).click();
        getEmailSel.get(EMAIL_ADD).click();

        scrollDown50Percent = new ScrollDown50Percent(appiumDriver);
        scrollDown50Percent.scrollDownTo();

        // Find Email: get first email in emaillist
        By emailAddSel = MobileBy.xpath("//android.widget.Button[contains(@text, '@')]");
        List<MobileElement> emailList = appiumDriver.findElements(emailAddSel);
        // Check if any email in the list is not in the database
        for (MobileElement emailElement : emailList) {
            String email = emailElement.getText();
            System.out.println("Check email in DB...");
            if (GetAllEmailOfTableParent.isEmailInDatabase(email)) {
                System.out.println(email + "is already existing!");
            } else {
                return email; // Return the first email that is not in the database
            }
        }

        // If all emails are in the database, create a new email
        MobileElement createEmail = appiumDriver.findElementByXPath("//android.widget.Button[@text = 'Create an address automatically']");
        createEmail.click();
        try {
            MobileElement closePopup = appiumDriver.findElementByXPath("//android.widget.Button[@text = 'Close']");
//            wait.until(ExpectedConditions.visibilityOf(closePopup));
            if (closePopup.isDisplayed()) {
                closePopup.click();
            }
        } catch (Exception e) {
            MobileElement agreeCreateBtn = appiumDriver.findElementByXPath("//android.widget.Button[@text = 'Yes']");
            wait.until(ExpectedConditions.visibilityOf(agreeCreateBtn));
            agreeCreateBtn.click();
            MobileElement closePopup = appiumDriver.findElementByXPath("//android.widget.Button[@text = 'Close']");
            wait.until(ExpectedConditions.visibilityOf(closePopup));
            closePopup.click();
        }

        // Find the new email and return it
        scrollDown50Percent.scrollDownTo();
        if (!emailList.isEmpty()) {
            MobileElement lastEmailAddElement = emailList.get(emailList.size() - 1);
            return lastEmailAddElement.getText();
        } else {
            System.out.println("Email address list is empty!");
            return null;
        }

    }

    // Get new OTP
    public String getNewOTP() {
        By emailElementsSel = MobileBy.className("android.widget.ImageView"); // third class
        List<MobileElement> emailBoxSel = appiumDriver.findElements(emailElementsSel);
        int EMAIL_BOX = 2;
        emailBoxSel.get(EMAIL_BOX).click();
        emailBoxSel.get(EMAIL_BOX).click();

//        MobileElement reloadIcon = appiumDriver.findElementByXPath("//*[@resource-id = 'air.kukulive.mailnow:id/action_menu_reload']");
//        wait.until(ExpectedConditions.elementToBeClickable(reloadIcon)).click();
        scrollUp90Percent = new ScrollUp90Percent(appiumDriver);
        scrollUp90Percent.scrollUpTo();
//        MobileElement openEmail = appiumDriver.findElementByXPath("//android.widget.Button[contains(@text, 'Your verification code')]");
//        openEmail.click();

        // Open the new email
        try {
            Thread.sleep(3000);
            By openNewEmail = MobileBy.xpath("//android.widget.Button[contains(@text, 'Your verification code')]");
            List<MobileElement> emailList = appiumDriver.findElements(openNewEmail);
            if (!emailList.isEmpty()) {
                int NEW_EMAIL = 0;
                MobileElement newEmailElement = emailList.get(NEW_EMAIL);
                System.out.println("Click on new email!");
                Thread.sleep(3000);
                wait.until(ExpectedConditions.elementToBeClickable(newEmailElement)).click();
            } else {
                System.out.println("No new email found!");
                return null;
            }
        } catch (Exception e) {
            // Find again
//            By openNewEmail = MobileBy.xpath("//android.widget.Button[contains(@text, 'Your verification code')]");
//            List<MobileElement> emailList = appiumDriver.findElements(openNewEmail);
//            int NEW_EMAIL = 0;
//            emailList.get(NEW_EMAIL).click();
            System.out.println("Cannot get OTP!");
        }
        // Locate OTP
        MobileElement newOTPSel = appiumDriver.findElementByXPath("//android.widget.TextView[contains(@text, 'Your confirmation code is')]");
        // Lấy giá trị số ngày từ thuộc tính textContent
        String text = newOTPSel.getText();
        System.out.println(text);
        // Trích xuất số ngày từ đoạn văn bản
        String newOtpLeft = text.replaceAll("[^\\d]", "");

        return newOtpLeft;
    }

    // Get old OTP
    public String getOldOTP() {
        // Open email box
        By emailElementsSel = MobileBy.className("android.widget.ImageView"); // third class
        List<MobileElement> emailBoxSel = appiumDriver.findElements(emailElementsSel);
        int EMAIL_BOX = 2;
        emailBoxSel.get(EMAIL_BOX).click();
        emailBoxSel.get(EMAIL_BOX).click();

        scrollUp90Percent = new ScrollUp90Percent(appiumDriver);
        scrollUp90Percent.scrollUpTo();

        // Open the old email
        try {
            Thread.sleep(3000);
            By openOldEmail = MobileBy.xpath("//android.widget.Button[contains(@text, 'Your verification code')]");
            List<MobileElement> emailList = appiumDriver.findElements(openOldEmail);
            if (!emailList.isEmpty()) {
                int OLD_EMAIL = 1;
                MobileElement oldEmailElement = emailList.get(OLD_EMAIL);
                System.out.println("Click on old email!");
                Thread.sleep(3000);
                wait.until(ExpectedConditions.elementToBeClickable(oldEmailElement)).click();
            } else {
                System.out.println("No old email found!");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Cannot get old OTP!");
        }

        // Locate OTP
        MobileElement OTPSel = appiumDriver.findElementByXPath("//android.widget.TextView[contains(@text, 'Your confirmation code is')]");
        // Lấy giá trị số ngày từ thuộc tính textContent
        String text = OTPSel.getText();
        // Trích xuất số ngày từ đoạn văn bản
        String oldOTPLeft = text.replaceAll("[^\\d]", "");
        // Đổi giá trị từ chuỗi sang kiểu số nguyên
//        int OTP = Integer.parseInt(otpLeft);
        return oldOTPLeft;
    }

}

// MobileElement backToListSel = appiumDriver.findElementByXPath("//android.widget.TextView[contains(@text, 'BACK')]");


/*
Locate cua 2 button Refresh & Delete tren website get OTP
//android.widget.TextView[contains(@text, 'Refresh')]
//android.widget.Button[@text = 'Delete']
 */


//        By tieptucBangAccCuaNhuSel = MobileBy.xpath("//*[@resource-id='com.android.chrome:id/signin_fre_continue_button']");
//        By agreeBtnSel = MobileBy.xpath("//*[@resource-id='com.android.chrome:id/button_primary']");
//        By xemThemSel = MobileBy.xpath("//*[@resource-id='com.android.chrome:id/more_button']");
//        By toiHieuSel = MobileBy.xpath("//*[@resource-id='com.android.chrome:id/ack_button']");


//        MobileElement contByNhuAcc = appiumDriver.findElement(tieptucBangAccCuaNhuSel);
//        wait.until(ExpectedConditions.visibilityOf(contByNhuAcc)).click();
//        MobileElement agreeBtn = appiumDriver.findElement(agreeBtnSel);
//        wait.until(ExpectedConditions.visibilityOf(agreeBtn)).click();
//        MobileElement xemThemBtn = appiumDriver.findElement(xemThemSel);
//        wait.until(ExpectedConditions.visibilityOf(xemThemBtn)).click();
//        MobileElement toiHieuBtn = appiumDriver.findElement(toiHieuSel);
//        wait.until(ExpectedConditions.visibilityOf(toiHieuBtn)).click();