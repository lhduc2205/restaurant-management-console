package services.impl;

import common.patterns.servicelocator.ServiceLocator;
import models.dtos.MenuDto;
import models.entities.Menu;
import models.mappers.ModelMapper;
import models.dtos.MenuItemDto;
import models.entities.MenuItem;
import repositories.MenuItemRepository;
import repositories.impl.MenuItemRepositoryImpl;
import services.MenuItemService;

import java.util.List;
import java.util.Optional;

public class MenuItemServiceImpl implements MenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final ModelMapper mapper;

    public MenuItemServiceImpl() {
        this.menuItemRepository = ServiceLocator.getService(MenuItemRepositoryImpl.class.getName());
        this.mapper = ServiceLocator.getService(ModelMapper.class.getName());
    }

    @Override
    public List<MenuItemDto> getAll() {
        return null;
    }

    @Override
    public MenuItemDto getById(int id) {
        Optional<MenuItem> existedMenuItem = menuItemRepository.getById(id);

        if (existedMenuItem.isEmpty()) {
            System.out.println("Menu Item with id " + id + " does not exist!");
            return null;
        }

        return mapper.map(existedMenuItem.get(), MenuItemDto.class);
    }

    @Override
    public MenuItemDto create(MenuItemDto menuItemDto) {
        MenuItem menuItem = mapper.map(menuItemDto, MenuItem.class);

        System.out.println("Create menu item successfully! ");
        MenuItem menuItemResult = menuItemRepository.create(menuItem);

        return mapper.map(menuItemResult, MenuItemDto.class);
    }

    @Override
    public void update(MenuItemDto menuItemDto) {
        MenuItem menuItem = mapper.map(menuItemDto, MenuItem.class);

        menuItemRepository.update(menuItem);
    }

    @Override
    public void delete(MenuItemDto menuItemDto) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteAllMenuItemsByMenuId(int menuId) {
        menuItemRepository.deleteAllMenuItemsByMenuId(menuId);
    }
}
