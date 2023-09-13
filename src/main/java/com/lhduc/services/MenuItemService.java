package com.lhduc.services;

import com.lhduc.models.dtos.MenuItemDto;

import java.util.List;

public interface MenuItemService extends CrudService<MenuItemDto> {
    public List<MenuItemDto> getByMenuId(int menuId);
    public void deleteAllMenuItemsByMenuId(int menuId);
}
