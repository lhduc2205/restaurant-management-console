package services.impl;

import cores.utils.MapperUtil;
import dtos.MenuItemDto;
import entities.MenuItem;
import repositories.MenuItemRepository;
import services.MenuItemService;

import java.util.List;

public class MenuItemServiceImpl implements MenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final MapperUtil mapper;

    public MenuItemServiceImpl(MenuItemRepository menuItemRepository, MapperUtil mapperUtil) {
        this.menuItemRepository = menuItemRepository;
        this.mapper = mapperUtil;
    }

    @Override
    public List<MenuItemDto> getAllMenuItem() {
        return null;
    }

    @Override
    public MenuItemDto getMenuItemById(int id) {
        return null;
    }

    @Override
    public MenuItemDto createMenuItem(MenuItemDto menuItemDto) {
        MenuItem menuItem = mapper.map(menuItemDto, MenuItem.class);

        System.out.println("Create menu item successfully! ");
        MenuItem menuItemResult = menuItemRepository.create(menuItem, menuItemDto.getMenuId());

        return mapper.map(menuItemResult, MenuItemDto.class);
    }

    @Override
    public void updateMenuItem(MenuItemDto menuItemDto) {

    }

    @Override
    public void deleteMenuItem(MenuItemDto menuItemDto) {

    }

    @Override
    public void deleteMenuItemById(int id) {

    }

    @Override
    public void deleteAllMenuItemsByMenuId(int menuId) {
        menuItemRepository.deleteAllMenuItemsByMenuId(menuId);
    }
}
