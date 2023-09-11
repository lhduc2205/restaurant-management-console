package common.enums;

public enum MenuCategory {
    FOOD("Food"),
    DRINK("Drink");

    private final String description;

    MenuCategory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
