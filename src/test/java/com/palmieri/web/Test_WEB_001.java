package com.palmieri.web;

import com.palmieri.ManagementDriver;
import com.palmieri.Utility;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Properties;

import static com.palmieri.GlobalParameters.CHROME_DRIVER_PATH;
import static com.palmieri.GlobalParameters.PROPERTIES_PATH;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Test_WEB_001 {

    static Properties webProp = null;

    @BeforeAll
    static void beforeAll(){
        webProp = Utility.loadProp("web");
        ManagementDriver.startDriver();
    }

    @BeforeEach
    void beforeEach() {}

    @Order(1)
    @Test
    @DisplayName("Simulazioni tasti navigazione browser")
    void test_001(){
        String title = "";
        String currentUrl = "";

        ManagementDriver.getDriver().get(webProp.getProperty("google.url"));
        ManagementDriver.getDriver().get(webProp.getProperty("ebay.url"));

        title = ManagementDriver.getDriver().getTitle();
        currentUrl = ManagementDriver.getDriver().getCurrentUrl();

        System.out.println("title = " + title );
        System.out.println("current url = " + currentUrl );

        ManagementDriver.getDriver().navigate().back();

        title = ManagementDriver.getDriver().getTitle();
        currentUrl = ManagementDriver.getDriver().getCurrentUrl();

        System.out.println("title = " + title );
        System.out.println("current url = " + currentUrl );
        ManagementDriver.getDriver().navigate().forward();
        ManagementDriver.getDriver().navigate().refresh();

    }

    @Order(2)
    @Test
    @DisplayName("Test Window Browser")
    void test_002(){
        String handleWindow = "";
        int width = 0;
        int height = 0;
        int x = 0;
        int y = 0;

        ManagementDriver.getDriver().get(webProp.getProperty("ebay.url"));

        handleWindow = ManagementDriver.getDriver().getWindowHandle();
        width = ManagementDriver.getDriver().manage().window().getSize().getWidth();
        height = ManagementDriver.getDriver().manage().window().getSize().getHeight();
        x = ManagementDriver.getDriver().manage().window().getPosition().getX();
        y = ManagementDriver.getDriver().manage().window().getPosition().getY();

        System.out.println("handle Window = " + handleWindow);
        System.out.println("width = " + width);
        System.out.println("height = " + height);
        System.out.println("Pos x = " + x);
        System.out.println("Pos y = " + y);

        ManagementDriver.getDriver().manage().window().setSize(new Dimension(1024,768));
        ManagementDriver.getDriver().manage().window().setPosition(new Point(500,0));
        ManagementDriver.getDriver().manage().window().minimize();
        ManagementDriver.getDriver().manage().window().maximize();
        ManagementDriver.getDriver().manage().window().fullscreen();
        ManagementDriver.getDriver().switchTo().newWindow(WindowType.TAB);
        ManagementDriver.getDriver().get(webProp.getProperty("google.url"));
        ManagementDriver.getDriver().close();
        ManagementDriver.getDriver().switchTo().window(handleWindow);

        ManagementDriver.getDriver().switchTo().newWindow(WindowType.WINDOW);
        ManagementDriver.getDriver().get(webProp.getProperty("google.url"));
        ManagementDriver.getDriver().close();
    }

    @AfterEach
    void tearDown() {}

    @AfterAll
    static void tearDownAll() {
       // ManagementDriver.stopDriver();
    }
}
