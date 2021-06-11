package com.palmieri;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

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
        Utility.loadProp("log4j");
        BasicConfigurator.configure();
    }

    public static void startAppium(String platform, DesiredCapabilities desiredCapabilities) {
        try {
            if(platform.equals("ANDROID")) {
                androidDriver = new AndroidDriver<WebElement>(new URL(ANDROID_DRIVER_URL), desiredCapabilities);
            } else {
                iosDriver = new IOSDriver<WebElement>(new URL(IOS_DRIVER_URL), desiredCapabilities);
            }
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
}
