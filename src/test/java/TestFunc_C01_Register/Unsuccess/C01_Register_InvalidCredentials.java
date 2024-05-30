package TestFunc_C01_Register.Unsuccess;

import Func.C00.LaunchApp;
import Func.C01.SignUpForm;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.*;

public class C01_Register_InvalidCredentials {
    AppiumDriver<MobileElement> appiumDriver = null;
    private SignUpForm signUpForm;

    @BeforeClass
    public void setup() {
        appiumDriver = LaunchApp.getAppiumDriver();
        signUpForm = new SignUpForm(appiumDriver);
        signUpForm.clickSignUpText();
        signUpForm.tapToggleIconSignUpForm();
    }

    @Test
    public void TC001_registerWithoutInputCreds() {
        System.out.println("Click on Submit button!");
        signUpForm.clickSubmitButton();
        if (signUpForm.isRemainSignInPage()) {
            System.out.println("PASS - Cannot register without input credentials!");
        } else {
            System.out.println("FAIL - User can proceed to the next step, even thought submit without input required fields!");
        }
        System.out.println("---------------------------------------------------------------------------");
    }

    @Test
    public void TC002_registerWithoutName() {
        // Input Email - Password - Repeat password
        System.out.println("Input email");
        signUpForm.inputEmail("ClientTest@gmail.com");

        System.out.println("Input password");
        signUpForm.inputPassword("@Test123");

        System.out.println("Input repeat password");
        signUpForm.inputRepeatPassword("@Test123");

        System.out.println("Click on Submit button!");
        signUpForm.clickSubmitButton();
        if (signUpForm.isRemainSignInPage()) {
            System.out.println("PASS - Cannot register without input Email!");
        } else {
            System.out.println("FAIL - User can proceed to the next step, even thought submit without input required fields!");
        }
        System.out.println("---------------------------------------------------------------------------");
    }

    @Test
    public void TC003_registerWithoutEmail() {
        // Input Name - Password - Repeat password
//        signUpForm.clearEmail();
//        signUpForm.clearPassword();
//        signUpForm.clearRepeatPassword();
        signUpForm.clear();

        System.out.println("Input name");
        signUpForm.inputName("Client test");

        System.out.println("Input password");
        signUpForm.inputPassword("@Test123");

        System.out.println("Input repeat password");
        signUpForm.inputRepeatPassword("@Test123");

        System.out.println("Click on submit");
        signUpForm.clickSubmitButton();
        if (signUpForm.isRemainSignInPage()) {
            System.out.println("PASS - Cannot register without input Email!");
        } else {
            System.out.println("FAIL - User can proceed to the next step, even thought submit without input required fields!");
        }
        System.out.println("---------------------------------------------------------------------------");
    }

    @Test
    public void TC004_registerWithoutPassword() {
        // Input Email - Name - Password
//        signUpForm.clearName();
//        signUpForm.clearPassword();
//        signUpForm.clearRepeatPassword();
        signUpForm.clear();

        System.out.println("Input name");
        System.out.println("Input email");
        System.out.println("Input repeat password");
        signUpForm.inputName("Client test");
        signUpForm.inputEmail("ClientTest@gmail.com");
        signUpForm.inputRepeatPassword("@Test123");

        signUpForm.clickSubmitButton();
        if (signUpForm.isRemainSignInPage()) {
            System.out.println("PASS - Cannot register without input Password!");
        } else {
            System.out.println("FAIL - User can proceed to the next step, even thought submit without input required fields!");
        }
        System.out.println("---------------------------------------------------------------------------");
    }

    @Test
    public void TC005_registerWithoutRepeatPassword() {
//        signUpForm.clearName();
//        signUpForm.clearEmail();
//        signUpForm.clearPassword();
        signUpForm.clear();

        System.out.println("Input name");
        System.out.println("Input email");
        System.out.println("Input password");
        signUpForm.inputName("Client test");
        signUpForm.inputEmail("ClientTest@gmail.com");
        signUpForm.inputPassword("@Test123");

        signUpForm.clickSubmitButton();
        if (signUpForm.isRemainSignInPage()) {
            System.out.println("PASS - Cannot register without input Repeat password!");
        } else {
            System.out.println("FAIL - User can proceed to the next step, even thought submit without input required fields!");
        }
        System.out.println("---------------------------------------------------------------------------");
    }
}
