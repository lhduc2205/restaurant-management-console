package com.lhduc.common.enums;

public enum CrudOption {
    SHOW("Show"),
    CREATE("Create"),
    UPDATE("Update"),
    DELETE("Delete");

    private final String description;

    CrudOption(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void displayTitle(String title) {
        System.out.println("\n(" + this.getDescription() + " " + title + "): ");
    }
}
