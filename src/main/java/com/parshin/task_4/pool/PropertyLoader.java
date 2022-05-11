package com.parshin.task_4.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Properties;
import java.util.TimeZone;

public class PropertyLoader {
    static final Logger logger = LogManager.getLogger();
    private static final String FILE_NAME = "sql_properties/sql_config.properties";
    private static final Properties properties = new Properties();

    private static PropertyLoader instance = new PropertyLoader();

    private PropertyLoader() {
    }

    public static PropertyLoader getInstance() {
        return instance;
    }

    public Properties loadProperties() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(FILE_NAME);
        if (resource == null) {
            logger.log(Level.ERROR, "file - {} - not found!", FILE_NAME);
            throw new RuntimeException("file not found! :" + FILE_NAME);
        }

        try (FileInputStream fileInputStream = new FileInputStream(resource.getFile())) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            logger.log(Level.ERROR, "ConnectionFactory loadPropertyFromFile file - {} - can't be read", FILE_NAME);
            throw new RuntimeException("SQL config property file can't be read", e);
        }

        if (Boolean.parseBoolean(properties.getProperty("set_serverTimezone_localTimezone", "true"))) {
            Calendar now = Calendar.getInstance();
            TimeZone timeZone = now.getTimeZone();
            String timeZoneName = timeZone.getID();
            properties.setProperty("serverTimezone", timeZoneName);
        }
        return properties;
    }
}
