package com.lhduc.common.filtered;

/**
 * The {@code Filterable} interface defines the contract for objects that can be filtered.
 * Classes that implement this interface provide a list of fields that can be used as filter criteria.
 */
public interface Filterable {

    /**
     * Gets an array of field names that can be used as filter criteria for objects implementing this interface.
     *
     * @return An array of field names that can be used as filter criteria.
     */
    String[] getFilterableFieldNames();
}
