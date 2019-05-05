package by.itacademy.akulov.jdbc;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@UtilityClass
public class PropertiesManager {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream inputStream =
                     PropertiesManager.class.getClassLoader()
                             .getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
}
