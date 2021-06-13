package com.palmieri.template;

import com.palmieri.ManagementDriver;
import io.appium.java_client.android.AndroidDriver;

public class TemplateMobileStep {

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
}
