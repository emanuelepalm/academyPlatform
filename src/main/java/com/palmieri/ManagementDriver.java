package com.palmieri;

import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.palmieri.GlobalParameters.CHROME_DRIVER_PATH_WIN;

public class ManagementDriver {

    private static ChromeDriver driver;
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

    public static ChromeDriver getDriver() {
        return driver;
    }

    public static void stopDriver() {
        driver.quit();
    }
}
