package com.lhduc.model.dto;

import com.lhduc.common.enums.MenuCategory;
import com.lhduc.common.filtered.Filterable;

import java.util.ArrayList;
import java.util.List;

public class MenuDto implements Filterable {
    private int id;
    private MenuCategory category;
    private List<MenuItemDto> items = new ArrayList<>();

    public MenuDto() {}

    public MenuDto(MenuCategory category) {
        this.category = category;
    }

    public MenuDto(int id, MenuCategory category) {
        this.id = id;
        this.category = category;
    }

    public void addItem(MenuItemDto item) {
        items.add(item);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MenuCategory getCategory() {
        return category;
    }

    public void setCategory(MenuCategory category) {
        this.category = category;
    }

    public List<MenuItemDto> getItems() {
        return items;
    }

    public void setItems(List<MenuItemDto> items) {
        this.items = items;
    }

    public String[] getFilterableFieldNames() {
        return new String[]{"id", "category"};
    }
}
