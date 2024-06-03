package Func.C11_Child;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AddChildForm {
    AppiumDriver<MobileElement> appiumDriver;
    private WebDriverWait wait;
    By addSel = MobileBy.AccessibilityId("Add");
    By submitSel = MobileBy.AccessibilityId("Submit");
    By cancelSel = MobileBy.AccessibilityId("Cancel");
    By sexSel = MobileBy.AccessibilityId("Select item");
    By maleSel = MobileBy.xpath("//android.widget.TextView[@text = 'Male']");
    By femaleSel = MobileBy.xpath("//android.widget.TextView[@text = 'Female']");
    By otherSel = MobileBy.xpath("//android.widget.TextView[@text = 'Other']");

    By addFormSel = MobileBy.className("android.widget.EditText");
    int NAME = 0;
    int NICKNAME = 1;
    int SCHOOL = 2;
    int CARD_NUMBER = 3;
    By dob_avt = MobileBy.className("android.view.ViewGroup");  // eighth 7 - eleventh 10
    int BIRTHDAY = 7;
    int AVATAR = 10;
    By confirmDOBSel = MobileBy.xpath("//*[@resource-id = 'android:id/button1']");
    By firstImage = MobileBy.className("android.widget.ImageView"); //2nd 1
    By datePickerSel = MobileBy.xpath("//*[@resource-id = 'com.metadata.busnoti_client:id/pickerWrapper']");

    public AddChildForm(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void inputChildName(String name) {
        List<MobileElement> addFormElements = appiumDriver.findElements(addFormSel);
        addFormElements.get(NAME).sendKeys(name);
    }

    public void inputNickname(String nickname) {
        List<MobileElement> addFormElements = appiumDriver.findElements(addFormSel);
        addFormElements.get(NICKNAME).sendKeys(nickname);
    }

    public void inputSchool(String school) {
        List<MobileElement> addFormElements = appiumDriver.findElements(addFormSel);
        addFormElements.get(SCHOOL).sendKeys(school);
    }

    public void inputCardNumber(String cardNumber) {
        List<MobileElement> addFormElements = appiumDriver.findElements(addFormSel);
        addFormElements.get(CARD_NUMBER).sendKeys(cardNumber);
    }

    public void inputBirthday() {
        wait = new WebDriverWait(appiumDriver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(dob_avt));
        List<MobileElement> dobAvtElement = appiumDriver.findElements(dob_avt);
        dobAvtElement.get(7).click();
        MobileElement confirm = appiumDriver.findElement(confirmDOBSel);
        confirm.click();
    }

    public void uploadAvatar() {
        List<MobileElement> dobAvtElement = appiumDriver.findElements(dob_avt);
        dobAvtElement.get(10).click();
        // Pick iamge
        List<MobileElement> albumIamge = appiumDriver.findElements(firstImage);
        albumIamge.get(1).click();
    }

    public void clickOnSubmit() {
        MobileElement submitBtn = appiumDriver.findElement(submitSel);
        submitBtn.click();
    }

    public void clickOnCancel() {
        MobileElement cancelBtn = appiumDriver.findElement(cancelSel);
        cancelBtn.click();
    }

    public void clickBackBtn() {
        MobileElement back = appiumDriver.findElementByAccessibilityId("Di chuyển lên");
        back.click();
    }

    public boolean verifyAddFail() {
        try {
            Thread.sleep(3000);
            MobileElement submitBtn = appiumDriver.findElement(submitSel);
            if (submitBtn.isDisplayed()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("FAIL: error messages is not displayed!");
            return false;
        }

    }

    public boolean verifyAllErrDisplayed() {
//        MobileElement nameErr = appiumDriver.findElementByXPath("//android.widget.TextView[@text = 'Please enter your name']");
//        MobileElement nicknameErr = appiumDriver.findElementByXPath("//android.widget.TextView[@text = 'Please enter your nickname']");
//        MobileElement sexErr = appiumDriver.findElementByXPath("//android.widget.TextView[@text = 'Required']");
//        MobileElement dobErr = appiumDriver.findElementByXPath("//android.widget.TextView[@text = 'Required']");
//        MobileElement schoolErr = appiumDriver.findElementByXPath("//android.widget.TextView[@text = 'Required']");
//        MobileElement cardNumberErr = appiumDriver.findElementByXPath("//android.widget.TextView[@text = 'Please enter your card number']");
//        MobileElement avtErr = appiumDriver.findElementByXPath("//android.widget.TextView[@text = 'Required']");
        String[] popupElement = {"Please enter your name", "Please enter your nickname", "Required", "Please enter your card number"};
        boolean allDisplayed = true;

        for (String element : popupElement) {
            try {
                MobileElement msgErr = appiumDriver.findElementByXPath("//android.widget.TextView[@text = '" + element + "']");
                if (msgErr.isDisplayed()) {
                    System.out.println("PASS: " + element + " is displayed!");
                } else {
                    System.out.println("FAIL: " + element + " is not displayed!");
                    allDisplayed = false;
                }
            } catch (NoSuchElementException e) {
                System.out.println("FAIL: " + element + " is not displayed!");
                allDisplayed = false;
            }
        }

        return allDisplayed;

    }

    public void inputGenderFemale() {
        MobileElement sexOption = appiumDriver.findElement(sexSel);
        sexOption.click();
        MobileElement female = appiumDriver.findElement(femaleSel);
        female.click();
    }

    public void inputGenderMale() {
        MobileElement sexOption = appiumDriver.findElement(sexSel);
        sexOption.click();
        MobileElement male = appiumDriver.findElement(maleSel);
        male.click();
    }

    public void inputGenderOther() {
        MobileElement sexOption = appiumDriver.findElement(sexSel);
        sexOption.click();
        MobileElement other = appiumDriver.findElement(otherSel);
        other.click();
    }

    public void clear() {
        List<MobileElement> addFormElements = appiumDriver.findElements(addFormSel);
        addFormElements.get(NAME).clear();
        addFormElements.get(NICKNAME).clear();
        addFormElements.get(SCHOOL).clear();
        addFormElements.get(CARD_NUMBER).clear();
        List<MobileElement> dobAvtElement = appiumDriver.findElements(dob_avt);
        dobAvtElement.get(7).clear();
        dobAvtElement.get(10).clear();
    }
}
