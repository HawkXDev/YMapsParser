package com.sokol.util;

import com.sokol.LaunchChrome;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public final class PropertiesUtil {
    private PropertiesUtil() {
    }

    public static String getProperty(String key) {
        Properties properties = new Properties();

        try (InputStream input = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("prop.properties")) {
            properties.load(input);
            return properties.getProperty("url");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
