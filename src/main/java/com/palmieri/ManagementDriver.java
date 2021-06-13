package com.palmieri;

import com.palmieri.toolbox.Screen;
import com.palmieri.toolbox.Utils;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import static com.palmieri.GlobalParameters.*;

public class ManagementDriver {

    private static ChromeDriver driver;
    private static AndroidDriver<?> androidDriver;
    private static IOSDriver<?> iosDriver;
    private static boolean mobile = false;

    public static boolean isMobile() {
        return mobile;
    }

    public static void setMobile(boolean mobile) {
        ManagementDriver.mobile = mobile;
    }

    public static void startDriver(DefaultChromeOptions defaultChromeOptions){
        System.setProperty("webdriver.chrome.driver",CHROME_DRIVER_PATH_WIN);
        System.setProperty("org.freemarker.loggerLibrary","none");
        new ChromeOptions().setPageLoadStrategy(PageLoadStrategy.NORMAL);

        if(defaultChromeOptions == null) {
            defaultChromeOptions = new DefaultChromeOptions(new ChromeOptions());
        }
        driver = new ChromeDriver(defaultChromeOptions);

        System.err.close();
        System.setErr(System.out);
        Utils.loadProp("log4j");
        BasicConfigurator.configure();
    }

    public static void startAppium(DesiredCapabilities desiredCapabilities) {
        try {
            androidDriver = new AndroidDriver<>(new URL(ANDROID_DRIVER_URL), desiredCapabilities);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public static AndroidDriver getAndroidDriver() {
        return androidDriver;
    }

    public static ChromeDriver getChromeDriver() {
        return driver;
    }

    public static void stopDriver() {
        if(driver != null)driver.quit();
        if(androidDriver != null)androidDriver.quit();
    }

    public static WebElement waitUntilDisplayed(char type,String selector) {
        if (driver != null) {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofSeconds(9))
                    .pollingEvery(Duration.ofSeconds(3))
                    .ignoring(NoSuchElementException.class);
            switch (type) {
                case 'x':
                    return wait.until(driver -> driver.findElement(By.xpath(selector)));
                case 'i':
                    return wait.until(driver -> driver.findElement(By.id(selector)));
                case 't':
                    return wait.until(driver -> driver.findElement(By.tagName(selector)));
                case 'c':
                    return wait.until(driver -> driver.findElement(By.className(selector)));
                case 's':
                    return wait.until(driver -> driver.findElement(By.cssSelector(selector)));
                default:
                    return null;
            }
        }
        if (androidDriver != null) {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(androidDriver)
                    .withTimeout(Duration.ofSeconds(5))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(NoSuchElementException.class);
            switch (type) {
                case 'x':
                    return wait.until(driver -> androidDriver.findElement(By.xpath(selector)));
                case 'i':
                    return wait.until(driver -> androidDriver.findElement(By.id(selector)));
                case 't':
                    return wait.until(driver -> androidDriver.findElement(By.tagName(selector)));
                case 'c':
                    return wait.until(driver -> androidDriver.findElement(By.className(selector)));
                case 's':
                    return wait.until(driver -> androidDriver.findElement(By.cssSelector(selector)));
                default:
                    return null;
            }
        } return null;
    }
    public static List<WebElement> waitUntilListDisplayed(char type, String selector) {
        if (driver != null) {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofSeconds(9))
                    .pollingEvery(Duration.ofSeconds(3))
                    .ignoring(NoSuchElementException.class);
            switch (type) {
                case 'x':
                    return wait.until(driver -> driver.findElements(By.xpath(selector)));
                case 'i':
                    return wait.until(driver -> driver.findElements(By.id(selector)));
                case 't':
                    return wait.until(driver -> driver.findElements(By.tagName(selector)));
                case 'c':
                    return wait.until(driver -> driver.findElements(By.className(selector)));
                case 's':
                    return wait.until(driver -> driver.findElements(By.cssSelector(selector)));
                default:
                    return null;
            }
        }
        if (androidDriver != null) {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(androidDriver)
                    .withTimeout(Duration.ofSeconds(5))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(NoSuchElementException.class);
            switch (type) {
                case 'x':
                    return (List<WebElement>) wait.until(driver -> androidDriver.findElements(By.xpath(selector)));
                case 'i':
                    return (List<WebElement>) wait.until(driver -> androidDriver.findElements(By.id(selector)));
                case 't':
                    return (List<WebElement>) wait.until(driver -> androidDriver.findElements(By.tagName(selector)));
                case 'c':
                    return (List<WebElement>) wait.until(driver -> androidDriver.findElements(By.className(selector)));
                case 's':
                    return (List<WebElement>)  wait.until(driver -> androidDriver.findElements(By.cssSelector(selector)));
                default:
                    return null;
            }
        } return null;
    }

}
