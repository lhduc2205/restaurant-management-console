package com.lhduc.common.filtered;

import com.lhduc.exception.ApplicationRuntimeException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PropertyFilter {
    public <T> List<T> filter(List<T> objects, FilterCondition conditions) {
        List<T> filteredObject = new ArrayList<>();
        for (T object : objects) {
            if (objectMatchesConditions(object, conditions.getData())) {
                filteredObject.add(object);
            }
        }

        return new ArrayList<>(filteredObject);
    }

    private <T> boolean objectMatchesConditions(T object, Map<String, Object> conditions) {
        try {
            for (String propertyName : conditions.keySet()) {
                Field field = object.getClass().getDeclaredField(propertyName);
                field.setAccessible(true);

                Object conditionValue = conditions.get(propertyName);
                Object propertyValue = field.get(object);

                if (!containIgnoreCase(propertyValue, conditionValue)) {
                    return false;
                }
            }

            return true;
        } catch (NoSuchFieldException e) {
            return false;
        } catch (Exception e) {
            throw new ApplicationRuntimeException("Unable to access object values");
        }
    }

    private boolean containIgnoreCase(Object source, Object input) {
        String sourceString = source.toString().toLowerCase();
        String inputString = input.toString().toLowerCase();
        return sourceString.contains(inputString);
    }
}
