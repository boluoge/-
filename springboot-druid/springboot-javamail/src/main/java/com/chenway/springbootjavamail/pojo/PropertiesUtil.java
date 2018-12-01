package com.chenway.springbootjavamail.pojo;

import java.util.Locale;
import java.util.ResourceBundle;

public class PropertiesUtil {
    private final ResourceBundle resourceBundle;
    private final String fileName;

    public PropertiesUtil (String fileName){
        this.fileName=fileName;
        Locale locale = new Locale("zh","CN");
        this.resourceBundle=ResourceBundle.getBundle(this.fileName, locale);
    }

    public String getValue(String key){
        return this.resourceBundle.getString(key);
    }
}
