package com.palmieri.interfaces;

import com.palmieri.ManagementDriver;
import io.appium.java_client.android.AndroidDriver;

public interface ISteps {

    AndroidDriver<?> driver = null;
    String stepName = null;

    String getStepName();
    void setStepName(String stepName);
    void clickOnButton(String key, String Value);
}
