package com.palmieri;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.palmieri.GlobalParameters.EXT_PROP;
import static com.palmieri.GlobalParameters.PROPERTIES_PATH;

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

}
