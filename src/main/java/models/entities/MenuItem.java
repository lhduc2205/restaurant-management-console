package models.entities;


import common.enums.Origin;

import java.util.ArrayList;

public class MenuItem implements Comparable<MenuItem>{
    private int id;
    private String name;
    private String description;
    private String image;
    private double price;
    private Origin origin;
    private int menuId;

    public MenuItem(){}

    public MenuItem(String name, String description, double price, Origin origin, int menuId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.origin = origin;
        this.menuId = menuId;
    }

    public MenuItem(String name, String description, String image, double price, Origin origin) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.origin = origin;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MenuItem item) {
            return this.id == item.id;
        }
        return false;
    }

    @Override
    public int compareTo(MenuItem item) {
        return this.id - item.id;
    }
}
