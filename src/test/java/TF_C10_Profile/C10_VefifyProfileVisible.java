package TF_C10_Profile;

import Action.ScrollDown90Percent;
import DB.Parent_GetDataWithEmail;
import Func.C00.LaunchApp;
import Func.C02.SignInForm;
import Func.C10_Profile.Verifier;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class C10_VefifyProfileVisible {


    private AppiumDriver<MobileElement> appiumDriver;
    private WebDriverWait wait;
    private SignInForm signInForm;
    private String checkAccount = "1obmd@fthcapital.com";
    private String passwordAcc = "@Test123";
    private ScrollDown90Percent scrollDown;

    @BeforeClass
    public void setup() {
        appiumDriver = LaunchApp.getAppiumDriver();
        wait = new WebDriverWait(appiumDriver, 10);
        signInForm = new SignInForm(appiumDriver);
        // Sign in
        signInForm.clickSignInButton();
        signInForm.inputEmail(checkAccount);
        signInForm.inputPassword(passwordAcc);
        signInForm.clickSubmitButton();
        // Click on TRACKING on navigation bar
        MobileElement profileNav = appiumDriver.findElementByAccessibilityId("Profile");
        profileNav.click();
    }

    @Test
    public void TC001() {
        // Verify avatar
        By avtSel = MobileBy.className("android.widget.ImageView");
        wait.until(ExpectedConditions.visibilityOfElementLocated(avtSel));
        List<MobileElement> avtElement = appiumDriver.findElements(avtSel);
        int AVATAR = 0;
        MobileElement avatar = avtElement.get(AVATAR);
        Assert.assertTrue(avatar.isDisplayed(), "Avatar is not displayed!");
        if (avatar.isDisplayed()) {
            System.out.println("Avatar is displayed!");
        }
        // Verify other elements
        scrollDown = new ScrollDown90Percent(appiumDriver);
        scrollDown.scrollDownTo();
        String[] elements = {
                "ID", "Name", "Email", "Date of birthday", "Change password", "Language",
                "About Kidscommutesafe", "Term of use", "Private Policy", "Help", "Logout"
        };
        for (String element : elements) {
            MobileElement profileElement = appiumDriver.findElement(By.xpath("//android.widget.TextView[contains(@text, '" + element + "')]"));
            Assert.assertTrue(profileElement.isDisplayed(), element + "is not displayed!");
            System.out.println(element + " is displayed!");
        }
    }

    @Test
    public void verifyProfileDataWithDB() {
        try {
            ResultSet resultSet = Parent_GetDataWithEmail.getParentData(checkAccount);
            if (resultSet.next()) {
                String dbName = resultSet.getString("user_name");
                String dbEmail = resultSet.getString("email");
                String dbDOB = resultSet.getString("date_of_birth");
                String dbLanguage = resultSet.getString("language");
                // Convert database date format to profile date format (dd/MM/yyyy)
                LocalDate dbDate = LocalDate.parse(dbDOB, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX"));
                String formattedDOB = dbDate.format(DateTimeFormatter.ofPattern("dd/M/yyyy"));
                // Language
                String dbLanguageText;
                if (dbLanguage.equals("en")) {
                    dbLanguageText = "English";
                } else if (dbLanguage.equals("cn")) {
                    dbLanguageText = "中文";
                } else {
                    dbLanguageText = null;
                }

                // Get Name & DOB from the profile
                MobileElement nameElement = appiumDriver.findElementByClassName("android.widget.EditText");
                By uiSel = MobileBy.className("android.widget.TextView");
                wait.until(ExpectedConditions.visibilityOfElementLocated(uiSel));
                List<MobileElement> uiList = appiumDriver.findElements(uiSel);

                MobileElement emailElement = uiList.get(5); // sixth 5
                MobileElement dobElement = uiList.get(8); // ninth 8
                MobileElement languageElement = uiList.get(11); // twelfth 11

                String uiName = nameElement.getText();
                String uiEmail = emailElement.getText();
                String uiDOB = dobElement.getText();
                String uiLanguage = languageElement.getText();


                // Verify and print errors directly
                Verifier.verifyAndPrintError("Name", dbName, uiName);
                Verifier.verifyAndPrintError("Email", dbEmail, uiEmail);
                Verifier.verifyAndPrintError("DOB", formattedDOB, uiDOB);
                Verifier.verifyAndPrintError("Language", dbLanguageText, uiLanguage);

                // Verify
//                Assert.assertEquals(dbName, uiName, "Name does not match with the databases!");
//                Assert.assertEquals(dbEmail, uiEmail, "Email does not match with the databases!");
//                Assert.assertEquals(dbDOB, uiDOB, "DOB does not match with the databases!");
//                Assert.assertEquals(dbLanguage, uiLanguage, "Language does not match with the databases!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
/*
Xác minh Profile hiển thị đầy đủ theo requirement (Ngoại trừ avatar có dạng ảnh, các element còn lại đều ở dạng text):
- Avatar
- ID: [mã ID]
- Name: [name]
- Email: [email]
- DOB: [ngày sinh]
- Change password
- Language
- About Kidscommutesafe
- Term of use
- Private Policy
- Help
- Logout

Testcase 1: Xác minh Profile hiển thị các mục trên
Testcase 2: Dựa vào email, đối chiếu các mục Name, DOB có khớp với database table Parent hay không
 */
