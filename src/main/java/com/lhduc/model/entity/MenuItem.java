package com.lhduc.model.entity;


import com.lhduc.common.enums.Origin;

public class MenuItem implements Comparable<MenuItem> {
    private int id;
    private String name;
    private String description;
    private double price;
    private Origin origin;
    private int menuId;
    private boolean isDeleted = false;

    public MenuItem() {}

    public MenuItem(MenuItem menuItem) {
        this.id = menuItem.id;
        this.name = menuItem.name;
        this.description = menuItem.description;
        this.price = menuItem.price;
        this.origin = menuItem.origin;
        this.menuId = menuItem.menuId;
        this.isDeleted = menuItem.isDeleted;
    }

    public MenuItem(int id, String name, String description, double price, Origin origin, int menuId) {
        this.id = id;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MenuItem item) {
            return this.id == item.id &&
                    this.name.equals(item.name) &&
                    this.description.equals(item.description) &&
                    this.price == item.price &&
                    this.origin == item.origin &&
                    this.menuId == item.menuId;
        }
        return false;
    }

    @Override
    public int compareTo(MenuItem item) {
        return this.id - item.id;
    }
}
