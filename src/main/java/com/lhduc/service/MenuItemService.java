package com.lhduc.service;

import com.lhduc.model.dto.MenuItemDto;

import java.util.List;

public interface MenuItemService extends CrudService<MenuItemDto> {
    public List<MenuItemDto> getByMenuId(int menuId);
    public void deleteAllMenuItemsByMenuId(int menuId);
}
