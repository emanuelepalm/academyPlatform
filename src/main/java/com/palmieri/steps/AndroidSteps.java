package com.palmieri.steps;

import com.palmieri.ManagementDriver;
import com.palmieri.toolbox.Screen;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class AndroidSteps {

    AndroidDriver<?> driver = ManagementDriver.getAndroidDriver();

        public AndroidSteps() {
    }

        public boolean clickOnButtonByXpath(String xpath){
            try {
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                        .withTimeout(Duration.ofSeconds(9))
                        .pollingEvery(Duration.ofSeconds(3))
                        .ignoring(NoSuchElementException.class);


                WebElement webElement = wait.until(driver -> driver.findElement(By.xpath(xpath)));
                if (webElement.isDisplayed()) {
                     webElement.click();
                     return true;
                }
            } catch (TimeoutException e) {
                System.out.println("Pulsante non trovato xpath: " + xpath);

            } return false;
        }

    public boolean clickOnButtonById(String id){
        try {
            Wait<AndroidDriver<?>> wait = new FluentWait<AndroidDriver<?>>(driver)
                    .withTimeout(Duration.ofSeconds(9))
                    .pollingEvery(Duration.ofSeconds(3))
                    .ignoring(NoSuchElementException.class);


            WebElement webElement = wait.until(driver -> driver.findElement(By.id(id)));
            if (webElement.isDisplayed()) {
                webElement.click();
                return true;
            }
        } catch (TimeoutException e) {
            System.out.println("Pulsante non trovato. Id: " + id);

        } return false;
    }

        public boolean insertUserName(Properties prop,String user) {
                ManagementDriver.waitUntilDisplayed('i',prop.getProperty("id.input.user")).sendKeys(user);
                return true;
        }

    public boolean insertPassword(Properties prop,String pwd) {
            ManagementDriver.waitUntilDisplayed('i',prop.getProperty("id.input.pwd")).sendKeys(pwd);
            return true;
    }
    public boolean insertName(Properties prop,String name) {
        ManagementDriver.waitUntilDisplayed('i',prop.getProperty("id.input.add")).sendKeys(name);
        return true;
    }

    public boolean reset(Properties prop) {
        if (clickOnButtonById(prop.getProperty("id.btn.reset"))) return true;
        return false;
    }

    public List<WebElement> getContattiList(Properties prop) {
        List<WebElement> list = ManagementDriver.waitUntilListDisplayed('c',prop.getProperty("class.list.user"));
            return list.subList(2,list.size());
    }
}



