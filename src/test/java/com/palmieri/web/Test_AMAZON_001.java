package com.palmieri.web;

import com.palmieri.DefaultChromeOptions;
import com.palmieri.ManagementDriver;
import com.palmieri.Utility;
import com.palmieri.steps.AmazonSteps;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.Properties;

import static com.palmieri.GlobalParameters.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class Test_AMAZON_001 {

        static private ManagementDriver managementDriver = null;
        static private ChromeDriver driver = null;
        static private Properties webProp = null;
        static private AmazonSteps steps = null;
        static private DefaultChromeOptions defaultChromeOptions = null;
        static private String propname = "amazon";
        static private ExtentReports extentReports;
        static private ExtentTest extentTest;

        @BeforeAll
        static void beforeAll() {

            defaultChromeOptions = new DefaultChromeOptions(new ChromeOptions());
            ManagementDriver.setMobile(false);
            if(ManagementDriver.isMobile()) {
                propname = propname + ".mobile";
                defaultChromeOptions.addArguments("--window-size=375,812");
                defaultChromeOptions.addArguments("--user-agent=Mozilla/5.0 (iPhone; CPU iPhone OS 10_3 like Mac OS X) AppleWebKit/602.1.50 (KHTML, like Gecko) CriOS/56.0.2924.75 Mobile/14E5239e Safari/602.1");
            }
            extentReports = new ExtentReports(REPORT_PATH + File.separator + "report" + EXT_HTML, true);
            extentReports.loadConfig(new File(REPORT_CONFIG_XML));
            webProp = Utility.loadProp(propname);
            managementDriver.startDriver(defaultChromeOptions);
            driver = ManagementDriver.getChromeDriver();
            steps = new AmazonSteps();
        }

        @BeforeEach
        void beforeEach() {
        }

        @Test
        @DisplayName("Test Amazon")
        void test_001(TestInfo testInfo) {
            try {
                extentTest = extentReports.startTest(testInfo.getDisplayName());
                driver.navigate().to(webProp.getProperty("amazon.url"));
                extentTest.log(LogStatus.INFO, "Apro Amazon", " ");
                steps.search(webProp, "iphone");
                extentTest.log(LogStatus.INFO, "Cerco iphone", extentTest.addBase64ScreenShot(Utility.getBase64MobileScreenshot()));
                steps.orderBy(webProp);
                extentTest.log(LogStatus.INFO, "Ordino per ultimi arrivati", extentTest.addBase64ScreenShot(Utility.getBase64MobileScreenshot()));
                steps.selectBrand(webProp);
                extentTest.log(LogStatus.INFO, "Seleziono Brand Apple", extentTest.addBase64ScreenShot(Utility.getBase64MobileScreenshot()));
                steps.selectProduct(webProp, 2);
            }
            catch (Exception e) {
                System.err.println(e.getMessage());
                extentTest.log(LogStatus.FAIL,e.getMessage(),extentTest.addBase64ScreenShot(Utility.getBase64Screenshot()));
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
