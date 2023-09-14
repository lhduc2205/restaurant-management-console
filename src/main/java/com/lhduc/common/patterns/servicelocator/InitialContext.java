package com.lhduc.common.patterns.servicelocator;

import com.lhduc.configs.ApplicationConfig;
import com.lhduc.databases.CsvDatabase;
import com.lhduc.databases.Database;
import com.lhduc.databases.JsonDatabase;
import com.lhduc.exceptions.ApplicationRuntimeException;

import java.lang.reflect.InvocationTargetException;

class InitialContext {
    public <T> T lookup(String objectName) {
        try {
            Class<T> clazz = (Class<T>) Class.forName(objectName);

            if (isDatabase(clazz)) {
                return (T) getDatabaseInstance();
            }

            T newInstance = clazz.getConstructor().newInstance();
            return cast(newInstance, clazz);
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            System.out.println("Error when cast Object to " + objectName);
            throw new ApplicationRuntimeException(e.getMessage(), e.getCause());
        }
    }

    private static <T> T cast(Object object, Class<T> clazz) {
        if (clazz.isAssignableFrom(object.getClass())) {
            return clazz.cast(object);
        }
        throw new ClassCastException("Failed to cast instance.");
    }

    private static boolean isDatabase(Class<?> clazz) {
        return clazz.isAssignableFrom(Database.class);
    }

    private Database getDatabaseInstance() {
        String databaseType = ApplicationConfig.getDatabaseType();

        switch (databaseType) {
            case "csv": {
                return new CsvDatabase();
            }
            default: {
                return new JsonDatabase();
            }
        }
    }
}
