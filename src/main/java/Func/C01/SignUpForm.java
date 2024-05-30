package Func.C01;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementXPath;

import java.util.List;

public class SignUpForm {
    private AppiumDriver<MobileElement> appiumDriver;
    private WebDriverWait wait;

    By signUpSel = MobileBy.AccessibilityId(ElementXPath.signUpTxtId);
    By submitSignUpSel = MobileBy.AccessibilityId(ElementXPath.submitSignUpBtnId);
    By nameFieldLocator = By.className(ElementXPath.nameTextBoxXpath);
    By emailFieldLocator = By.className(ElementXPath.emailSignUpTextBoxXpath);
    By passwordFieldLocator = By.className(ElementXPath.passwordlSignUpTextBoxXpath);
    By rePasswordFieldLocator = By.className(ElementXPath.repeatPasswordlSignUpTextBoxXpath);

    By inputEmailLocator = By.xpath(ElementXPath.emailTextBoxXpath);
    By inputPasswordLocator = By.className(ElementXPath.passwordTextBoxXpath);
    By incorrectUsernameOrPasswordPopup = By.xpath(ElementXPath.popUpLoginNotSuccessXpath);
    By HomeIcon = By.id("Home");
    By toggleSignInLocator = By.className(ElementXPath.togglePasswordSignUpClass);

    public SignUpForm(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
        this.wait = new WebDriverWait(appiumDriver, 10);
    }

    public void clickSignUpText() {
        MobileElement signUpLink = appiumDriver.findElement(signUpSel);
        signUpLink.click();
        MobileElement alreadyHaveAnAcc = appiumDriver.findElementByXPath("//android.widget.TextView[contains(@text, 'Already have an account?')]");
        wait.until(ExpectedConditions.visibilityOf(alreadyHaveAnAcc));
    }

    public void clickSubmitButton() {
        MobileElement submitBtn = appiumDriver.findElement(submitSignUpSel);
        wait.until(ExpectedConditions.visibilityOf(submitBtn));
        submitBtn.click();
    }

    public void tapToggleIconSignUpForm() {
        List<MobileElement> toggleSignInForm = appiumDriver.findElements(toggleSignInLocator);
        final int SECOND_TOGGLE_PASSWORD = 1;
        final int THIRD_TOGGLE_PASSWORD = 2;
        toggleSignInForm.get(SECOND_TOGGLE_PASSWORD).click();
        toggleSignInForm.get(THIRD_TOGGLE_PASSWORD).click();
    }

    public void inputName(String name) {
        List<MobileElement> textBoxElements = appiumDriver.findElements(nameFieldLocator);
        final int NAME_SAME_ELEMENT = 0;
        textBoxElements.get(NAME_SAME_ELEMENT).sendKeys(name);
    }

    public void inputEmail(String email) {
        List<MobileElement> textBoxElements = appiumDriver.findElements(nameFieldLocator);
        final int EMAIL_SAME_ELEMENT = 1;
        textBoxElements.get(EMAIL_SAME_ELEMENT).sendKeys(email);
    }

    public void inputPassword(String password) {
        List<MobileElement> textBoxElements = appiumDriver.findElements(nameFieldLocator);
        final int PASSWORD_SAME_ELEMENT = 2;
        textBoxElements.get(PASSWORD_SAME_ELEMENT).sendKeys(password);
    }

    public void inputRepeatPassword(String repeatPassword) {
        List<MobileElement> textBoxElements = appiumDriver.findElements(nameFieldLocator);
        final int REPEAT_PASSWORD_SAME_ELEMENT = 3;
        textBoxElements.get(REPEAT_PASSWORD_SAME_ELEMENT).sendKeys(repeatPassword);
    }

    public void clear() {
        List<MobileElement> textBoxElements = appiumDriver.findElements(nameFieldLocator);
        final int NAME_SAME_ELEMENT = 0;
        textBoxElements.get(NAME_SAME_ELEMENT).clear();
        final int EMAIL_SAME_ELEMENT = 1;
        textBoxElements.get(EMAIL_SAME_ELEMENT).clear();
        final int PASSWORD_SAME_ELEMENT = 2;
        textBoxElements.get(PASSWORD_SAME_ELEMENT).clear();
        final int REPEAT_PASSWORD_SAME_ELEMENT = 3;
        textBoxElements.get(REPEAT_PASSWORD_SAME_ELEMENT).clear();
    }


    public boolean isRemainSignInPage() {
//        MobileElement submitBtnAgain = (MobileElement) wait.until(ExpectedConditions.visibilityOf(appiumDriver.findElement(submitSignUpSel)));
        MobileElement signupScreen = appiumDriver.findElementByXPath("//android.widget.TextView[contains(@text, 'Sign Up')]");
        try {
            wait.until(ExpectedConditions.visibilityOf(signupScreen));
            if (signupScreen.isDisplayed()) {
                return true;
            }
        } catch (WebDriverException e) {
            //
        }
        return false;
    }

    // Locate câu hỏi hiển thị trên màn hình nhập OTP, màn hình này hiển thị đồng nghĩa step trước đã pass.
    By verifyQuestionSel = MobileBy.xpath("//android.widget.TextView[@text = 'Check your email']");


    public boolean isOTPScreenDisplaying() {
        MobileElement verifyQuestion = appiumDriver.findElement(verifyQuestionSel);
        try {
            wait.until(ExpectedConditions.visibilityOf(verifyQuestion));
            if (verifyQuestion.isDisplayed()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public void inputOTPandSubmit(String otp) {
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

    public void clearOldOTP() {
        List<MobileElement> textBoxElements = appiumDriver.findElements(nameFieldLocator);
        final int FIRST_OTP_BOX = 0;
        textBoxElements.get(FIRST_OTP_BOX).clear();
        final int SECOND_OTP_BOX = 1;
        textBoxElements.get(SECOND_OTP_BOX).clear();
        final int THIRD_OTP_BOX = 2;
        textBoxElements.get(THIRD_OTP_BOX).clear();
        final int FOURTH_OTP_BOX = 3;
        textBoxElements.get(FOURTH_OTP_BOX).clear();
        final int FIFTH_OTP_BOX = 4;
        textBoxElements.get(FIFTH_OTP_BOX).clear();
        final int SIXTH_OTP_BOX = 5;
        textBoxElements.get(SIXTH_OTP_BOX).clear();
    }

    public boolean verifyRegisterSuccess() {
        MobileElement inputEmailLocator = appiumDriver.findElementByXPath(ElementXPath.emailTextBoxXpath);
        try {
            wait.until(ExpectedConditions.visibilityOf(inputEmailLocator));
            if (inputEmailLocator.isDisplayed()) {
                return true;
            }
        } catch (Exception e) {
            //
        }
        return false;
    }
}

/*

    public void clearName() {
        List<MobileElement> textBoxElements = appiumDriver.findElements(nameFieldLocator);
        final int NAME_SAME_ELEMENT = 0;
        textBoxElements.get(NAME_SAME_ELEMENT).clear();
    }

    public void clearEmail() {
        List<MobileElement> textBoxElements = appiumDriver.findElements(nameFieldLocator);
        final int EMAIL_SAME_ELEMENT = 1;
        textBoxElements.get(EMAIL_SAME_ELEMENT).clear();
    }

    public void clearPassword() {
        List<MobileElement> textBoxElements = appiumDriver.findElements(nameFieldLocator);
        final int PASSWORD_SAME_ELEMENT = 2;
        textBoxElements.get(PASSWORD_SAME_ELEMENT).clear();
    }

    public void clearRepeatPassword() {
        List<MobileElement> textBoxElements = appiumDriver.findElements(nameFieldLocator);
        final int REPEAT_PASSWORD_SAME_ELEMENT = 3;
        textBoxElements.get(REPEAT_PASSWORD_SAME_ELEMENT).clear();
    }
 */