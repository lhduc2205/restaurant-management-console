package common.patterns.servicelocator;

import configs.ApplicationConfig;
import databases.Database;

import java.util.Map;

public class ServiceLocator {
    private static Cache cache = new Cache();

    private ServiceLocator() {}

    public static <T> T getService(String serviceName) {
        Object service = cache.getService(serviceName);

        if (service != null) {
            return (T) service;
        }


        InitialContext context = new InitialContext();
        service = context.lookup(serviceName);

        cache.addService(service);
        return (T) service;
    }
}
