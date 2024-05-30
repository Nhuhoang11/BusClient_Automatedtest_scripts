package Func.C02;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementXPath;

import java.util.List;

public class SignInForm {
    private AppiumDriver<MobileElement> appiumDriver;
    private WebDriverWait wait;

    By signInSel = MobileBy.AccessibilityId(ElementXPath.signInBtnId);
    By submitSignInSel = MobileBy.AccessibilityId(ElementXPath.submitSignInId);
    By emailHelperTextLocator = By.xpath("//android.widget.TextView[contains(@text, 'Please enter your email')]");
    By passwordHelperTextLocator = By.xpath("//android.widget.TextView[contains(@text, 'Minimum 8 characters')]");
    By inputEmailLocator = By.xpath(ElementXPath.emailTextBoxXpath);
    By inputPasswordLocator = By.className(ElementXPath.passwordTextBoxXpath);
    By incorrectUsernameOrPasswordPopup = By.xpath(ElementXPath.popUpLoginNotSuccessXpath);
    By HomeIcon = By.id("Home");
    By toggleSignInLocator = By.className(ElementXPath.togglePasswordSignInClass);

    public SignInForm(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
        this.wait = new WebDriverWait(appiumDriver, 10);
    }

    public void clickSignInButton() {
        MobileElement signInBtn = appiumDriver.findElement(signInSel);
        signInBtn.click();
    }

    public void clickSubmitButton() {
        MobileElement submitBtn = appiumDriver.findElement(submitSignInSel);
        wait.until(ExpectedConditions.visibilityOf(submitBtn));
        submitBtn.click();
    }

    public void tapToggleIconSignInForm() {
        List<MobileElement> toggleSignInForm = appiumDriver.findElements(toggleSignInLocator);
        final int SECOND_TOGGLE_PASSWORD = 1;
        final int THIRD_TOGGLE_PASSWORD = 2;
        toggleSignInForm.get(SECOND_TOGGLE_PASSWORD).click();
        toggleSignInForm.get(THIRD_TOGGLE_PASSWORD).click();
    }

    public void inputEmail(String email) {
        List<MobileElement> passwordElement = appiumDriver.findElements(inputPasswordLocator);
        final int PASSWORD_SAME_ELEMENT_FIRST = 0;
        passwordElement.get(PASSWORD_SAME_ELEMENT_FIRST).sendKeys(email);
    }

    public void clearEmail() {
        List<MobileElement> passwordElement = appiumDriver.findElements(inputPasswordLocator);
        final int PASSWORD_SAME_ELEMENT_FIRST = 0;
        passwordElement.get(PASSWORD_SAME_ELEMENT_FIRST).clear();
    }

    public void inputPassword(String password) {
        List<MobileElement> passwordElement = appiumDriver.findElements(inputPasswordLocator);
        final int PASSWORD_SAME_ELEMENT_SECOND = 1;
        passwordElement.get(PASSWORD_SAME_ELEMENT_SECOND).sendKeys(password);
    }

    public void clearPassword() {
        List<MobileElement> passwordElement = appiumDriver.findElements(inputPasswordLocator);
        final int PASSWORD_SAME_ELEMENT_SECOND = 1;
        passwordElement.get(PASSWORD_SAME_ELEMENT_SECOND).clear();
    }

    public void clearSignInForm() {
        List<MobileElement> signInFieldElements = appiumDriver.findElements(inputPasswordLocator);
        final int EMAIL_SAME_ELEMENT_SECOND = 0;
        final int PASSWORD_SAME_ELEMENT_SECOND = 1;
        signInFieldElements.get(EMAIL_SAME_ELEMENT_SECOND).clear();
        signInFieldElements.get(PASSWORD_SAME_ELEMENT_SECOND).clear();
    }

    public boolean isSignInScreedDisplayed() {
        MobileElement submitBtn = appiumDriver.findElement(submitSignInSel);
        if (submitBtn.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isSignInSuccess() {
        try {
            MobileElement homeIcon = appiumDriver.findElementByAccessibilityId("Home");
            wait.until(ExpectedConditions.visibilityOf(homeIcon));
            if (homeIcon.isDisplayed()) {
                return true;
            }
        } catch (WebDriverException e) {
            //
        }
        return false;
    }

    public boolean isAnyHelperTextDisplayed() {
        try {
            MobileElement emailHelperText = (MobileElement) wait.until(ExpectedConditions.visibilityOf(appiumDriver.findElement(emailHelperTextLocator)));
            if (emailHelperText.isDisplayed()) {
                return true;
            }
        } catch (WebDriverException e) {
            // Ignore exception and continue
        }

        try {
            MobileElement passwordHelperText = (MobileElement) wait.until(ExpectedConditions.visibilityOf(appiumDriver.findElement(passwordHelperTextLocator)));
            if (passwordHelperText.isDisplayed()) {
                return true;
            }
        } catch (WebDriverException e) {
            // Ignore exception and continue
        }

        try {
            MobileElement popupErrorLogin = (MobileElement) wait.until(ExpectedConditions.visibilityOf(appiumDriver.findElement(incorrectUsernameOrPasswordPopup)));
            if (popupErrorLogin.isDisplayed()) {
                return true;
            }
        } catch (WebDriverException e) {
            // Ignore exception and continue
        }
        return false;
    }

}
