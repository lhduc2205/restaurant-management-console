package com.lhduc.common.pattern.servicelocator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServiceLocator {
    private static final Cache cache = new Cache();

    private ServiceLocator() {}

    public static <T> T getService(Class<T> serviceClass) {
        T service = cache.getService(serviceClass);

        if (service != null) {
            return service;
        }


        InitialContext context = new InitialContext();
        service = context.lookup(serviceClass);

        cache.addService(service);
        return service;
    }
}
