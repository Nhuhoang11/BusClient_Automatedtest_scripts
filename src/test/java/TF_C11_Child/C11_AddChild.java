package TF_C11_Child;

import Action.ScrollDown90Percent;
import Action.ScrollUp50Percent;
import Func.C00.LaunchApp;
import Func.C02.SignInForm;
import Func.C11_Child.AddChildForm;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class C11_AddChild {
    AppiumDriver<MobileElement> appiumDriver;
    private WebDriverWait wait;
    private SignInForm signInForm;
    private ScrollDown90Percent scrollDown;
    private ScrollUp50Percent scrollUp;
    private String parentEmail = "1obmd@fthcapital.com";
    private String passwordAcc = "@Test123";
    private AddChildForm addChildForm;
    By addSel = MobileBy.AccessibilityId("Add");
    String childName = "AbcChildName";
    String childNickname = "NN";
    String school = "SSchool";
    String cardNumber = "000";

    @BeforeClass
    public void setup() {
        appiumDriver = LaunchApp.getAppiumDriver();
        wait = new WebDriverWait(appiumDriver, 10);
        addChildForm = new AddChildForm(appiumDriver);
        scrollDown = new ScrollDown90Percent(appiumDriver);
        scrollUp = new ScrollUp50Percent(appiumDriver);
        signInForm = new SignInForm(appiumDriver);
        signInForm.clickSignInButton();
        signInForm.inputEmail(parentEmail);
        signInForm.inputPassword(passwordAcc);
        signInForm.clickSubmitButton();
        // Click on CHILD on navigation bar
        MobileElement ChildNav = appiumDriver.findElementByAccessibilityId("Child");
        ChildNav.click();

    }

    @Test
    public void TC001_VerifyCannotAddWithoutInputInfor() {
        // Click Add button
        MobileElement addBtn = appiumDriver.findElement(addSel);
        addBtn.click();
        // 1. Left all fields empty &  Click on Submit btn
        scrollDown.scrollDownTo();
        addChildForm.clickOnSubmit();
        scrollUp.scrollUpTo();
        // Verify Add child fail
        if (addChildForm.verifyAllErrDisplayed()) {
            System.out.println("PASS - Cannot add child without input required fields!");
        } else {
            System.out.println("FAIL - Parent can add child even if they left required fields empty!");
        }
        addChildForm.clickBackBtn();
    }

    @Test
    public void TC002_VerifyCannotAddWithoutName() {
        // Click Add button
        MobileElement addBtn = appiumDriver.findElement(addSel);
        addBtn.click();
        addChildForm.inputNickname(childNickname);
        addChildForm.inputGenderFemale();
        addChildForm.inputBirthday();
        addChildForm.inputSchool(school);
        addChildForm.inputCardNumber(cardNumber);
        addChildForm.uploadAvatar();
        scrollDown.scrollDownTo();
        addChildForm.clickOnSubmit();
        if (addChildForm.verifyAddFail()) {
            System.out.println("PASS - Cannot add child without input Name field!");
        } else {
            System.out.println("FAIL - Parent can add child even if they left Name field empty!");
        }
        addChildForm.clickOnCancel();
    }

    @Test
    public void TC003_VerifyCannotAddWithoutNickname() {
        // Click Add button
        MobileElement addBtn = appiumDriver.findElement(addSel);
        addBtn.click();
        addChildForm.inputChildName(childName);
        addChildForm.inputGenderFemale();
        addChildForm.inputBirthday();
        addChildForm.inputSchool(school);
        addChildForm.inputCardNumber(cardNumber);
        addChildForm.uploadAvatar();
        scrollDown.scrollDownTo();
        addChildForm.clickOnSubmit();
        if (addChildForm.verifyAddFail()) {
            System.out.println("PASS - Cannot add child without input Nickname field!");
        } else {
            System.out.println("FAIL - Parent can add child even if they left Nickname field empty!");
        }
        addChildForm.clickOnCancel();
    }

    @Test
    public void TC004_VerifyCannotAddWithoutSex() {
        // Click Add button
        MobileElement addBtn = appiumDriver.findElement(addSel);
        addBtn.click();
        addChildForm.inputChildName(childName);
        addChildForm.inputNickname(childNickname);
        addChildForm.inputBirthday();
        addChildForm.inputSchool(school);
        addChildForm.inputCardNumber(cardNumber);
        addChildForm.uploadAvatar();
        scrollDown.scrollDownTo();
        addChildForm.clickOnSubmit();
        if (addChildForm.verifyAddFail()) {
            System.out.println("PASS - Cannot add child without input Gender field!");
        } else {
            System.out.println("FAIL - Parent can add child even if they left Gender field empty!");
        }
        addChildForm.clickOnCancel();
    }

    @Test
    public void TC005_VerifyCannotAddWithoutDOB() {
        // Click Add button
        MobileElement addBtn = appiumDriver.findElement(addSel);
        addBtn.click();
        addChildForm.inputChildName(childName);
        addChildForm.inputNickname(childNickname);
        addChildForm.inputGenderFemale();
        addChildForm.inputSchool(school);
        addChildForm.inputCardNumber(cardNumber);
        addChildForm.uploadAvatar();
        scrollDown.scrollDownTo();
        addChildForm.clickOnSubmit();
        if (addChildForm.verifyAddFail()) {
            System.out.println("PASS - Cannot add child without input DOB field!");
        } else {
            System.out.println("FAIL - Parent can add child even if they left DOB field empty!");
        }
        addChildForm.clickBackBtn();
    }

    @Test
    public void TC006_VerifyCannotAddWithoutSchool() {
        // Click Add button
        MobileElement addBtn = appiumDriver.findElement(addSel);
        addBtn.click();
        addChildForm.inputChildName(childName);
        addChildForm.inputNickname(childNickname);
        addChildForm.inputGenderFemale();
        addChildForm.inputBirthday();
        addChildForm.inputCardNumber(cardNumber);
        addChildForm.uploadAvatar();
        scrollDown.scrollDownTo();
        addChildForm.clickOnSubmit();
        if (addChildForm.verifyAddFail()) {
            System.out.println("PASS - Cannot add child without input School field!");
        } else {
            System.out.println("FAIL - Parent can add child even if they left School field empty!");
        }
        addChildForm.clickOnCancel();
    }

    @Test
    public void TC007_VerifyCannotAddWithoutCardNumber() {
        // Click Add button
        MobileElement addBtn = appiumDriver.findElement(addSel);
        addBtn.click();
        addChildForm.inputChildName(childName);
        addChildForm.inputNickname(childNickname);
        addChildForm.inputGenderFemale();
        addChildForm.inputBirthday();
        addChildForm.inputSchool(school);
        addChildForm.uploadAvatar();
        scrollDown.scrollDownTo();
        addChildForm.clickOnSubmit();
        if (addChildForm.verifyAddFail()) {
            System.out.println("PASS - Cannot add child without input School field!");
        } else {
            System.out.println("FAIL - Parent can add child even if they left School field empty!");
        }
        addChildForm.clickOnCancel();
    }

    @Test
    public void TC008_VerifyCannotAddWithoutUploadAvatar() {
        // Click Add button
        MobileElement addBtn = appiumDriver.findElement(addSel);
        addBtn.click();
        addChildForm.inputChildName(childName);
        addChildForm.inputNickname(childNickname);
        addChildForm.inputGenderFemale();
        addChildForm.inputBirthday();
        addChildForm.inputSchool(school);
        addChildForm.inputCardNumber(cardNumber);
        scrollDown.scrollDownTo();
        addChildForm.clickOnSubmit();
        if (addChildForm.verifyAddFail()) {
            System.out.println("PASS - Cannot add child without upload avatar!");
        } else {
            System.out.println("FAIL - Parent can add child even if they left avatar field empty!");
        }
        addChildForm.clickOnCancel();
    }

}
