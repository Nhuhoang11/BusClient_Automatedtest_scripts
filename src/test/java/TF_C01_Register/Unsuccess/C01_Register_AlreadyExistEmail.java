package TF_C01_Register.Unsuccess;

import DB.GetTheFirstValueOfTableParent;
import Func.C00.LaunchApp;
import Func.C01.SignUpForm;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.*;
import utils.ElementXPath;

public class C01_Register_AlreadyExistEmail {
    AppiumDriver<MobileElement> appiumDriver = null;
    private SignUpForm signUpForm;
    String firstParentEmail = GetTheFirstValueOfTableParent.getFirstParentEmail();

    @BeforeClass
    public void setup() {
        appiumDriver = LaunchApp.getAppiumDriver();
        signUpForm = new SignUpForm(appiumDriver);
        signUpForm.clickSignUpText();
        signUpForm.tapToggleIconSignUpForm();
    }

    @Test
    public void TC001_InputEmailAlreadyExist() {
        System.out.println("Try input email already existing: " + firstParentEmail);
        signUpForm.inputName("Abcde");
        signUpForm.inputEmail(firstParentEmail);
        signUpForm.inputPassword(ElementXPath.validPassword);
        signUpForm.inputRepeatPassword(ElementXPath.validPassword);

        signUpForm.clickSubmitButton();
        if (signUpForm.isRemainSignInPage()) {
            System.out.println("PASS - Cannot register with already existing email!");
        } else {
            System.out.println("FAIL - User can proceed to the next step, even if input an email already existing email!");
        }
    }

}
