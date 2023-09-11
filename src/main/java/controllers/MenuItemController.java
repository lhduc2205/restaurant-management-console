package controllers;

import common.anotations.Controller;
import models.dtos.MenuItemDto;
import services.MenuItemService;

import java.util.List;

@Controller
public class MenuItemController implements CrudController<MenuItemDto> {
    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }
    
    @Override
    public List<MenuItemDto> getAll() {
       return menuItemService.getAll();
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
    public void update(MenuItemDto menuItemDto) {
        menuItemService.update(menuItemDto);
    }

    @Override
    public void delete(MenuItemDto menuItemDto) {
        menuItemService.delete(menuItemDto);
    }

    @Override
    public void deleteById(int id) {
        menuItemService.deleteById(id);
    }

    public void deleteAllByMenuId(int menuId) {
        menuItemService.deleteAllMenuItemsByMenuId(menuId);
    }
}
