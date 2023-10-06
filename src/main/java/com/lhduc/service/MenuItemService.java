package com.lhduc.service;

import com.lhduc.common.filtered.FilterCondition;
import com.lhduc.model.dto.MenuItemDto;

import java.util.List;

public interface MenuItemService extends CrudService<MenuItemDto> {
    List<MenuItemDto> getAll(FilterCondition filterCondition);

    List<MenuItemDto> getByMenuId(int menuId);

    void deleteAllMenuItemsByMenuId(int menuId);
}
