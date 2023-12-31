package com.lhduc.model.entity;

import com.lhduc.common.enums.MenuCategory;

public class Menu implements Comparable<Menu> {
    private int id;
    private MenuCategory category;

    public Menu() {
    }

    public Menu(Menu menu) {
        this.id = menu.id;
        this.category = menu.category;
    }

    public Menu(MenuCategory category) {
        this.category = category;
    }

    public Menu(int id, MenuCategory category) {
        this.id = id;
        this.category = category;
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

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Menu menu) {
            return this.id == menu.id && this.category.equals(menu.category);
        }
        return false;
    }

    @Override
    public int compareTo(Menu menu) {
        return this.id - menu.id;
    }
}
