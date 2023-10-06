package com.lhduc.common.enums;

public enum CrudOption {
    SHOW("Show"),
    CREATE("Create"),
    UPDATE("Update"),
    DELETE("Delete"),
    FILTER("Filter"),
    BACK("Back to previous");

    private final String description;

    CrudOption(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getDescription(String suffix) {
        return description + " " + suffix;
    }

    public static int getLength() {
        return CrudOption.values().length;
    }

    public static CrudOption get(int index) {
        return CrudOption.values()[index];
    }
}
