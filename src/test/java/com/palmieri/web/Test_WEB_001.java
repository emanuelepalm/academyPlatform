package com.palmieri.web;

import com.palmieri.DefaultChromeOptions;
import com.palmieri.ManagementDriver;
import com.palmieri.toolbox.Screen;
import com.palmieri.steps.WebSteps;
import com.palmieri.toolbox.Utils;
import org.junit.Ignore;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Properties;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Test_WEB_001 {

    static private ManagementDriver managementDriver = null;
    static private ChromeDriver driver = null;
    static private Properties webProp = null;
    static private WebSteps steps = null;
    static private DefaultChromeOptions defaultChromeOptions = null;

    @BeforeAll
    static void beforeAll() {
        webProp = Utils.loadProp("web");
        managementDriver.startDriver(defaultChromeOptions);
        driver = ManagementDriver.getChromeDriver();
        steps = new WebSteps();
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

        driver.get(webProp.getProperty("ebay.url")) ;

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
  //      driver.manage().window().minimize();
        driver.manage().window().maximize();
        driver.manage().window().fullscreen();
    //    driver.switchTo().newWindow(WindowType.TAB);
        driver.get(webProp.getProperty("google.url"));
        driver.close();
        driver.switchTo().window(handleWindow);

      //  driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get(webProp.getProperty("google.url"));
        driver.close();
        driver.switchTo().window(handleWindow);
    }

    @Order(3)
    @Test
    @DisplayName("TestScreenshot")
    void test_003(TestInfo testInfo) {
        driver.get(webProp.getProperty("google.url"));
        Screen.Screenshot(driver,testInfo.getDisplayName() );
    }
    @Ignore
    @Order(4)
    @ParameterizedTest
    @CsvSource({"37.89703180341463, 41.12869044940056"})
    @DisplayName("Test Geolocalizzazione")
    void test_004(double o, double o2) throws InterruptedException {
      //  driver.executeCdpCommand("Emulation.setGeolocationOverride", Screen.createMap(o, o2));
        driver.navigate().to("https://www.google.com/maps");
        steps.clickOnButtonByXpath(webProp.getProperty("xpath.btn.accept.maps"));
        steps.clickOnButtonByXpath(webProp.getProperty("xpath.btn.my.location"));
        Thread.sleep(7000);
        Screen.Screenshot(driver,"TestGeolocalizzazione");
    }

    @Order(5)
    @Test
    @DisplayName("Test Colore")
    void test_005() throws InterruptedException {
        driver.navigate().to("https://www.google.com/");
        steps.clickOnButtonByXpath(webProp.getProperty("xpath.btn.google.accept"));
        assertTrue(driver.findElement(By.xpath(webProp.getProperty("xpath.accedi.button"))).getCssValue("background-color").equals("rgba(26, 115, 232, 1)"));
    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void tearDownAll() {
        managementDriver.stopDriver();
    }

}
