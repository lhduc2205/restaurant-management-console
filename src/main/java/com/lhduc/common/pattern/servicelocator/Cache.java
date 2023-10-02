package com.lhduc.common.pattern.servicelocator;

import java.util.ArrayList;
import java.util.List;

class Cache {
    private static final List<Object> services = new ArrayList<>();

    public <T> T getService(Class<T> serviceClass) {
        for (Object object : services) {
            if (object.getClass().isAssignableFrom(serviceClass)) {
                return (T) object;
            }
        }

        return null;
    }

    public void addService(Object service) {
        services.add(service);
    }
}
