package utils;

public class ElementXPath {
    // Creds
    public static final String validEmail = "1obmd@fthcapital.com";
    public static final String validPassword = "@Test123";
    public static final String popUpLoginNotSuccessXpath = "//android.widget.TextView[contains(@text, 'Incorrect username or password')]";
    // Spash screen
    public static final String signInBtnId = "Sign In";                                              // content-desc: Sign In
    public static final String signUpTxtId = "Create One";                                           //content-desc: Create One
    // Login form
    public static final String emailTextBoxXpath = "//android.widget.EditText[@text='example@gmail.com']";
    public static final String passwordTextBoxXpath = "android.widget.EditText"; // class: android.widget.ImageView (1st class in 2 class) // xpath "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.widget.EditText";
    public static final String togglePasswordSignInClass = "android.widget.ImageView"; // class: android.widget.ImageView (2nd class in 3 class)
    public static final String submitSignInId = "Submit";                                            // content-desc: Submit
    public static final String forgotPasswordId = "Forgot Password?";                                // content-desc: Forgot Password?
    public static final String emailHelperText = "Please enter your email";
    public static final String passwordHelperText = "Minimum 8 characters";
    // Sign Up form
    public static final String nameTextBoxXpath = "android.widget.EditText"; // 4 class name email pw repeat pw có chung class name
    public static final String emailSignUpTextBoxXpath = "android.widget.EditText";
    public static final String passwordlSignUpTextBoxXpath = "android.widget.EditText";
    public static final String repeatPasswordlSignUpTextBoxXpath = "android.widget.EditText";
    public static final String submitSignUpBtnId = "Submit";                                                 // content-desc: Submit
    public static final String togglePasswordSignUpClass = "android.widget.ImageView";

    // Navigation bar
    public static final String navBarHomeIconId = "Home"; // content-desc: Home
    public static final String navBarChildIconId = "Child"; // content-desc: Child
    public static final String navBarTrackingIconXpath = "Tracking"; // content-desc: Tracking
    public static final String navBarProfileIconXpath = "Profile"; // content-desc: Profile

}
