package com.lhduc.model.mapper;

import com.lhduc.exception.ApplicationRuntimeException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ModelMapper {
    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> this.map(element, targetClass))
                .collect(Collectors.toList());
    }

    public <D> D map(Object source, Class<D> destinationType) {
        if (source == null) {
            throw new IllegalArgumentException("Source must not be null");
        }

        if (source instanceof Optional<?>) {
            source = ((Optional<?>) source).orElseThrow();
        }

        Field[] sourceFields = source.getClass().getDeclaredFields();
        Field[] destinationFields = destinationType.getDeclaredFields();

        Map<String, Field> sourceFieldMap = new HashMap<>();

        for (Field sourceField : sourceFields) {
            sourceField.setAccessible(true);
            sourceFieldMap.put(sourceField.getName(), sourceField);
        }

        try {
            Class<D> destinationClass = (Class<D>) Class.forName(destinationType.getName());
            D destinationInstance = destinationClass.getConstructor().newInstance();

            for (Field destinationField : destinationFields) {
                String destinationFieldName = destinationField.getName();

                if (sourceFieldMap.get(destinationFieldName) == null) {
                    continue;
                }

                destinationField.setAccessible(true);
                destinationField.set(destinationInstance, sourceFieldMap.get(destinationFieldName).get(source));
            }

            return destinationInstance;
        } catch (Exception e) {
            throw new ApplicationRuntimeException(e.getMessage(), e.getCause());
        }
    }
}
