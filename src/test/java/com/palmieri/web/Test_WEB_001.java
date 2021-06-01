package com.palmieri.web;

import com.palmieri.ManagementDriver;
import com.palmieri.Utility;
import org.junit.jupiter.api.*;
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

    @Test
    @DisplayName("Test 001")
    void test_001(){
        ManagementDriver.getDriver().get(webProp.getProperty("google.url"));
    }

    @AfterEach
    void tearDown() {}

    @AfterAll
    static void tearDownAll() {
        ManagementDriver.stopDriver();
    }
}
