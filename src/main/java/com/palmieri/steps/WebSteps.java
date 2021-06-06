package com.palmieri.steps;

import com.palmieri.ManagementDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebSteps {
    ChromeDriver driver = ManagementDriver.getDriver();

    public void clickOnButtonByXpath(String xpath){
        Wait <WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);


        WebElement webElement = wait.until(driver -> driver.findElement(By.xpath(xpath)));
        if(webElement.isDisplayed()) {
            webElement.click();
        }
    }
}
