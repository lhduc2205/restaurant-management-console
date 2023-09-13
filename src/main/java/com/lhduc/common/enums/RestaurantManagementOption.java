package com.lhduc.common.enums;

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
}
