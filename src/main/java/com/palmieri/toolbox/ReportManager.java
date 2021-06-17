package com.palmieri.toolbox;

import com.palmieri.interfaces.ISteps;
import com.palmieri.steps.mobile.AndroidSteps;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.util.Properties;

public class ReportManager {
    private ExtentTest extentTest;
    private Properties prop;
    private ISteps steps;

    public ReportManager(ExtentTest extentTest, Properties prop, ISteps steps) {
        this.extentTest = extentTest;
        this.prop = prop;
        this.steps = steps;
    }

    public ExtentTest getExtentTest() {
        return extentTest;
    }

    public void setExtentTest(ExtentTest extentTest) {
        this.extentTest = extentTest;
    }

    public Properties getProp() {
        return prop;
    }

    public void setProp(Properties prop) {
        this.prop = prop;
    }

    public boolean cleanClick(String key) {
        try
        {
            steps.setStepName("Click on " + key);
            steps.clickOnButton(key,prop.getProperty(key));
            extentTest.log(LogStatus.INFO, "Click on: " + key,"");
            return true;
        }
        catch (Exception e)
        {
            extentTest.log(LogStatus.ERROR, "Click on: " + key + "Failed\nErrore: " +  e.getMessage(), extentTest.addBase64ScreenShot(Screen.getBase64MobileScreenshot()));
        }
        return false;
    }
}
