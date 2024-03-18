package com.progressoft.interns.advanced.utility;


import com.progressoft.interns.advanced.exception.PropertiesException;


import java.io.FileReader;
import java.io.IOException;


import java.util.Properties;

public class PropertiesUtilityImpl implements PropertiesUtility {

    private final Properties properties;

    public PropertiesUtilityImpl() {
        this.properties = new Properties();
    }

    @Override
    public void loadProperties(String filePath) {
        if (filePath == null) {
            throw new PropertiesException("File Path Cannot be null");
        }

        try (FileReader reader = new FileReader(filePath)) {
            properties.load(reader);
        } catch (IOException e) {
            throw new PropertiesException("Properties file not found: " + filePath);
        }
    }

    @Override
    public String getPropertyValue(String propertyName) {
        String value = properties.getProperty(propertyName);

        if (value == null) {
            throw new PropertiesException("Property with key: (" + propertyName + ") is not found");
        }
        return value;
    }

    @Override
    public Object getClassFromProperty(String propertyName) {
        String className = getPropertyValue(propertyName);
        try {
            Class<?> parserClass = Class.forName(className);
            return parserClass.getDeclaredConstructor().newInstance();

        } catch (Exception e) {
            throw new PropertiesException("Property with key: (" + propertyName + ") is not found");
        }
    }
}
