package com.chenway.common.utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

    public static Properties prop;

    static {
        try {
            InputStream in = ClassLoader.getSystemResourceAsStream("conf.properties");
            prop = new Properties();
            prop.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProp(String key) {
        return prop.getProperty(key);
    }

    public static String getString(String key) {
        return prop.getProperty(key);
    }

    public static int getInt(String key) {
        return Integer.parseInt(prop.getProperty(key));
    }

}
