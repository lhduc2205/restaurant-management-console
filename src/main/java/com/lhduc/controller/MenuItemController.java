package com.lhduc.controller;

import com.lhduc.common.anotation.Controller;
import com.lhduc.common.filtered.FilterCondition;
import com.lhduc.model.dto.MenuItemDto;
import com.lhduc.service.MenuItemService;
import com.lhduc.service.impl.MenuItemServiceImpl;

import java.util.List;

@Controller
public class MenuItemController implements CrudController<MenuItemDto> {
    private final MenuItemService menuItemService;

    public MenuItemController() {
        this.menuItemService = new MenuItemServiceImpl();
    }
    
    @Override
    public List<MenuItemDto> getAll() {
       return menuItemService.getAll();
    }

    public List<MenuItemDto> getAll(FilterCondition filterCondition) {
        return menuItemService.getAll(filterCondition);
    }

    @Override
    public MenuItemDto getById(int id) {
        return menuItemService.getById(id);
    }

    @Override
    public MenuItemDto create(MenuItemDto menuItemDto) {
        return menuItemService.create(menuItemDto);
    }

    @Override
    public MenuItemDto update(MenuItemDto menuItemDto) {
        return menuItemService.update(menuItemDto);
    }

    @Override
    public void deleteById(int id) {
        menuItemService.deleteById(id);
    }

    public void deleteAllByMenuId(int menuId) {
        menuItemService.deleteAllMenuItemsByMenuId(menuId);
    }
}
