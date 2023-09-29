package com.lhduc.service;

import com.lhduc.common.filtered.FilterCondition;
import com.lhduc.model.dto.MenuItemDto;

import java.util.List;

public interface MenuItemService extends CrudService<MenuItemDto> {
    public List<MenuItemDto> getAll(FilterCondition filterCondition);
    public List<MenuItemDto> getByMenuId(int menuId);
    public void deleteAllMenuItemsByMenuId(int menuId);
}
