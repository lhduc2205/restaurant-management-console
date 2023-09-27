package com.lhduc.model.dto;

import com.lhduc.common.enums.Origin;

public class MenuItemDto {
    private int id;
    private String name;
    private String description;
    private double price;
    private Origin origin;
    private int menuId;

    public MenuItemDto() {
    }

    public MenuItemDto(String name, String description, double price, Origin origin, int menuId) {
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
}
