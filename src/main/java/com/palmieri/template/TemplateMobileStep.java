package com.palmieri.template;

import com.palmieri.ManagementDriver;
import com.palmieri.toolbox.StringStylist;
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
        setStepName(StringStylist.ByAndStep(key));
        ManagementDriver.waitUntilDisplayed(StringStylist.getBy(),value).click();
    }
}
