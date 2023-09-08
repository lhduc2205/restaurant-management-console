package dtos;

import com.google.gson.annotations.Expose;
import cores.enums.MenuCategory;
import entities.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuDto {
    private int id;
    private MenuCategory category;
    private List<MenuItemDto> items;

    public MenuDto(){
        items = new ArrayList<>();
    }

    public MenuDto(MenuCategory category) {
        this.category = category;
        this.items = new ArrayList<>();
    }

    public MenuDto(int id, MenuCategory category) {
        this.id = id;
        this.category = category;
        this.items = new ArrayList<>();
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
}
