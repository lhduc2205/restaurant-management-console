package com.lhduc.model.dto;

import com.lhduc.common.enums.Origin;
import com.lhduc.common.filtered.Filterable;

import java.util.Objects;

public class MenuItemDto implements Filterable {
    private int id;
    private String name;
    private String description;
    private double price;
    private Origin origin;
    private int menuId;

    public MenuItemDto() {
    }

    public MenuItemDto(MenuItemDto item) {
        this.id = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.price = item.getPrice();
        this.origin = item.getOrigin();
        this.menuId = item.getMenuId();
    }

    public MenuItemDto(String name, String description, double price, Origin origin, int menuId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.origin = origin;
        this.menuId = menuId;
    }

    public MenuItemDto(int id, String name, String description, double price, Origin origin, int menuId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.origin = origin;
        this.menuId = menuId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String[] getFilterableFieldNames() {
        return new String[]{"id", "name", "description", "price", "origin", "menuId"};
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        MenuItemDto that = (MenuItemDto) object;
        return id == that.id &&
                Double.compare(price, that.price) == 0 &&
                menuId == that.menuId &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                origin == that.origin;
    }
}
