package com.lhduc.config;

import com.lhduc.RestaurantManagementApplication;
import com.lhduc.common.constant.AppConstant;
import com.lhduc.common.constant.FolderConstant;
import com.lhduc.exception.ApplicationRuntimeException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 * The ApplicationConfig is responsible for reading configuration properties
 * from a resources/properties.txt file and providing access to these properties.
 */
public class ApplicationConfig {
    private static final Map<String, String> properties = new HashMap<>();

    /**
     * Reads configuration from file and store to map.
     *
     * @throws ApplicationRuntimeException If the configuration file doesn't exist.
     */
    public static void readProperties() {
        try {
            InputStream inputStream = getResourceFileInputStream(AppConstant.PROPERTIES_TXT_FILE);

            if (inputStream != null) {
                try (Scanner scanner = new Scanner(inputStream)) {
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();

                        if (line == null) {
                            break;
                        }

                        splitStringToKeyValue(line);
                    }
                } catch (Exception e) {
                    throw new ApplicationRuntimeException(e.getMessage(), e.getCause());
                }
            }
        } catch (IOException e) {
            throw new ApplicationRuntimeException("Can not read properties.txt file");
        }

    }

    /**
     * Retrieves an InputStream to read configuration file either from classpath
     * or from a file path depending on whether the application is running from a JAR or not.
     *
     * @return An InputStream for reading the configuration file.
     * @throws FileNotFoundException If the file is not found.
     */
    public static InputStream getResourceFileInputStream(String fileName) throws FileNotFoundException {
        if (isRunningFromJAR()) {
            return ApplicationConfig.class.getClassLoader().getResourceAsStream(fileName);
        }

        return new FileInputStream(FolderConstant.RESOURCES_FOLDER_PATH + fileName);
    }

    /**
     * Check if the application is running from JAR file.
     *
     * @return true if the application is running from JAR file; false otherwise.
     */
    public static boolean isRunningFromJAR() {
        return Objects.requireNonNull(RestaurantManagementApplication.class.getResource("RestaurantManagementApplication.class")).toString().startsWith("jar:");
    }

    /**
     * Splits the String into key-value pairs and add them to the properties map.
     *
     * @param input The input string to split
     */
    private static void splitStringToKeyValue(String input) {
        if (input == null) {
            return;
        }

        String[] property = input.split("=");

        if (property.length == 0) {
            return;
        }

        String key = property[0].trim();

        if (property.length == 1) {
            properties.put(key, "");
            return;
        }

        String value = property[1].trim();

        properties.put(key, value);
    }

    /**
     * Retrieves the map of configuration properties.
     *
     * @return A map containing the configuration properties.
     */
    public static Map<String, String> getProperties() {
        return properties;
    }


    /**
     * Retrieves the value associated with a specific configuration property.
     *
     * @param key The key of the configuration property.
     * @return The value of the configuration property, or null if the key is not found.
     */
    public static String getProperty(String key) {
        return properties.get(key);
    }
}
