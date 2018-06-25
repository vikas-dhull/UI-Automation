package com.healthkart.hkAutomation.property;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyHelper {

    public static String readProperty;

    public static String readProperty(String key){
    Properties prop = new Properties();
    InputStream input = null;
    try {

        input = new FileInputStream(System.getProperty("user.dir")+ "/propertyhelper.properties");
        prop.load(input);

        return prop.getProperty(key);

    } catch (IOException ex) {
        ex.printStackTrace();
    } finally {
        if (input != null) {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    return null;
}

}
