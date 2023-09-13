package controllers;

import common.anotations.Controller;
import common.patterns.servicelocator.ServiceLocator;
import models.dtos.MenuItemDto;
import services.MenuItemService;
import services.impl.MenuItemServiceImpl;

import java.util.List;

@Controller
public class MenuItemController implements CrudController<MenuItemDto> {
    private final MenuItemService menuItemService;

    public MenuItemController() {
        this.menuItemService = ServiceLocator.getService(MenuItemServiceImpl.class.getName());
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
