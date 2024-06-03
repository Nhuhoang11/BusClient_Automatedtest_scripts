package TF_C11_Child;

import Action.ScrollDown90Percent;
import DB.Parent_GetChild;
import Func.C00.LaunchApp;
import Func.C02.SignInForm;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.*;

import java.util.List;

public class C11_VerifyChilds {
    private AppiumDriver<MobileElement> appiumDriver;
    private WebDriverWait wait;
    private SignInForm signInForm;
    private String parentEmail = "1obmd@fthcapital.com";
    private String passwordAcc = "@Test123";
    private ScrollDown90Percent scrollDown;

    @BeforeClass
    public void setup() {
        appiumDriver = LaunchApp.getAppiumDriver();
        wait = new WebDriverWait(appiumDriver, 10);
        signInForm = new SignInForm(appiumDriver);
        // Sign in
        signInForm.clickSignInButton();
        signInForm.inputEmail(parentEmail);
        signInForm.inputPassword(passwordAcc);
        signInForm.clickSubmitButton();
        // Click on CHILD on navigation bar
        MobileElement ChildNav = appiumDriver.findElementByAccessibilityId("Child");
        ChildNav.click();
    }

    @Test
    public void TC001_VerfiyChildsDisplay() {
        // Verify that all child of Parent have to displayed in this session.
        try {
            List<String[]> childrenFromDB = Parent_GetChild.getChildNameAndNickname(parentEmail);
//            Parent_GetChild.getChildNameAndNickname(parentEmail);
            By childSel = MobileBy.xpath("//android.widget.TextView[contains(@text, '(')]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(childSel));
            List<MobileElement> childList = appiumDriver.findElements(childSel);
            // Get childs from CHILD
            for (int i = 0; i < childList.size(); i++) {
                String uiChildName = childList.get(i).getText();
                System.out.println("Child name: " + uiChildName);

                Assert.assertTrue((i < childrenFromDB.size()), "Không có dữ liệu child nào trong cơ sở dữ liệu để so sánh.");
//              // Tạo chuỗi so sánh từ dữ liệu cơ sở dữ liệu
                String dbChildName = childrenFromDB.get(i)[0];
                String dbChildNickname = childrenFromDB.get(i)[2];
                String dbChildFullName = dbChildName + childrenFromDB.get(i)[1] + dbChildNickname + childrenFromDB.get(i)[3];

//                Assert.assertEquals(uiChildName, dbChildFullName, "Child Name không khớp với cơ sở dữ liệu.");

                if (uiChildName.equals(dbChildFullName)) {
                    System.out.println("PASS - Expected: " + dbChildFullName + ", Actual: " + uiChildName);
                } else {
                    System.out.println("FAIL - Expected: " + dbChildFullName + ", Actual: " + uiChildName);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
/*
The Second (007)
The Second (007)
 */