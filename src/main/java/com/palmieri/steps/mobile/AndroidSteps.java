package com.palmieri.steps.mobile;

import com.palmieri.ManagementDriver;
import com.palmieri.toolbox.StringStylist;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;

import java.util.List;
import java.util.Properties;

public class AndroidSteps {

    AndroidDriver<?> driver = ManagementDriver.getAndroidDriver();
    private String stepName = "";

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public void clickOnButton(String key,String value){
        String[] selectorSp = key.split("\\.");
        char type;
        if(selectorSp[0].charAt(0) != 'c') type = selectorSp[0].charAt(0);
        else if(selectorSp[0].charAt(1) == 'l') type = selectorSp[0].charAt(0);
        else type = 's';
        setStepName("Click on " + selectorSp[1] + " " + selectorSp[2]);
        ManagementDriver.waitUntilDisplayed(type,value).click();
        }

    public void insertUserName(Properties prop,String user) {
        setStepName(StringStylist.polishMethodName(new AndroidSteps(){}.getClass().getEnclosingMethod().getName()));
        ManagementDriver.waitUntilDisplayed('i',prop.getProperty("id.input.user")).sendKeys(user);
        }

    public void insertPassword(Properties prop,String pwd) {
        setStepName(StringStylist.polishMethodName(new AndroidSteps(){}.getClass().getEnclosingMethod().getName()));
        ManagementDriver.waitUntilDisplayed('i',prop.getProperty("id.input.pwd")).sendKeys(pwd);
    }

    public void insertName(Properties prop,String name) {
        setStepName(StringStylist.polishMethodName(new AndroidSteps(){}.getClass().getEnclosingMethod().getName()));
        ManagementDriver.waitUntilDisplayed('i',prop.getProperty("id.input.add")).sendKeys(name);

    }

    public void reset(Properties prop) {
        setStepName(StringStylist.polishMethodName(new AndroidSteps(){}.getClass().getEnclosingMethod().getName()));
        clickOnButton("id.btn.reset",prop.getProperty("id.btn.reset"));
    }

    public List<WebElement> getContattiList(Properties prop) {
        List<WebElement> list = ManagementDriver.waitUntilListDisplayed('c',prop.getProperty("class.list.user"));
            return list.subList(2,list.size());
    }
}



