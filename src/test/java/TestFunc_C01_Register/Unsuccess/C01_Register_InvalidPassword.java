package TestFunc_C01_Register.Unsuccess;

import Func.C00.LaunchApp;
import Func.C01.SignUpForm;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.*;
import utils.ElementXPath;

import java.sql.SQLOutput;

public class C01_Register_InvalidPassword {
    AppiumDriver<MobileElement> appiumDriver = null;
    private SignUpForm signUpForm;

    @BeforeClass
    public void setup() {
        appiumDriver = LaunchApp.getAppiumDriver();
        signUpForm = new SignUpForm(appiumDriver);
        signUpForm.clickSignUpText();
        signUpForm.tapToggleIconSignUpForm();
    }

    // Password rule
    @Test
    public void TC001_PasswordLessthan8Chars() {
        // Password < 8 characters
        String passwordLessThan8Chars = "Test12@";
        signUpForm.inputName("Client test");
        signUpForm.inputEmail("ClientTest@gmail.com");

        System.out.println("Input password: " + passwordLessThan8Chars);
        signUpForm.inputPassword(passwordLessThan8Chars);
        System.out.println("Input repeat password: " + passwordLessThan8Chars);
        signUpForm.inputRepeatPassword(passwordLessThan8Chars);

        if (signUpForm.isRemainSignInPage()) {
            System.out.println("PASS - Cannot register with Password less than 8 characters!");
        } else {
            System.out.println("FAIL - Users can successfully register even if they input a Password less than 8 characters!");
        }
        System.out.println("---------------------------------------------------------------------------");
    }

    @Test
    public void TC002_PasswordNotContainsUpperLetter() {
        // Password not contain upper case
        String passwordNotContainsUpperLetter = "test123@";
        signUpForm.clear();
        signUpForm.inputName("Client test");
        signUpForm.inputEmail("ClientTest@gmail.com");

        System.out.println("Input password: " + passwordNotContainsUpperLetter);
        System.out.println("Input repeat password: " + passwordNotContainsUpperLetter);
        signUpForm.inputPassword(passwordNotContainsUpperLetter);
        signUpForm.inputRepeatPassword(passwordNotContainsUpperLetter);

        if (signUpForm.isRemainSignInPage()) {
            System.out.println("PASS - Cannot register with Password not contains upper letter!");
        } else {
            System.out.println("FAIL - Users can successfully register even if they input a Password not contains upper letter!");
        }
        System.out.println("---------------------------------------------------------------------------");
    }

    @Test
    public void TC003_PasswordNotContainsLowerLetter() {
        // Password not contain lower case
        String passwordNotContainsLowerLetter = "TEST123@";
        signUpForm.clear();
        signUpForm.inputName("Client test");
        signUpForm.inputEmail("ClientTest@gmail.com");

        System.out.println("Input password: " + passwordNotContainsLowerLetter);
        System.out.println("Input repeat password: " + passwordNotContainsLowerLetter);
        signUpForm.inputPassword(passwordNotContainsLowerLetter);
        signUpForm.inputRepeatPassword(passwordNotContainsLowerLetter);

        if (signUpForm.isRemainSignInPage()) {
            System.out.println("PASS - Cannot register with Password not contains lower letter!");
        } else {
            System.out.println("FAIL - Users can successfully register even if they input a Password not contains lower letter!");
        }
        System.out.println("---------------------------------------------------------------------------");
    }

    @Test
    public void TC004_PasswordNotContainsNumber() {
        // Password not contain number
        String passwordNotContainsNumber = "Test@@@@";
        signUpForm.clear();
        signUpForm.inputName("Client test");
        signUpForm.inputEmail("ClientTest@gmail.com");

        System.out.println("Input password: " + passwordNotContainsNumber);
        System.out.println("Input repeat password: " + passwordNotContainsNumber);
        signUpForm.inputPassword(passwordNotContainsNumber);
        signUpForm.inputRepeatPassword(passwordNotContainsNumber);

        if (signUpForm.isRemainSignInPage()) {
            System.out.println("PASS - Cannot register with Password not contains number!");
        } else {
            System.out.println("FAIL - Users can successfully register even if they input a Password not contains number!");
        }
        System.out.println("---------------------------------------------------------------------------");
    }

    @Test
    public void TC005_PasswordNotContainsSpecialChars() {
        // Password not contain special characters
        String passwordNotContainsSpecialChars = "Test1234";
        signUpForm.clear();
        signUpForm.inputName("Client test");
        signUpForm.inputEmail("ClientTest@gmail.com");

        System.out.println("Input password: " + passwordNotContainsSpecialChars);
        System.out.println("Input repeat password: " + passwordNotContainsSpecialChars);
        signUpForm.inputPassword(passwordNotContainsSpecialChars);
        signUpForm.inputRepeatPassword(passwordNotContainsSpecialChars);

        if (signUpForm.isRemainSignInPage()) {
            System.out.println("PASS - Cannot register with Password not contains number!");
        } else {
            System.out.println("FAIL - Users can successfully register even if they input a Password not contains number!");
        }
        System.out.println("---------------------------------------------------------------------------");
    }

    // Password and Repeat password do not match
    public void TC005_PasswordAndConfirmPasswordNotMatch() {
        // Password and confirm password do not match

        signUpForm.clear();
        signUpForm.inputName("Client test");
        signUpForm.inputEmail("ClientTest@gmail.com");

        System.out.println("Input password: " + ElementXPath.validPassword);
        System.out.println("Input repeat password: " + ElementXPath.validPassword + "@");
        signUpForm.inputPassword(ElementXPath.validPassword);
        signUpForm.inputRepeatPassword(ElementXPath.validPassword + "@");

        if (signUpForm.isRemainSignInPage()) {
            System.out.println("PASS - Cannot register with Password and Repeat password do not match!");
        } else {
            System.out.println("FAIL - Users can successfully register even if the Password and Repeat password do not match!");
        }
        System.out.println("---------------------------------------------------------------------------");
    }
}
