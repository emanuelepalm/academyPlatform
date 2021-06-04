package com.palmieri.web;

import com.palmieri.ManagementDriver;
import com.palmieri.models.EbayProduct;
import com.palmieri.models.SelectMenuEbay;
import com.palmieri.steps.EbaySteps;
import com.palmieri.Utility;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.*;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertTrue;
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

    @Order(2)
    @ParameterizedTest(name = "q = {0}, c= {0}")
    @CsvSource({"iphone, cell phone","ipad, tablet"})
    @DisplayName("Controllare che esista il prodotto ricercato e la categoria selezionata")
    void test_002(String q, String c) {
        driver.get(webProp.getProperty("ebay.url"));
        ebaySteps.closeBanner(webProp);
        assertTrue(ebaySteps.selectCategory(webProp, c).isSelected());
        ebaySteps.search(webProp, q);
        assertTrue(driver.findElement(By.xpath(webProp.getProperty("xpath.span.selected.category"))).getText().toLowerCase().contains(c));
    }

    @Order(3)
    @ParameterizedTest(name = "q = {0}")
    @ValueSource(strings = {"iphone","ipad"})
    @DisplayName("Controllare che esista il prodotto ricercato (fluent wait)")
    void test_003(String q) {
        driver.get(webProp.getProperty("ebay.url"));
        ebaySteps.closeBannerFluent(webProp);
        ebaySteps.search(webProp, q);
        String result = driver.findElement(By.xpath(webProp.getProperty("xpath.span.result"))).getText();
        System.out.println(q + " trovati: " + result);
        if(result.length() < 1) {
            fail(q + " non presente");
        }
    }

    @Order(4)
    @Test
    @DisplayName("Controllo categorie con modello")
    void test_004() {
        driver.get(webProp.getProperty("ebay.url"));
        ebaySteps.getTabsAsObject(webProp.getProperty("id.select.category")).print();
    }

    @Order(5)
    @ParameterizedTest(name = "q = {0}")
    @ValueSource(strings = {"iphone","ipad"})
    @DisplayName("Controllare che esista il prodotto ricercato (fluent wait)")
    void test_005(String q) {
        driver.get(webProp.getProperty("ebay.url"));
        ebaySteps.search(webProp, q);
        for(EbayProduct p : ebaySteps.getProducts(webProp)) {
            p.print();
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
