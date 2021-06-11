package com.palmieri.steps;

import com.palmieri.ManagementDriver;
import com.palmieri.Utility;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class IlMeteoSteps {
    private WebElement webElement;
    private WebDriver driver = ManagementDriver.getChromeDriver();

    public void search(Properties prop, String q) {
        webElement = driver.findElement(By.id(prop.getProperty("id.input.search")));
        webElement.clear();
        webElement.sendKeys(q);
        driver.findElement(By.id(prop.getProperty("id.btn.search"))).click();
    }

    public void closeBanner(Properties prop) {
        try{
            webElement = new WebDriverWait(driver, (long) 5)
                    .until(driver -> driver.findElement(By.id(prop.getProperty("id.banner.cookie"))));
            webElement = driver.findElement(By.id(prop.getProperty("id.banner.cookie")));
            if(webElement.isDisplayed()) {
                driver.findElement(By.id(prop.getProperty("id.btn.accept.cookie"))).click();
                System.out.println("Banner Trovato e Chiuso");
            }
        }catch (NoSuchElementException e) {
            System.out.println("Banner non trovato");
            Utility.Screenshot("closeBannerException");
        }
    }


    public List<WebElement> getMenuTabs(Properties prop, String propKey) {
        return driver.findElement(By.id(prop.getProperty(propKey))).findElements(By.tagName("a"));
    }
}
