package com.palmieri.mobile;

import com.palmieri.ManagementDriver;
import com.palmieri.interfaces.ISteps;
import com.palmieri.steps.mobile.BershkaSteps;
import com.palmieri.toolbox.ReportManager;
import com.palmieri.toolbox.Screen;
import com.palmieri.toolbox.Utils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.Properties;

import static com.palmieri.GlobalParameters.*;
import static org.junit.jupiter.api.Assertions.fail;

public class Test_BERSHKA_001 {

    static private ManagementDriver managementDriver = null;
    static private AndroidDriver driver = null;
    static private Properties androidProp = null;
    static private String propname = "zara";
    static private ExtentReports extentReports;
    static private ExtentTest extentTest;
    static private BershkaSteps steps;
    private ReportManager repo;

    @BeforeAll
    static void beforeAll() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, " emulator-5554 ");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, RESOURCES_PATH + File.separator + "Zara_v10.25.0_apkpure.com" + EXT_ANDROID);
        ManagementDriver.startAppium(desiredCapabilities);
        extentReports = new ExtentReports(REPORT_PATH + File.separator + "reportTemplate" + EXT_HTML, false);
        extentReports.loadConfig(new File(REPORT_CONFIG_XML));
        androidProp = Utils.loadProp(propname);
        driver = ManagementDriver.getAndroidDriver();
        steps = new BershkaSteps();
    }

    @BeforeEach
    void beforeEach() {
    }

    @Test
    @DisplayName("Arrivare alla Home")
    void test_001(TestInfo testInfo)  {
        extentTest = extentReports.startTest(testInfo.getDisplayName());
        repo = new ReportManager(extentTest, androidProp, steps);
        try {
            steps.getToHome(androidProp);
        }catch (Exception e) {
            extentTest.log(LogStatus.ERROR, "Step: " + steps.getStepName() + "\nErrore: " +  e.getMessage(), extentTest.addBase64ScreenShot(Screen.getBase64MobileScreenshot()));
            fail();
        }
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
