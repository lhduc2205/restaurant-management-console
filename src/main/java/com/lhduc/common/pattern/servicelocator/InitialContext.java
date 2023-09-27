package com.lhduc.common.pattern.servicelocator;

import com.lhduc.config.ApplicationConfig;
import com.lhduc.datasource.CsvDatasource;
import com.lhduc.datasource.Datasource;
import com.lhduc.datasource.JsonDatasource;
import com.lhduc.exception.ApplicationRuntimeException;

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
        return clazz.isAssignableFrom(Datasource.class);
    }

    private Datasource getDatabaseInstance() {
        String databaseType = ApplicationConfig.getProperty("database");

        switch (databaseType) {
            case "csv": {
                return new CsvDatasource();
            }
            default: {
                return new JsonDatasource();
            }
        }
    }
}
