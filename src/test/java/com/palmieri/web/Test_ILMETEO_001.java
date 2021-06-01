package com.palmieri.web;

import com.palmieri.IlmeteoSteps;
import com.palmieri.ManagementDriver;
import com.palmieri.EbaySteps;
import com.palmieri.Utility;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
    void test_002() throws InterruptedException {
        driver.get(webProp.getProperty("ilmeteo.url"));

        for(int i = 2; i<steps.getMenuTabs(webProp).size(); i++) {
            WebElement element = steps.getMenuTabs(webProp).get(i);
            element = driver.findElement(By.id("tab"+i));
            element.click();
                element = driver.findElement(By.id("tab"+i));
                Thread.sleep(4000);
                WebElement comparato = driver.findElement(By.xpath(webProp.getProperty("xpath.page.title")));
                assertTrue(comparato.getText().toLowerCase().contains(element.getText().toLowerCase()));
            System.out.println(element.getText());
            System.out.println(comparato.getText());
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
