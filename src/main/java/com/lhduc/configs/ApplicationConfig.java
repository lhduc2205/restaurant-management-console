package com.lhduc.configs;

import com.lhduc.common.constants.AppPath;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ApplicationConfig {
    private static final Map<String, String> properties = new HashMap<>();

    public static void readProperties() {
        try {
            Scanner scanner = new Scanner(new File(AppPath.RESOURCES + "/properties.txt"));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                splitStringToKeyValue(line);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void splitStringToKeyValue(String input) {
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


    public static Map<String, String> getProperties() {
        return properties;
    }
}
