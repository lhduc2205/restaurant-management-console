package common.patterns.servicelocator;

import configs.ApplicationConfig;
import databases.CsvDatabase;
import databases.Database;
import databases.JsonDatabase;
import exceptions.ApplicationRuntimeException;

import java.util.Map;

class InitialContext {
    public <T> T lookup(String objectName) {
        try {
            Class<T> clazz = (Class<T>) Class.forName(objectName);

            if (isDatabase(clazz)) {
                return (T) getDatabaseInstance(clazz);
            }

            T newInstance = clazz.getConstructor().newInstance();
            return cast(newInstance, clazz);
        } catch (Exception e) {
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

    private Database getDatabaseInstance(Class<?> clazz) {
        String databaseType = getDatabaseConfig();

        switch (databaseType) {
            case "csv": {
                return new CsvDatabase();
            }
            default: {
                return new JsonDatabase();
            }
        }
    }

    private String getDatabaseConfig() {
        return ApplicationConfig.getProperties().get("database");
    }
}
