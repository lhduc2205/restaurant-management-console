package com.lhduc.controllers;

import com.lhduc.common.anotations.Controller;
import com.lhduc.common.patterns.servicelocator.ServiceLocator;
import com.lhduc.models.dtos.MenuDto;
import com.lhduc.services.MenuService;
import com.lhduc.services.impl.MenuServiceImpl;

import java.util.List;

@Controller
public class MenuController implements CrudController<MenuDto> {
    private final MenuService menuService;

    public MenuController() {
        this.menuService = ServiceLocator.getService(MenuServiceImpl.class.getName());
    }

    @Override
    public List<MenuDto> getAll() {
        return menuService.getAll();
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
