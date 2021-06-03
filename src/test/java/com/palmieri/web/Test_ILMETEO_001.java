package com.palmieri.web;

import com.palmieri.IlmeteoSteps;
import com.palmieri.ManagementDriver;
import com.palmieri.EbaySteps;
import com.palmieri.Utility;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.*;

import java.awt.image.ByteLookupTable;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class Test_ILMETEO_001 {
    static private ManagementDriver managementDriver = null;
    static private WebDriver driver = null;
    static private IlmeteoSteps steps = null;
    static private Properties webProp = null;

    @BeforeAll
    static void beforeAll() {
        webProp = Utility.loadProp("ilmeteo");
        managementDriver.startDriver();
        driver = ManagementDriver.getDriver();
        steps = new IlmeteoSteps();
    }

    @BeforeEach
    void beforeEach() {
    }

    @Order(1)
    @ParameterizedTest(name = "q = {0}")
    @ValueSource(strings = {"Francavilla al Mare","xxxodmdfofpo"})
    @DisplayName("Controllare che esista il prodotto ricercato")
    void test_001(String q) {
        driver.get(webProp.getProperty("ilmeteo.url"));
        steps.closeBanner(webProp);
        steps.search(webProp, q);
        String result = driver.findElement(By.id(webProp.getProperty("id.title"))).getText();
        if(!result.contains(q.toUpperCase())) {
            fail();
        }
    }

    @Order(2)
    @Test
    @DisplayName("Check titoli")
    void test_003() throws InterruptedException {
        driver.get(webProp.getProperty("ilmeteo.url"));
        String handle = driver.getWindowHandle();
        for(WebElement element : steps.getMenuTabs(webProp,"id.page.menu")) {
            String elementText = element.getText();
            if (!elementText.equals("Home")) {
                String a = element.getAttribute("href");
                driver.switchTo().newWindow(WindowType.TAB);
                driver.get(a);
                Thread.sleep(4000);
                assertTrue(driver.findElement(By.xpath(webProp.getProperty("xpath.page.title"))).getText().toLowerCase().contains(elementText.toLowerCase()));
                driver.close();
                driver.switchTo().window(handle);
            }
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
