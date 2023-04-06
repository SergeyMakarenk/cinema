package com.makarenko.main.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.makarenko.main.util.Constants.PROPERTIES_APP;

public class PropertiesUtil {

    public static final Properties PROPERTIES = new Properties();

    static{
        loadProperties();
    }

    public static String get(String key){
        return PROPERTIES.getProperty(key);
    }

    private static void loadProperties(){
        try (InputStream stream = PropertiesUtil.class.getClassLoader().getResourceAsStream(PROPERTIES_APP)) {
            PROPERTIES.load(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
