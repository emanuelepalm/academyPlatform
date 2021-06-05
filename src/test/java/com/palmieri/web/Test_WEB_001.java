package com.palmieri.web;

import com.palmieri.ManagementDriver;
import com.palmieri.Utility;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static javax.swing.UIManager.put;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Test_WEB_001 {

    static private ManagementDriver managementDriver = null;
    static private ChromeDriver driver = null;
    static private Properties webProp = null;

    @BeforeAll
    static void beforeAll() {
        webProp = Utility.loadProp("web");
        managementDriver.startDriver();
        driver = ManagementDriver.getDriver();
    }

    @BeforeEach
    void beforeEach() {
    }

    @Order(1)
    @Test
    @DisplayName("Simulazioni tasti navigazione browser")
    void test_001() {
        String title = "";
        String currentUrl = "";

        driver.get(webProp.getProperty("google.url"));
        driver.get(webProp.getProperty("ebay.url"));

        title = driver.getTitle();
        currentUrl = driver.getCurrentUrl();

        System.out.println("title = " + title);
        System.out.println("current url = " + currentUrl);

        driver.navigate().back();

        title = driver.getTitle();
        currentUrl = driver.getCurrentUrl();

        System.out.println("title = " + title);
        System.out.println("current url = " + currentUrl);
        driver.navigate().forward();
        driver.navigate().refresh();

    }

    @Order(2)
    @Test
    @DisplayName("Test Window Browser")
    void test_002() {
        String handleWindow = "";
        int width = 0;
        int height = 0;
        int x = 0;
        int y = 0;

        driver.get(webProp.getProperty("ebay.url"));

        handleWindow = driver.getWindowHandle();
        width = driver.manage().window().getSize().getWidth();
        height = driver.manage().window().getSize().getHeight();
        x = driver.manage().window().getPosition().getX();
        y = driver.manage().window().getPosition().getY();

        System.out.println("handle Window = " + handleWindow);
        System.out.println("width = " + width);
        System.out.println("height = " + height);
        System.out.println("Pos x = " + x);
        System.out.println("Pos y = " + y);

        driver.manage().window().setSize(new Dimension(1024, 768));
        driver.manage().window().setPosition(new Point(500, 0));
        driver.manage().window().minimize();
        driver.manage().window().maximize();
        driver.manage().window().fullscreen();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(webProp.getProperty("google.url"));
        driver.close();
        driver.switchTo().window(handleWindow);

        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get(webProp.getProperty("google.url"));
        driver.close();
        driver.switchTo().window(handleWindow);
    }

    @Order(3)
    @Test
    @DisplayName("TestScreenshot")
    void test_003(TestInfo testInfo) {
        driver.get(webProp.getProperty("google.url"));
        Utility.Screenshot(driver,testInfo.getDisplayName() );
    }

    @Order(4)
    @Test
    @DisplayName("Test Geolocalizzazione")
    void test_004() {
            Map coordinates = new HashMap()
            {{
                put("latitude", 42.460790);
                put("longitude", 14.213230);
                put("accuracy", 1);
            }};
            driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
            driver.get("http://maps.google.it");
    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void tearDownAll() {
        managementDriver.stopDriver();
    }

}
