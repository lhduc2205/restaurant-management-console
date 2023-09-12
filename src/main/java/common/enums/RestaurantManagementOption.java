package common.enums;

public enum RestaurantManagementOption {
    MENU("Menu"),
    MENU_ITEM("Menu Item"),
    ORDER ("Order");

    private final String description;

    RestaurantManagementOption(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
