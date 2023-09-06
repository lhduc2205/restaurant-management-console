package services;

import models.MenuItem;

import java.util.List;

public interface MenuItemService {
    public List<MenuItem> getAllMenuItem();

    public MenuItem getMenuItemById(int id);

    public MenuItem createMenuItem(MenuItem menuItem);

    public void updateMenuItem(MenuItem menuItem);

    public void deleteMenuItem(MenuItem menuItem);

    public void deleteMenuItemById(int id);
}
