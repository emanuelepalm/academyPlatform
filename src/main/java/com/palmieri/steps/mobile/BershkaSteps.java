package com.palmieri.steps.mobile;

import com.palmieri.ManagementDriver;
import com.palmieri.interfaces.ISteps;
import com.palmieri.toolbox.StringStylist;
import io.appium.java_client.android.AndroidDriver;

import java.util.Properties;

public class BershkaSteps implements ISteps {

    String stepName;


    @Override
    public String getStepName() {
        return this.stepName;
    }

    @Override
    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    @Override
    public void clickOnButton(String key, String value) {
        String[] selectorSp = key.split("\\.");
        char type;
        if(selectorSp[0].charAt(0) != 'c') type = selectorSp[0].charAt(0);
        else if(selectorSp[0].charAt(1) == 'l') type = selectorSp[0].charAt(0);
        else type = 's';
        ManagementDriver.waitUntilDisplayed(type,value).click();
    }

    public void getToHome(Properties prop) {
        setStepName(StringStylist.polishMethodName(new AndroidSteps(){}.getClass().getEnclosingMethod().getName()));
        clickOnButton("id.accept.cookies",prop.getProperty("id.accept.cookies"));
        clickOnButton("id.confirm.language",prop.getProperty("id.confirm.language"));
        clickOnButton("iid.confirm.location",prop.getProperty("id.confirm.location"));
        clickOnButton("id.accept.notification",prop.getProperty("id.accept.notification"));
        clickOnButton("id.continue.location",prop.getProperty("id.continue.location"));
        clickOnButton("id.accept.location",prop.getProperty("id.accept.location"));
    }
}
