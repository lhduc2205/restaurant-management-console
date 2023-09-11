package controllers;

import common.anotations.Controller;
import models.dtos.MenuDto;
import services.MenuService;

import java.util.List;

@Controller
public class MenuController implements CrudController<MenuDto> {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
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
