package Drafts;

import Action.ScrollDown90Percent;
import Func.C00.LaunchApp;
import Func.C01.SignUpForm;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.LaunchChrome;
import utils.LaunchGmail;
import utils.LaunchInstAddr;
import utils.LaunchInstAddr_ComparewithExistingEMail;

import java.io.IOException;

public class Try {
    AppiumDriver<MobileElement> appiumDriver = null;
    private WebDriverWait wait;
    private SignUpForm signUpForm;
    //    private LaunchInstAddr launchInstAddr;
    private LaunchInstAddr_ComparewithExistingEMail launchInstAddrComparewithExistingEMail;
    private ScrollDown90Percent scrollDown;
    private LaunchGmail launchGmail;

    @BeforeClass
    public void setup() {
        appiumDriver = LaunchApp.getAppiumDriver();
        wait = new WebDriverWait(appiumDriver, 10);
        signUpForm = new SignUpForm(appiumDriver);
//        signUpForm.clickSignUpText();
//        launchInstAddr = new LaunchInstAddr(appiumDriver);
//        launchInstAddrComparewithExistingEMail = new LaunchInstAddr_ComparewithExistingEMail(appiumDriver);
    }

    @Test
    public void inputValidCreds() throws IOException {
//        launchChrome.launchChrome();
//        // Get Email
//        String email = launchChrome.getEmail();
//        System.out.println("Email: " + email);
//
//        // Relaunch app BusClient
//        scrollDown = new ScrollDown90Percent(appiumDriver);
//        scrollDown.scrollDownTo();
//        // Get OTP
//        String OTP = launchChrome.getOTP();
//        System.out.println("OTP: " + OTP);
//        launchInstAddr.launchInstAdd();

//        launchInstAddrComparewithExistingEMail.launchInstAddress();
//        // Get Email
//        String email = launchInstAddrComparewithExistingEMail.getEmail();
//        System.out.println("Email: " + email);
//        // Get OTP
//        String OTP = launchInstAddrComparewithExistingEMail.getNewOTP();
//        System.out.println("OTP: " + OTP);

        launchGmail = new LaunchGmail(appiumDriver);
        launchGmail.openGmail();
        String old = launchGmail.getOldOTP();
        System.out.println(old);
    }
}
