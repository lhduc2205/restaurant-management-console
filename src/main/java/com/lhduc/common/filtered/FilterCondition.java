package com.lhduc.common.filtered;

import java.util.Map;

/**
 * The {@code FilterCondition} class represents filter conditions for querying data.
 * It encapsulates filter criteria in the form of a key-value map, where keys are field names,
 * and values are filter values associated with those fields.
 */

public class FilterCondition {
    private final Map<String, Object> data;

    public FilterCondition(Map<String, Object> data) {
        this.data = data;
    }

    public Map<String, Object> getData() {
        return data;
    }
}
