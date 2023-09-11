package services;

import models.dtos.MenuItemDto;

import java.util.List;

public interface MenuItemService extends CrudService<MenuItemDto> {
    public void deleteAllMenuItemsByMenuId(int menuId);
}
