package controllers;

import models.Menu;
import services.MenuService;

import java.util.List;

public class MenuController implements CrudController<Menu> {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public List<Menu> getAll() {
        return menuService.getAllMenu();
    }

    @Override
    public Menu getById(int id) {
        return menuService.getMenuById(id);
    }

    @Override
    public Menu create(Menu menu) {
        return menuService.createMenu(menu);
    }

    @Override
    public void update(Menu menu) {
        menuService.updateMenu(menu);
    }

    @Override
    public void delete(Menu menu) {
        menuService.deleteMenu(menu);
    }

    @Override
    public void deleteById(int id) {
        menuService.deleteMenuById(id);
    }
}
