package com.palmieri.web;

import com.palmieri.DefaultChromeOptions;
import com.palmieri.ManagementDriver;
import com.palmieri.Utility;
import com.palmieri.steps.GoogleSteps;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.Properties;

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

    @BeforeAll
    static void beforeAll() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, " emulator-5554 ");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, RESOURCES_PATH + File.separator + "app" + EXT_ANDROID);
        ManagementDriver.startAppium("android",desiredCapabilities);
        extentReports = new ExtentReports(REPORT_PATH + File.separator + "report" + EXT_HTML, false);
        extentReports.loadConfig(new File(REPORT_CONFIG_XML));
        androidProp = Utility.loadProp(propname);
        driver = ManagementDriver.getAndroidDriver();
    }


    @BeforeEach
    void beforeEach() {
    }

    @Test
    @DisplayName("Test Screenshot Google")
    void test_001(TestInfo testInfo)  {

    }


    @AfterEach
    void tearDown() {
        extentReports.endTest(extentTest);
    }

    @AfterAll
    static void tearDownAll() {
        managementDriver.stopDriver();
        extentReports.flush();
    }
}
