package com.palmieri.steps;

import com.palmieri.ManagementDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class IlMeteoSteps {
    private WebElement webElement;
    private WebDriver driver = ManagementDriver.getDriver();

    public void search(Properties prop, String q) {
        webElement = driver.findElement(By.id(prop.getProperty("id.input.search")));
        webElement.clear();
        webElement.sendKeys(q);
        driver.findElement(By.id(prop.getProperty("id.btn.search"))).click();
    }

    public void closeBanner(Properties prop) {
        try{
            webElement = new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(driver -> driver.findElement(By.id(prop.getProperty("id.banner.cookie"))));
            webElement = driver.findElement(By.id(prop.getProperty("id.banner.cookie")));
            if(webElement.isDisplayed()) {
                driver.findElement(By.id(prop.getProperty("id.btn.accept.cookie"))).click();
                System.out.println("Banner Trovato e Chiuso");
            }
        }catch (NoSuchElementException e) {
            System.out.println("Banner non trovato");
        }
    }

    public void closeBannerFluent(Properties prop) {
        try{
            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(5))
                    .pollingEvery(Duration.ofSeconds(1))
                    .ignoring(NoSuchElementException.class);

            if(wait.until(driver -> driver.findElement(By.id("id.banner.cookie")).isDisplayed())) {
                driver.findElement(By.id(prop.getProperty("id.btn.accept.cookie"))).click();
                System.out.println("Banner Trovato e Chiuso");
            }
        }catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Banner non trovato");
        }
    }

    public List<WebElement> getMenuTabs(Properties prop, String propKey) {
        return driver.findElement(By.id(prop.getProperty(propKey))).findElements(By.tagName("a"));
    }
}
