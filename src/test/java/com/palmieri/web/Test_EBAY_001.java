package com.palmieri.web;

import com.palmieri.ManagementDriver;
import com.palmieri.EbaySteps;
import com.palmieri.Utility;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.*;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Test_EBAY_001 {

    static private ManagementDriver managementDriver = null;
    static private WebDriver driver = null;
    static private EbaySteps ebaySteps = null;
    static private WebElement webElement = null;
    static private Properties webProp = null;

    @BeforeAll
    static void beforeAll() {
        webProp = Utility.loadProp("ebay");
        managementDriver.startDriver();
        driver = ManagementDriver.getDriver();
        ebaySteps = new EbaySteps();
    }

    @BeforeEach
    void beforeEach() {
    }

    @Order(1)
    @ParameterizedTest(name = "q = {0}")
    @ValueSource(strings = {"iphone","ipad"})
    @DisplayName("Controllare che esista il prodotto ricercato")
    void test_001(String q) {
        driver.get(webProp.getProperty("ebay.url"));
        ebaySteps.closeBanner(webProp);
        ebaySteps.search(webProp, q);
        String result = driver.findElement(By.xpath(webProp.getProperty("xpath.span.result"))).getText();
        System.out.println(q + " trovati: " + result);
        if(result.length() < 1) {
            fail(q + " non presente");
        }
    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void tearDownAll() {
        managementDriver.stopDriver();
    }

}
