package com.palmieri;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
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

    public static boolean Screenshot(WebDriver driver, String testName) {
        String fileName = testName +  new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
        try {
            File scrFile  = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(SCREENSHOT_PATH + fileName + EXT_PNG));
            return true;
        }
        catch (IOException e) {
            System.out.println(e.getMessage() + e.getCause());
        }
        return false;
    }

    public static boolean Screenshot(String testName) {
        String fileName = testName + "bytes" + new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
        try {
            byte[] imgBytes  = ((TakesScreenshot) ManagementDriver.getChromeDriver()).getScreenshotAs(OutputType.BYTES);
            Files.write(Paths.get(SCREENSHOT_PATH + fileName + EXT_PNG),imgBytes);
            return true;

        }
        catch (IOException e) {
            System.err.println(e.getMessage() + e.getCause());
            return false;

        }
    }
    public static String getBase64Screenshot() {
        try {
            SimpleDateFormat oSDF = new SimpleDateFormat("yyyyMMddHHmmss");
            String sDate = oSDF.format(new Date());
            String encodedBase64 = null;
            FileInputStream fileInputStream = null;
            String destination = "";
            File source = null;

            source = ((TakesScreenshot) ManagementDriver.getChromeDriver()).getScreenshotAs(OutputType.FILE);
            destination = SCREENSHOT_PATH  +  File.separator + File.separator + sDate + EXT_PNG;

            File finalDestination = new File(destination);
            FileUtils.copyFile(Objects.requireNonNull(source), finalDestination);

            try {
                fileInputStream = new FileInputStream(finalDestination);
                byte[] bytes = new byte[(int) finalDestination.length()];
                int fileSize = fileInputStream.read(bytes);
                encodedBase64 = new String(Base64.encodeBase64(bytes));

            } catch (FileNotFoundException ex) {
                Assert.fail("Errore: "+ ex.getMessage());
            } finally {
                if(fileInputStream != null) fileInputStream.close();
            }
            return "data:image/png;base64," + encodedBase64;
        } catch (Exception ex) {
            Assert.fail("Errore: "+ ex.getMessage());
        }
        return null;
    }
    public static String getBase64MobileScreenshot() {
        try {
            SimpleDateFormat oSDF = new SimpleDateFormat("yyyyMMddHHmmss");
            String sDate = oSDF.format(new Date());
            String encodedBase64 = null;
            FileInputStream fileInputStream = null;
            String destination = "";
            File source = null;

            source = ((TakesScreenshot) ManagementDriver.getAndroidDriver()).getScreenshotAs(OutputType.FILE);
            destination = SCREENSHOT_PATH  +  File.separator + File.separator + sDate + EXT_PNG;

            File finalDestination = new File(destination);
            FileUtils.copyFile(Objects.requireNonNull(source), finalDestination);

            try {
                fileInputStream = new FileInputStream(finalDestination);
                byte[] bytes = new byte[(int) finalDestination.length()];
                int fileSize = fileInputStream.read(bytes);
                encodedBase64 = new String(Base64.encodeBase64(bytes));

            } catch (FileNotFoundException ex) {
                Assert.fail("Errore: "+ ex.getMessage());
            } finally {
                if(fileInputStream != null) fileInputStream.close();
            }
            return "data:image/png;base64," + encodedBase64;
        } catch (Exception ex) {
            Assert.fail("Errore: "+ ex.getMessage());
        }
        return null;
    }


    public static HashMap<String, Object> createMap(Object o, Object o2) {
        HashMap<String, Object> coordinate = new HashMap<>();
        coordinate.put("latitude", o);
        coordinate.put("longitude",o2);
        coordinate.put("accuracy", 1);
        return coordinate;
    }
}
