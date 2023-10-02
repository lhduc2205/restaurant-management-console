package com.lhduc.common.filtered;

import com.lhduc.exception.ApplicationRuntimeException;
import com.lhduc.util.UserInputUtil;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code ConditionCreator} class is responsible for creating filter conditions
 * for objects that implement the {@link Filterable} interface.
 *
 * @param <T> The type of objects that can be filtered.
 */
public class ConditionCreator<T extends Filterable> {
    private final Class<T> clazz;
    private final T instance;
    private final FilterCondition conditions;

    public ConditionCreator(Class<T> clazz) {
        this.clazz = clazz;
        this.instance = createInstance();
        this.conditions = new FilterCondition();
    }

    private T createInstance() {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new ApplicationRuntimeException("Unable to create instance of object");
        }
    }

    /**
     * Creates filter conditions based on the fields defined by the {@link Filterable} object.
     * The user is prompted to enter filter values for each field.
     *
     * @return Filter conditions represented as a {@link FilterCondition} object.
     */
    public FilterCondition createConditions() {
        this.conditions.clear();

        String[] filteredFieldsName = instance.getFilterableFieldNames();

        for (int i = 0; i < filteredFieldsName.length; i++) {
            Field field = getFieldByName(filteredFieldsName[i]);
            field.setAccessible(true);
            System.out.println(i + 1 + ". " + field.getName());
        }

        collectFilterConditions(filteredFieldsName);

        return conditions;
    }

    private Field getFieldByName(String fieldName) {
        try {
            return instance.getClass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            throw new ApplicationRuntimeException("Field not found: " + fieldName);
        }
    }

    private void collectFilterConditions(String[] filteredFieldsName) {
        do {
            int fieldNameIndex = UserInputUtil.enterInteger("Choose field name", filteredFieldsName.length);
            fieldNameIndex--;

            String filteredValue = UserInputUtil.enterString("Enter value for " + filteredFieldsName[fieldNameIndex]);
            this.conditions.add(filteredFieldsName[fieldNameIndex], filteredValue);

        } while (askToAddMoreFilter());
    }

    private boolean askToAddMoreFilter() {
        return UserInputUtil.getUserChoiceForYesNoOption("Do you want to add more filter? (Y/N): ");
    }
}
