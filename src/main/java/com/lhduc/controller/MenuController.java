package com.lhduc.controller;

import com.lhduc.common.anotation.Controller;
import com.lhduc.common.filtered.FilterCondition;
import com.lhduc.common.pattern.servicelocator.ServiceLocator;
import com.lhduc.model.dto.MenuDto;
import com.lhduc.service.MenuService;
import com.lhduc.service.impl.MenuServiceImpl;

import java.util.List;

@Controller
public class MenuController implements CrudController<MenuDto> {
    private final MenuService menuService;

    public MenuController() {
        this.menuService = ServiceLocator.getService(MenuServiceImpl.class);
    }

    @Override
    public List<MenuDto> getAll() {
        return menuService.getAll();
    }

    public List<MenuDto> getAll(FilterCondition condition) {
        return menuService.getAll(condition);
    }

    @Override
    public MenuDto getById(int id) {
        return menuService.getById(id);
    }

    @Override
    public MenuDto create(MenuDto menuDto) {
        return menuService.create(menuDto);
    }

    @Override
    public MenuDto update(MenuDto menuDto) {
        return menuService.update(menuDto);
    }

    @Override
    public void deleteById(int id) {
        menuService.deleteById(id);
    }
}
