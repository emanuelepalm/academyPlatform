package com.palmieri.toolbox;

import com.palmieri.interfaces.ISteps;
import com.palmieri.steps.mobile.AndroidSteps;
import com.relevantcodes.extentreports.ExtentTest;

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

    public void cleanClick(String key) {
        steps.clickOnButton(key,prop.getProperty(key));
    }


}
