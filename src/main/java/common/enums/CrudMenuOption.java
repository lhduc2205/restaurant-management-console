package common.enums;

public enum CrudMenuOption {
    SHOW("Show menu"),
    CREATE("Create menu"),
    UPDATE("Update menu"),
    DELETE("Delete menu");

    private final String description;

    CrudMenuOption(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
