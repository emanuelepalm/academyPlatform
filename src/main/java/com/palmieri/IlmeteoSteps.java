package com.palmieri;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class IlmeteoSteps {
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
            Thread.sleep(4000);
            webElement = driver.findElement(By.id(prop.getProperty("id.banner.cookie")));
            if(webElement.isDisplayed()) {
                driver.findElement(By.id(prop.getProperty("id.btn.accept.cookie"))).click();
                System.out.println("Banner Trovato e Chiuso");
            }
        }catch (NoSuchElementException | InterruptedException e) {
            System.out.println("Banner non trovato");
        }
    }

    public List<WebElement> getMenuTabs(Properties prop) {
        return driver.findElement(By.id(prop.getProperty("id.page.menu"))).findElements(By.tagName("a"));
    }
}
