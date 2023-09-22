package com.lhduc.services.impl;

import com.lhduc.common.patterns.servicelocator.ServiceLocator;
import com.lhduc.exceptions.NotFoundException;
import com.lhduc.models.mappers.ModelMapper;
import com.lhduc.models.dtos.MenuItemDto;
import com.lhduc.models.entities.MenuItem;
import com.lhduc.repositories.MenuItemRepository;
import com.lhduc.repositories.MenuRepository;
import com.lhduc.repositories.impl.MenuItemRepositoryImpl;
import com.lhduc.repositories.impl.MenuRepositoryImpl;
import com.lhduc.services.MenuItemService;

import java.util.List;

public class MenuItemServiceImpl implements MenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final MenuRepository menuRepository;
    private final ModelMapper mapper;

    public MenuItemServiceImpl() {
        this.menuItemRepository = ServiceLocator.getService(MenuItemRepositoryImpl.class.getName());
        this.menuRepository = ServiceLocator.getService(MenuRepositoryImpl.class.getName());
        this.mapper = ServiceLocator.getService(ModelMapper.class.getName());
    }

    /**
     * Retrieves a list of all entities of type MenuItemDto.
     *
     * @return A list of all entities.
     */
    @Override
    public List<MenuItemDto> getAll() {
        List<MenuItem> menuItems = menuItemRepository.getAll()
                .stream()
                .filter(item -> !item.isDeleted())
                .toList();

        return mapper.mapList(menuItems, MenuItemDto.class);
    }

    /**
     * Retrieves a list of MenuItemDto objects associated with the specified menu ID.
     *
     * @param menuId The id of the menu for which menu items are to be retrieved.
     * @return A list of MenuItemDto.
     */
    @Override
    public List<MenuItemDto> getByMenuId(int menuId) {
        List<MenuItem> menuItems = menuItemRepository.getByMenuId(menuId)
                .stream()
                .filter(item -> !item.isDeleted())
                .toList();

        return mapper.mapList(menuItems, MenuItemDto.class);
    }

    /**
     * Retrieves an entity of type MenuItemDto by its unique identifier.
     *
     * @param id The unique identifier of the entity.
     * @return The entity with the specified ID, or null if not found.
     */
    @Override
    public MenuItemDto getById(int id) {
        MenuItem existedMenuItem = checkExistedMenuItemById(id);

        if (existedMenuItem.isDeleted()) {
            throw new NotFoundException("Menu Item", id);
        }

        return mapper.map(existedMenuItem, MenuItemDto.class);
    }

    /**
     * Creates a new entity of type MenuItemDto.
     *
     * @param menuItemDto The entity to create.
     * @return The created entity.
     */
    @Override
    public MenuItemDto create(MenuItemDto menuItemDto) {
        menuRepository.getById(menuItemDto.getMenuId())
                .orElseThrow(() -> new NotFoundException("Menu" + menuItemDto.getMenuId()));

        MenuItem menuItem = mapper.map(menuItemDto, MenuItem.class);

        return mapper.map(menuItemRepository.create(menuItem), MenuItemDto.class);
    }

    /**
     * Updates an existing entity of type MenuItemDto.
     *
     * @param menuItemDto The entity to update.
     * @return The updated entity.
     */
    @Override
    public MenuItemDto update(MenuItemDto menuItemDto) {
        this.checkExistedMenuItemById(menuItemDto.getId());

        MenuItem menuItem = mapper.map(menuItemDto, MenuItem.class);

        return mapper.map(menuItemRepository.update(menuItem), MenuItemDto.class);
    }

    /**
     * Deletes an entity of type MenuItemDto by its unique identifier.
     *
     * @param id The unique identifier of the entity to delete.
     */
    @Override
    public void deleteById(int id) {
        this.checkExistedMenuItemById(id);

        menuItemRepository.deleteById(id);

        System.out.println("Delete menu with id " + id + " successfully!");
    }

    @Override
    public void deleteAllMenuItemsByMenuId(int menuId) {
        menuItemRepository.deleteAllMenuItemsByMenuId(menuId);
    }

    private MenuItem checkExistedMenuItemById(int id) {
        return menuItemRepository.getById(id).orElseThrow(() -> new NotFoundException("Menu Item" + id));
    }
}
