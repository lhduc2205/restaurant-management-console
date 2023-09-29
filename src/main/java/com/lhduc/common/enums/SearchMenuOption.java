package com.lhduc.common.enums;

public enum SearchMenuOption {
    FIND_BY_ID("Find by id"),
    FIND_BY_CATEGORY("Find by category");
//    QUERY("Query");

    private final String description;

    SearchMenuOption(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
