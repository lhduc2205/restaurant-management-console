package controllers;

import cores.anotations.Controller;
import dtos.MenuDto;
import entities.Menu;
import services.MenuService;
import views.MenuConsoleView;

import java.util.List;

@Controller
public class MenuController implements CrudController<MenuDto> {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public List<MenuDto> getAll() {
        return menuService.getAllMenu();
    }

    @Override
    public MenuDto getById(int id) {
        return menuService.getMenuById(id);
    }

    @Override
    public MenuDto create(MenuDto menuDto) {
        return menuService.createMenu(menuDto);
    }

    @Override
    public void update(MenuDto menuDto) {
        menuService.updateMenu(menuDto);
    }

    @Override
    public void delete(MenuDto menuDto) {
        menuService.deleteMenu(menuDto);
    }

    @Override
    public void deleteById(int id) {
        menuService.deleteMenuById(id);
    }
}
