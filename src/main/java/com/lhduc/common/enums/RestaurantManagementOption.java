package com.lhduc.common.enums;

import java.util.Arrays;
import java.util.List;

public enum RestaurantManagementOption {
    MENU("Menu"),
    MENU_ITEM("Menu Item"),
    ORDER ("Order"),
    ORDER_DETAIL ("Order Detail");

    private final String description;

    RestaurantManagementOption(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static List<String> getDescriptions() {
        return Arrays.stream(RestaurantManagementOption.values()).map(RestaurantManagementOption::getDescription).toList();
    }

    public static int getLength() {
        return RestaurantManagementOption.values().length;
    }

    public static RestaurantManagementOption get(int index) {
        return RestaurantManagementOption.values()[index];
    }
}
