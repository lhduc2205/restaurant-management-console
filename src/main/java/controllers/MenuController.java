package controllers;

import common.anotations.Controller;
import common.patterns.servicelocator.ServiceLocator;
import models.dtos.MenuDto;
import services.MenuService;
import services.impl.MenuServiceImpl;

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
    public void update(MenuDto menuDto) {
        menuService.update(menuDto);
    }

    @Override
    public void delete(MenuDto menuDto) {
        menuService.delete(menuDto);
    }

    @Override
    public void deleteById(int id) {
        menuService.deleteById(id);
    }
}
