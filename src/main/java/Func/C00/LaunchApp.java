package Func.C00;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.MobileCapabilityTypeEx;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class LaunchApp {
    public static AppiumDriver<MobileElement> getAppiumDriver() {
        AppiumDriver<MobileElement> appiumDriver = null;
        try {
//            Set DesiredCapabilities to send  to Appium server
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
            desiredCapabilities.setCapability(MobileCapabilityType.UDID, "2250F83B170285");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.metadata.busnoti_client");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.metadata.busnoti_client.MainActivity");

//            Setup the Appium serrver URL to connect to
            URL appiumServer = new URL("http://0.0.0.0:4723/wd/hub");
            appiumDriver = new AppiumDriver<MobileElement>(appiumServer, desiredCapabilities);

            appiumDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
            MobileElement allowBtn = appiumDriver.findElementByXPath("//android.widget.Button[@text='Cho phép']");
            allowBtn.click();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return appiumDriver;
    }
}
