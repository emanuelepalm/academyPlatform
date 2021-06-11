package com.palmieri;

import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DefaultMobileCapabilities extends DesiredCapabilities {

    public DefaultMobileCapabilities() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, " emulator-5554 ");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, "/path/to/my.apk");
        }
    }

