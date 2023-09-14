package com.lhduc.configs;

import com.lhduc.RestaurantManagementApplication;
import com.lhduc.common.constants.FolderConstant;
import com.lhduc.exceptions.ApplicationRuntimeException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ApplicationConfig {
    private static final Map<String, String> properties = new HashMap<>();

    public static void readProperties() {
        try {
            InputStream inputStream = getInputStreamToReadFileInResource();

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
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            throw new ApplicationRuntimeException("Can not read Config file");
        }

    }

    private static InputStream getInputStreamToReadFileInResource() throws FileNotFoundException {
        if (isRunningFromJAR()) {
            return ApplicationConfig.class.getClassLoader().getResourceAsStream("properties.txt");
        }

        return new FileInputStream(FolderConstant.RESOURCES_PATH + "/properties.txt");
    }

    public static boolean isRunningFromJAR() {
        return RestaurantManagementApplication.class.getResource("RestaurantManagementApplication.class").toString().startsWith("jar:");
    }

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


//    public static Map<String, String> getProperties() {
//        return properties;
//    }

    public static String getDatabaseType() {
        return properties.get("database");
    }
}
