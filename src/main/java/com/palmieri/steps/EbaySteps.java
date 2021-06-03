package com.palmieri.steps;

import com.palmieri.ManagementDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.function.Function;

import static java.util.concurrent.TimeUnit.SECONDS;

public class EbaySteps {


    private WebElement webElement;
    private WebDriver driver = ManagementDriver.getDriver();

    public void search(Properties prop,String q) {
        webElement = driver.findElement(By.name(prop.getProperty("name.input.search")));
        webElement.clear();
        webElement.sendKeys(q);
        driver.findElement(By.id(prop.getProperty("id.btn.search"))).click();
    }
    public void closeBanner(Properties prop) {
        try{
            webElement = new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(driver -> driver.findElement(By.id(prop.getProperty("id.banner.gdp"))));
            if(webElement.isDisplayed()) {
                driver.findElement(By.id(prop.getProperty("id.btn.gdp.accept"))).click();
                System.out.println("Banner Trovato e Chiuso");
            }
        }catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Banner non trovato");
        }
    }

    public void closeBannerFluent(Properties prop) {
        try{
            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(5))
                    .pollingEvery(Duration.ofSeconds(1))
                    .ignoring(NoSuchElementException.class);

            if(wait.until(driver -> driver.findElement(By.id("id.banner.gdp")).isDisplayed())) {
                driver.findElement(By.id(prop.getProperty("id.btn.gdp.accept"))).click();
                System.out.println("Banner Trovato e Chiuso");
            }
        }catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Banner non trovato");
        }
    }
    public List<WebElement> getTabs(Properties prop) {
        return driver.findElement(By.id(prop.getProperty("id.select.category"))).findElements(By.tagName("option"));
    }

    public WebElement   selectCategory(Properties prop, String c) {
        driver.findElement(By.id(prop.getProperty("id.select.category"))).click();
        for(WebElement element : getTabs(prop)) {
            if(element.getText().toLowerCase().contains(c)){
                element.click();
                return element;

            }
        }
        return null;
    }
}
