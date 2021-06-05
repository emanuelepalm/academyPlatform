package com.palmieri;

import jdk.nashorn.internal.runtime.regexp.joni.constants.internal.OPCode;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.palmieri.GlobalParameters.*;

public class Utility {

    public static Properties loadProp(String fileName) {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(PROPERTIES_PATH + File.separator + fileName + EXT_PROP));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    public static void Screenshot(WebDriver driver, String testName) {
        String fileName = testName +  new SimpleDateFormat("yyyyMMddHHmm'.txt'").format(new Date());

        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(SCREENSHOT_PATH + fileName + EXT_PNG));
        }
        catch (IOException e) {
            System.out.println(e.getMessage() + e.getCause());
        }
    }
}
