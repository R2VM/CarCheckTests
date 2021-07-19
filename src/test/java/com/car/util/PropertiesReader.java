package com.car.util;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    private static PropertiesReader instance = null;

    private Properties properties;

    public static PropertiesReader getPropertiesReader() {
        if (instance == null) {
            instance = new PropertiesReader();
            instance.loadData();
        }
        return instance;
    }

    private static void loadPropertyFile(Properties properties, String fileName) {
        try (InputStream inputStream = PropertiesReader.class.getClassLoader().getResourceAsStream(fileName)) {
            System.out.println("Loading properties from file:" + fileName);
            properties.load(inputStream);
        } catch (Exception exc) {
            throw new RuntimeException(String.format("Property file: %s could not be loaded", fileName));
        }
    }

    private void loadData() {
        properties = new Properties();
        String environment = System.getProperty("environment", "dev");
        String environmentFilePath = "environment/" + environment + "/application.properties";
        loadPropertyFile(properties, environmentFilePath);
    }

    public String getProperty(String propertyName) {
        String propertyValue = properties.getProperty(propertyName);
        if (propertyValue == null) {
            throw new RuntimeException(String.format("propertyValue is not found for propertyName: %s", propertyName));
        }
        return propertyValue;
    }

}
