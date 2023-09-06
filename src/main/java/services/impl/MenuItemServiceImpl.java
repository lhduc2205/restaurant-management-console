package services.impl;

import models.MenuItem;
import org.modelmapper.ModelMapper;
import repositories.MenuItemRepository;
import services.MenuItemService;

import java.util.List;

public class MenuItemServiceImpl implements MenuItemService {
    private final MenuItemRepository menuItemRepository;

    public MenuItemServiceImpl(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public List<MenuItem> getAllMenuItem() {
        return null;
    }

    @Override
    public MenuItem getMenuItemById(int id) {
        return null;
    }

    @Override
    public MenuItem createMenuItem(MenuItem menuItem) {

        System.out.println("Create menu item successfully! ");
        return menuItemRepository.create(menuItem, menuItem.getMenuId());
    }

    @Override
    public void updateMenuItem(MenuItem menuItem) {

    }

    @Override
    public void deleteMenuItem(MenuItem menuItem) {

    }

    @Override
    public void deleteMenuItemById(int id) {

    }
}
