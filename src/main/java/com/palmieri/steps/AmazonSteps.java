package com.palmieri.steps;

import com.palmieri.ManagementDriver;
import com.palmieri.toolbox.Screen;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;

public class AmazonSteps {
    ChromeDriver driver = ManagementDriver.getChromeDriver();
    WebElement webElement = null;


    public WebElement clickOnButtonByXpath(String xpath){
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofSeconds(9))
                    .pollingEvery(Duration.ofSeconds(3))
                    .ignoring(NoSuchElementException.class);

            WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
            webElement.click();
            return webElement;

        } catch (TimeoutException e) {
            Screen.Screenshot("exception_clickOnButtonByXpath");
            System.out.println("Pulsante non trovato");
        }
        return null;
    }

    public void search(Properties prop, String q) {
            webElement = new WebDriverWait(driver, (long) 5)
                    .until(driver -> driver.findElement(By.xpath(prop.getProperty("xpath.input.search"))));
            if(webElement.isDisplayed()) {
                webElement.clear();
                webElement.sendKeys(q);
                webElement.sendKeys(Keys.ENTER);
            }
    }
    public void selectProduct(Properties prop, int n) {
        driver.findElement(By.className("s-matching-dir")).findElement(By.className("sg-col-inner")).findElements(By.className("sg-col-inner")).get(n).click();
    }
    public void selectBrand(Properties prop) {
        WebElement brand = driver.findElement(By.id("p_89-title"));
        driver.findElement(By.id(prop.getProperty("id.brand.apple"))).findElement(By.tagName("i")).click();
    }

    public void orderBy(Properties prop) {
        WebElement sortBy = driver.findElement(By.className(prop.getProperty("class.name.order")));
        sortBy.click();
        driver.findElement(By.id(prop.getProperty("id.order.latest"))).click();
    }


    public WebElement clickOnButtonById(String id){
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofSeconds(9))
                    .pollingEvery(Duration.ofSeconds(3))
                    .ignoring(NoSuchElementException.class);


            WebElement webElement = wait.until(driver -> driver.findElement(By.id(id)));
            if (webElement.isDisplayed()) {
                webElement.click();
                return webElement;
            }
        } catch (TimeoutException e) {
            Screen.Screenshot("clickOnButtonByClassNameException");
        }
        return null;
    }
}
