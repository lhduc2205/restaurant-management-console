package com.lhduc.common;

import com.lhduc.common.enums.Origin;
import com.lhduc.common.filtered.FilterCondition;
import com.lhduc.common.filtered.PropertyFilter;
import com.lhduc.model.entity.MenuItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PropertyFilterTest {
    private final PropertyFilter propertyFilter = new PropertyFilter();

    @Test
    @DisplayName("Test filter by conditions for List<Object>")
    void filter() {
        MenuItem menuItem1 = new MenuItem(1, "Mi cay", "7 cap do", 49_000, Origin.VIETNAMESE, 1);
        MenuItem menuItem2 = new MenuItem(2, "Com tron", "Rong bien", 30_000, Origin.VIETNAMESE, 1);

        List<MenuItem> menuItems = new ArrayList<>(Arrays.asList(menuItem1, menuItem2));

        FilterCondition conditions = new FilterCondition();
        conditions.add("name", "c");

        List<MenuItem> result = propertyFilter.filter(menuItems, conditions);

        assertEquals(menuItems.size(), result.size());
    }

    @Test
    @DisplayName("Test filter by conditions with nonexistent field")
    void filterWithNonexistentField() {
        MenuItem menuItem1 = new MenuItem(1, "Mi cay", "7 cap do", 49_000, Origin.VIETNAMESE, 1);
        MenuItem menuItem2 = new MenuItem(2, "Com tron", "Rong bien", 30_000, Origin.VIETNAMESE, 1);

        List<MenuItem> menuItems = new ArrayList<>(Arrays.asList(menuItem1, menuItem2));

        Map<String, Object> conditions = new HashMap<>();
        conditions.put("name1", "c");

        List<MenuItem> result = propertyFilter.filter(menuItems, new FilterCondition(conditions));

        assertEquals(result.size(), 0);
    }
}