package com.palmieri.web;

import com.palmieri.DefaultChromeOptions;
import com.palmieri.ManagementDriver;
import com.palmieri.Utility;
import com.palmieri.steps.AndroidSteps;
import com.palmieri.steps.GoogleSteps;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static com.palmieri.GlobalParameters.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Test_MOBILE_001 {

    static private ManagementDriver managementDriver = null;
    static private AndroidDriver driver = null;
    static private Properties androidProp = null;
    static private String propname = "android";
    static private ExtentReports extentReports;
    static private ExtentTest extentTest;
    static private AndroidSteps steps;

    @BeforeAll
    static void beforeAll() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, " emulator-5554 ");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, RESOURCES_PATH + File.separator + "app" + EXT_ANDROID);
        ManagementDriver.startAppium(desiredCapabilities);
        extentReports = new ExtentReports(REPORT_PATH + File.separator + "report" + EXT_HTML, false);
        extentReports.loadConfig(new File(REPORT_CONFIG_XML));
        androidProp = Utility.loadProp(propname);
        driver = ManagementDriver.getAndroidDriver();
        steps = new AndroidSteps();
    }


    @BeforeEach
    void beforeEach() {
    }

    @Test
    @DisplayName("Login OK")
    void test_001(TestInfo testInfo)  {
        extentTest = extentReports.startTest(testInfo.getDisplayName());

    }


    @AfterEach
    void tearDown() {
        extentReports.endTest(extentTest);
    }

    @AfterAll
    static void tearDownAll() {
        //managementDriver.stopDriver();
        extentReports.flush();
    }
}
