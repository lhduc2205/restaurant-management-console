package services;

import dtos.MenuItemDto;

import java.util.List;

public interface MenuItemService {
    public List<MenuItemDto> getAllMenuItem();

    public MenuItemDto getMenuItemById(int id);

    public MenuItemDto createMenuItem(MenuItemDto menuItem);

    public void updateMenuItem(MenuItemDto menuItem);

    public void deleteMenuItem(MenuItemDto menuItem);

    public void deleteMenuItemById(int id);

    public void deleteAllMenuItemsByMenuId(int menuId);
}
