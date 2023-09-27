package com.lhduc.common.pattern.servicelocator;

import java.util.ArrayList;
import java.util.List;

class Cache {
    private static final List<Object> services = new ArrayList<>();

    public Object getService(String serviceName) {
        for (Object object : services) {
            if (object.getClass().getCanonicalName().equals(serviceName)) {
                return object;
            }
        }

        return null;
    }

    public void addService(Object service) {
        services.add(service);
    }
}
