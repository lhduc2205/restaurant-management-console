package controllers;

import cores.anotations.Controller;
import dtos.MenuItemDto;
import services.MenuItemService;
import views.MenuItemConsoleView;

import java.util.List;

@Controller
public class MenuItemController implements CrudController<MenuItemDto> {
    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }
    
    @Override
    public List<MenuItemDto> getAll() {
       return menuItemService.getAllMenuItem();
    }

    @Override
    public MenuItemDto getById(int id) {
        return menuItemService.getMenuItemById(id);
    }

    @Override
    public MenuItemDto create(MenuItemDto menuItemDto) {
        return menuItemService.createMenuItem(menuItemDto);
    }

    @Override
    public void update(MenuItemDto menuItemDto) {
        menuItemService.updateMenuItem(menuItemDto);
    }

    @Override
    public void delete(MenuItemDto menuItemDto) {
        menuItemService.deleteMenuItem(menuItemDto);
    }

    @Override
    public void deleteById(int id) {
        menuItemService.deleteMenuItemById(id);
    }

    public void deleteAllByMenuId(int menuId) {
        menuItemService.deleteAllMenuItemsByMenuId(menuId);
    }
}
