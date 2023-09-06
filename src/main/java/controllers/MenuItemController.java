package controllers;

import models.MenuItem;
import services.MenuItemService;

import java.util.List;

public class MenuItemController implements CrudController<MenuItem> {
    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }
    
    @Override
    public List<MenuItem> getAll() {
        return menuItemService.getAllMenuItem();
    }

    @Override
    public MenuItem getById(int id) {
        return menuItemService.getMenuItemById(id);
    }

    @Override
    public MenuItem create(MenuItem menuItem) {
        return menuItemService.createMenuItem(menuItem);
    }

    @Override
    public void update(MenuItem menuItem) {
        menuItemService.updateMenuItem(menuItem);
    }

    @Override
    public void delete(MenuItem menuItem) {
        menuItemService.deleteMenuItem(menuItem);
    }

    @Override
    public void deleteById(int id) {
        menuItemService.deleteMenuItemById(id);
    }
}
