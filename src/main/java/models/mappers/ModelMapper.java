package models.mappers;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ModelMapper {

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> this.map(element, targetClass))
                .collect(Collectors.toList());
    }

    public <D> D map(Object source, Class<D> destinationType) {
        List<Field> sourceFields = Arrays.stream(source.getClass().getDeclaredFields()).toList();
        Field[] destinationFields = destinationType.getDeclaredFields();

        Map<String, Field> sourceFieldMap = new HashMap<>();

        sourceFields.forEach(field -> {
            field.setAccessible(true);
            sourceFieldMap.put(field.getName(), field);
        });


        sourceFields.forEach(field -> field.setAccessible(true));

        try {
            Class<D> destinationClass = (Class<D>) Class.forName(destinationType.getName());
            D destinationInstance = destinationClass.getConstructor().newInstance();


            for (Field destinationField : destinationFields) {
                String destinationFieldName = destinationField.getName();

                if (sourceFieldMap.get(destinationFieldName) == null) {
                    break;
                }

                destinationField.setAccessible(true);
                destinationField.set(destinationInstance, sourceFieldMap.get(destinationFieldName).get(source));
            }

            return destinationInstance;
        } catch (Exception e) {
            e.fillInStackTrace();
            return null;
        }
    }
}
