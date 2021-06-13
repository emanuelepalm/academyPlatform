package com.palmieri.web;

import com.palmieri.ManagementDriver;
import com.palmieri.toolbox.Screen;
import com.palmieri.steps.mobile.AndroidSteps;
import com.palmieri.toolbox.Utils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.List;
import java.util.Properties;

import static com.palmieri.GlobalParameters.*;
import static org.junit.jupiter.api.Assertions.*;


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
        androidProp = Utils.loadProp(propname);
        driver = ManagementDriver.getAndroidDriver();
        steps = new AndroidSteps();
    }


    @BeforeEach
    void beforeEach() {
    }

    @ParameterizedTest(name = "Login Ok")
    @CsvSource({"admin ,benvenuto: admin"})
    @DisplayName("Login OK")
    void test_001(String login,String assertion, TestInfo testInfo)  {
        extentTest = extentReports.startTest(testInfo.getDisplayName());
        try {
            steps.reset(androidProp);
            steps.insertUserName(androidProp, login);
            extentTest.log(LogStatus.INFO, steps.getStepName(), extentTest.addBase64ScreenShot(Screen.getBase64MobileScreenshot()));
            steps.insertPassword(androidProp, login);
            extentTest.log(LogStatus.INFO, steps.getStepName(), extentTest.addBase64ScreenShot(Screen.getBase64MobileScreenshot()));
            steps.clickOnButton("id.btn.login",androidProp.getProperty("id.btn.login"));
            extentTest.log(LogStatus.INFO, steps.getStepName(), extentTest.addBase64ScreenShot(Screen.getBase64MobileScreenshot()));
            assertTrue(ManagementDriver.waitUntilDisplayed('i',androidProp.getProperty("id.welcome.text")).getText().toLowerCase().contains(assertion));
            extentTest.log(LogStatus.PASS, "Login Effettuato con Successo", extentTest.addBase64ScreenShot(Screen.getBase64MobileScreenshot()));
            steps.clickOnButton("xpath.btn.logout",androidProp.getProperty("xpath.btn.logout"));
        }
        catch (Exception e) {
            extentTest.log(LogStatus.ERROR, "Step: " + steps.getStepName() + "\nErrore: " +  e.getMessage(), extentTest.addBase64ScreenShot(Screen.getBase64MobileScreenshot()));
            fail();
        }
    }

    @ParameterizedTest(name = "Login Solo Username")
    @CsvSource({"admin, password"})
    @DisplayName("Login Solo Username")
    void test_002(String login,String assertion, TestInfo testInfo)  {
        extentTest = extentReports.startTest(testInfo.getDisplayName());
        try {
            steps.reset(androidProp);
            steps.insertUserName(androidProp, login);
            extentTest.log(LogStatus.INFO, steps.getStepName(), extentTest.addBase64ScreenShot(Screen.getBase64MobileScreenshot()));
            steps.clickOnButton("id.btn.login",androidProp.getProperty("id.btn.login"));
            extentTest.log(LogStatus.INFO, steps.getStepName(), extentTest.addBase64ScreenShot(Screen.getBase64MobileScreenshot()));
            assertTrue(ManagementDriver.waitUntilDisplayed('i',androidProp.getProperty("id.msg.error")).getText().toLowerCase().contains(assertion));
            extentTest.log(LogStatus.PASS, "Messaggio di errore contenente: " + assertion, extentTest.addBase64ScreenShot(Screen.getBase64MobileScreenshot()));
            steps.clickOnButton("id.ok.error",androidProp.getProperty("id.ok.error"));
        }
        catch (Exception e) {
            extentTest.log(LogStatus.ERROR, "Step: " + steps.getStepName() + "\nErrore: " +  e.getMessage(), extentTest.addBase64ScreenShot(Screen.getBase64MobileScreenshot()));
            fail();
        }
    }

    @ParameterizedTest(name = "Login Solo Password")
    @CsvSource({"admin, username"})
    @DisplayName("Login Solo Password")
    void test_003(String login,String assertion, TestInfo testInfo)  {
        extentTest = extentReports.startTest(testInfo.getDisplayName());
        try {
            steps.reset(androidProp);
            steps.insertPassword(androidProp, login);
            extentTest.log(LogStatus.INFO, steps.getStepName(), extentTest.addBase64ScreenShot(Screen.getBase64MobileScreenshot()));
            steps.clickOnButton("id.btn.login",androidProp.getProperty("id.btn.login"));
            extentTest.log(LogStatus.INFO, steps.getStepName(), extentTest.addBase64ScreenShot(Screen.getBase64MobileScreenshot()));
            assertTrue(ManagementDriver.waitUntilDisplayed('i',androidProp.getProperty("id.msg.error")).getText().toLowerCase().contains(assertion));
            extentTest.log(LogStatus.PASS, "Messaggio di errore contenente: " + assertion, extentTest.addBase64ScreenShot(Screen.getBase64MobileScreenshot()));
            steps.clickOnButton("id.ok.error",androidProp.getProperty("id.ok.error"));
        }
        catch (Exception e) {
            extentTest.log(LogStatus.ERROR, "Step: " + steps.getStepName() + "\nErrore: " +  e.getMessage(), extentTest.addBase64ScreenShot(Screen.getBase64MobileScreenshot()));
            fail();
        }
    }

    @ParameterizedTest(name = "Aggiungo un utente")
    @CsvSource({"admin ,benvenuto: admin, pippo"})
    @DisplayName("Aggiungo un utente")
    void test_004(String login,String assertion,String name, TestInfo testInfo)  {
        extentTest = extentReports.startTest(testInfo.getDisplayName());
        try {
            steps.reset(androidProp);
            steps.insertUserName(androidProp, login);
            extentTest.log(LogStatus.INFO, steps.getStepName(), extentTest.addBase64ScreenShot(Screen.getBase64MobileScreenshot()));
            steps.insertPassword(androidProp, login);
            extentTest.log(LogStatus.INFO, steps.getStepName(), extentTest.addBase64ScreenShot(Screen.getBase64MobileScreenshot()));
            steps.clickOnButton("id.btn.login",androidProp.getProperty("id.btn.login"));
            extentTest.log(LogStatus.INFO, steps.getStepName(), extentTest.addBase64ScreenShot(Screen.getBase64MobileScreenshot()));
            assertTrue(ManagementDriver.waitUntilDisplayed('i',androidProp.getProperty("id.welcome.text")).getText().toLowerCase().contains(assertion));
            steps.clickOnButton("id.btn.add",androidProp.getProperty("id.btn.add"));
            extentTest.log(LogStatus.INFO, steps.getStepName(), extentTest.addBase64ScreenShot(Screen.getBase64MobileScreenshot()));
            steps.insertName(androidProp, name);
            extentTest.log(LogStatus.INFO, steps.getStepName(), extentTest.addBase64ScreenShot(Screen.getBase64MobileScreenshot()));
            steps.clickOnButton("id.confirm.add",androidProp.getProperty("id.confirm.add"));
            extentTest.log(LogStatus.INFO, steps.getStepName(), extentTest.addBase64ScreenShot(Screen.getBase64MobileScreenshot()));
            List<WebElement> contattiList = steps.getContattiList(androidProp);
            assertEquals(contattiList.get(contattiList.size()-1).getText(),name);
            extentTest.log(LogStatus.PASS, "Contatto Aggiunto con Successo", extentTest.addBase64ScreenShot(Screen.getBase64MobileScreenshot()));
            steps.clickOnButton("xpath.btn.logout",androidProp.getProperty("xpath.btn.logout"));
        }
        catch (Exception e) {
            extentTest.log(LogStatus.ERROR, "Step: " + steps.getStepName() + "\nErrore: " +  e.getMessage(), extentTest.addBase64ScreenShot(Screen.getBase64MobileScreenshot()));
            fail();
        }
    }

    @ParameterizedTest(name = "Elimina utenti")
    @CsvSource({"admin ,benvenuto: admin"})
    @DisplayName("Elimina utenti")
    void test_004(String login,String assertion, TestInfo testInfo)  {
        extentTest = extentReports.startTest(testInfo.getDisplayName());
        try {
            steps.insertUserName(androidProp, login);
            extentTest.log(LogStatus.INFO, steps.getStepName(), extentTest.addBase64ScreenShot(Screen.getBase64MobileScreenshot()));
            steps.insertPassword(androidProp, login);
            extentTest.log(LogStatus.INFO, steps.getStepName(), extentTest.addBase64ScreenShot(Screen.getBase64MobileScreenshot()));
            steps.clickOnButton("id.btn.login",androidProp.getProperty("id.btn.login"));
            extentTest.log(LogStatus.INFO, steps.getStepName(), extentTest.addBase64ScreenShot(Screen.getBase64MobileScreenshot()));
            assertTrue(ManagementDriver.waitUntilDisplayed('i',androidProp.getProperty("id.welcome.text")).getText().toLowerCase().contains(assertion));
            steps.clickOnButton("id.btn.delAll",androidProp.getProperty("id.btn.delAll"));
            extentTest.log(LogStatus.INFO, steps.getStepName(), extentTest.addBase64ScreenShot(Screen.getBase64MobileScreenshot()));
            steps.clickOnButton("id.ok.delAll",androidProp.getProperty("id.ok.delAll"));
            extentTest.log(LogStatus.INFO, steps.getStepName(), extentTest.addBase64ScreenShot(Screen.getBase64MobileScreenshot()));
            assertTrue(steps.getContattiList(androidProp).isEmpty());
            steps.clickOnButton("xpath.btn.logout",androidProp.getProperty("xpath.btn.logout"));
        }
        catch (Exception e) {
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
        managementDriver.stopDriver();
        extentReports.flush();
    }
}
