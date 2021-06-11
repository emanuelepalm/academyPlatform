package com.palmieri.steps;

import com.palmieri.ManagementDriver;
import com.palmieri.Utility;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Properties;

public class GoogleSteps {
    ChromeDriver driver = ManagementDriver.getChromeDriver();
    WebElement webElement = null;


    public void clickOnButtonByXpath(String xpath){
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofSeconds(9))
                    .pollingEvery(Duration.ofSeconds(3))
                    .ignoring(NoSuchElementException.class);

            WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
                webElement.click();

        } catch (TimeoutException e) {
            Utility.Screenshot("exception_clickOnButtonByXpath");
            System.out.println("Pulsante non trovato");
        }
    }


    public void search(Properties prop, String q) {
        try{
            webElement = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(driver -> driver.findElement(By.xpath(prop.getProperty("xpath.input.search"))));
            if(webElement.isDisplayed()) {
                webElement.clear();
                webElement.sendKeys(q);
                webElement.sendKeys(Keys.ENTER);
            }
        }catch (NoSuchElementException | TimeoutException e) {
            Utility.Screenshot("searchException");
            System.out.println(e.getCause() + " " + e.getMessage());
        }

    }
}
