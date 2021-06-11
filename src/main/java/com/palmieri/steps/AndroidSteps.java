package com.palmieri.steps;

import com.palmieri.ManagementDriver;
import com.palmieri.Utility;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import java.time.Duration;
import java.util.Properties;

public class AndroidSteps {

    AndroidDriver<?> driver = ManagementDriver.getAndroidDriver();
    ExtentTest extentTest = null;

        public AndroidSteps() {
    }

        public void clickOnButtonByXpath(String xpath){
            try {
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                        .withTimeout(Duration.ofSeconds(9))
                        .pollingEvery(Duration.ofSeconds(3))
                        .ignoring(NoSuchElementException.class);


                WebElement webElement = wait.until(driver -> driver.findElement(By.xpath(xpath)));
                if (webElement.isDisplayed()) {
                    webElement.submit();
                }
            } catch (TimeoutException e) {
                System.out.println("Banner non trovato");
            }
        }

        public boolean login(Properties prop, ExtentTest extentTest) {
            try {
            driver.findElement(By.id(prop.getProperty("id.input.user"))).sendKeys("admin");
            extentTest.log(LogStatus.INFO, "Inserisco User", extentTest.addBase64ScreenShot(Utility.getBase64MobileScreenshot()));
            driver.findElement(By.id(prop.getProperty("id.input.pwd"))).sendKeys("admin");
            extentTest.log(LogStatus.INFO, "Inserisco PWD", extentTest.addBase64ScreenShot(Utility.getBase64MobileScreenshot()));
            driver.findElement(By.id(prop.getProperty("id.btn.login"))).click();
            extentTest.log(LogStatus.INFO, "Clicco Login", extentTest.addBase64ScreenShot(Utility.getBase64MobileScreenshot()));
            return true;
            }
            catch (Exception e) {
                extentTest.log(LogStatus.ERROR, "Clicco Login", extentTest.addBase64ScreenShot(Utility.getBase64MobileScreenshot()));
            }
            return false;
        }
}



