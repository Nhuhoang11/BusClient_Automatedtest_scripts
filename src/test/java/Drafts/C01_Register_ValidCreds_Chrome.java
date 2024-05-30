package Drafts;

public class C01_Register_ValidCreds_Chrome {
    public static void main(String[] args) {
        System.out.println("Chrome chậm như rùa!!!");
    }
//    AppiumDriver<MobileElement> appiumDriver = null;
//    private WebDriverWait wait;
//    private SignUpForm signUpForm;
//    private LaunchChrome launchChrome;
//    private ScrollDown90Percent scrollDown;
//
//    @BeforeClass
//    public void setup() {
//        appiumDriver = LaunchApp.getAppiumDriver();
//        wait = new WebDriverWait(appiumDriver, 10);
//        signUpForm = new SignUpForm(appiumDriver);
//        signUpForm.clickSignUpText();
//        launchChrome = new LaunchChrome(appiumDriver);
//    }
//
//    @Test
//    public void inputValidCreds() throws IOException {
//        launchChrome.launchChrome();
//        // Get Email
//        String email = launchChrome.getEmail();
//        System.out.println("Email: " + email);
//
//        // Relaunch app BusClient
//        appiumDriver.activateApp("com.metadata.busnoti_client");
//        MobileElement alreadyHaveAnAcc = appiumDriver.findElementByXPath("//android.widget.TextView[contains(@text, 'Already have an account?')]");
//        wait.until(ExpectedConditions.visibilityOf(alreadyHaveAnAcc));
//
//        // Input credentials
//        signUpForm.inputName("Client test");
//        System.out.println("Input name");
//        signUpForm.inputEmail(email);
//        System.out.println("Input email");
//        signUpForm.inputPassword(ElementXPath.validPassword);
//        System.out.println("Input password");
//        signUpForm.inputRepeatPassword(ElementXPath.validPassword);
//        System.out.println("Input repeat password");
//        System.out.println("Click on submit");
//        signUpForm.clickSubmitButton();
//
//        // Verify OTP screen hiển thị sau khi users nhập các thông tin chính xác:
//        if (signUpForm.isOTPScreenDisplaying()) {
//            System.out.println("PASS - OTP screen is displaying!");
//        } else {
//            System.out.println("FAIL - Cannot register even if input valid credentials");
//        }
//        System.out.println("---------------------------------------------------------------------------");
//
//        // Relaunch chrome:
//        appiumDriver.activateApp("com.android.chrome");
////        MobileElement yourTempEmailAddText = appiumDriver.findElementByXPath("//android.widget.EditText[contains(@text, '@')]");
////        wait.until(ExpectedConditions.visibilityOf(yourTempEmailAddText));
//
//        scrollDown = new ScrollDown90Percent(appiumDriver);
//        scrollDown.scrollDownTo();
//        // Get OTP
//        String OTP = launchChrome.getOTP();
//        System.out.println("OTP: " + OTP);
//
//        // Relaunch app BusClient
//        appiumDriver.activateApp("com.metadata.busnoti_client");
//        // Input OTP & Submit OTP
//        signUpForm.inputOTPandSubmit(OTP);
//
//        //Verify successfully register
//        signUpForm.verifyRegisterSuccess();
//        if (signUpForm.verifyRegisterSuccess()) {
//            System.out.println("PASS - Successfully register!");
//        } else {
//            System.out.println("FAIL - Register failed even if input valid credentials!");
//        }
//
//    }
}
