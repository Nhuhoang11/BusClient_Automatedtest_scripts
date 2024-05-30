package utils;

import Action.ScrollDown90Percent;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class LaunchChrome {
    private AppiumDriver<MobileElement> appiumDriver;
    private WebDriverWait wait;
    private ScrollDown90Percent scrollDown;

    public LaunchChrome(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
        this.wait = new WebDriverWait(appiumDriver, 15);
    }

    public void launchChrome() throws IOException {
        // Put BusCLient in background till call again
        appiumDriver.runAppInBackground(Duration.ofSeconds(3));
        // Open the Chrome
        appiumDriver.activateApp("com.android.chrome");
        // Setup acc chrome
        By searchBarSel = MobileBy.xpath("//*[@resource-id='com.android.chrome:id/url_bar']");
        By searchBoxSel = MobileBy.xpath("//*[@resource-id='com.android.chrome:id/search_box']");

        try {
            MobileElement searchBar = appiumDriver.findElement(searchBarSel);
            if (searchBar.isDisplayed()) {
                searchBar.click();
                searchBar.sendKeys("https://temp-mail.org/en/");
                Runtime.getRuntime().exec("adb shell input keyevent 66"); // Thay cho phím Enter
//                wait.until(ExpectedConditions.visibilityOf(searchBar)).sendKeys("https://temp-mail.org/en/" + Keys.ENTER);
                System.out.println("Input link1!");
            }
        } catch (Exception e) {
            MobileElement searchBox = appiumDriver.findElement(searchBoxSel);
            wait.until(ExpectedConditions.visibilityOf(searchBox));
            searchBox.click();

            MobileElement searchBar = appiumDriver.findElement(searchBarSel);
            wait.until(ExpectedConditions.visibilityOf(searchBar));
            searchBar.sendKeys("https://temp-mail.org/en/");
            Runtime.getRuntime().exec("adb shell input keyevent 66");
            System.out.println("Input link2!");
        }

    }

    public String getEmail() {
        By emailFieldSel = MobileBy.xpath("//android.widget.EditText[contains(@text, '@')]");
        MobileElement emailField = appiumDriver.findElement(emailFieldSel);
        wait.until(ExpectedConditions.visibilityOf(emailField));
        
        return emailField.getText();
    }

    public String getOTP() {
        By emailFieldSel = MobileBy.xpath("//android.widget.EditText[contains(@text, '@')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailFieldSel));
        scrollDown = new ScrollDown90Percent(appiumDriver);
        scrollDown.scrollDownTo();

        MobileElement readEmailIcon = appiumDriver.findElementByXPath("//android.widget.TextView[@text = 'Your verification code']");
        wait.until(ExpectedConditions.visibilityOf(readEmailIcon));
        readEmailIcon.click();

        // Locate OTP
        MobileElement OTPSel = appiumDriver.findElementByXPath("//android.widget.TextView[contains(@text, 'Your confirmation code is')]");
        // Lấy giá trị số ngày từ thuộc tính textContent
        String text = OTPSel.getText();
        // Trích xuất số ngày từ đoạn văn bản
        String otpLeft = text.replaceAll("[^\\d]", "");
        // Đổi giá trị từ chuỗi sang kiểu số nguyên
//        int OTP = Integer.parseInt(otpLeft);
        return otpLeft;
    }

    public String getOldOTP() {
        // OTP cũ là OTP thứ 2 trong danh sách
        By emailListsSel = MobileBy.xpath("//android.widget.TextView[@text = 'no-reply@verificationemail.com']");
        List<MobileElement> emailList = appiumDriver.findElements(emailListsSel);
        int FIRST_EMAIL = 0;
        int SECOND_EMAIL = 1;
        emailList.get(SECOND_EMAIL).click();

        By emailFieldSel = MobileBy.xpath("//android.widget.EditText[contains(@text, '@')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailFieldSel));
        scrollDown = new ScrollDown90Percent(appiumDriver);
        scrollDown.scrollDownTo();
        // Locate OTP
        MobileElement OTPSel = appiumDriver.findElementByXPath("//android.widget.TextView[contains(@text, 'Your confirmation code is')]");
        // Lấy giá trị số ngày từ thuộc tính textContent
        String text = OTPSel.getText();
        // Trích xuất số ngày từ đoạn văn bản
        String otpLeft = text.replaceAll("[^\\d]", "");
        // Đổi giá trị từ chuỗi sang kiểu số nguyên
//        int OTP = Integer.parseInt(otpLeft);
        return otpLeft;
    }
    // MobileElement backToListSel = appiumDriver.findElementByXPath("//android.widget.TextView[contains(@text, 'BACK')]");

}
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