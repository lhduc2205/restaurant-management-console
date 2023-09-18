package com.lhduc.services.impl;

import com.lhduc.common.patterns.servicelocator.ServiceLocator;
import com.lhduc.exceptions.NotFoundException;
import com.lhduc.models.mappers.ModelMapper;
import com.lhduc.models.dtos.MenuDto;
import com.lhduc.models.dtos.MenuItemDto;
import com.lhduc.models.entities.Menu;
import com.lhduc.repositories.MenuRepository;
import com.lhduc.repositories.impl.MenuRepositoryImpl;
import com.lhduc.services.MenuItemService;
import com.lhduc.services.MenuService;

import java.util.List;
import java.util.Optional;

public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final MenuItemService menuItemService;
    private final ModelMapper mapper;

    public MenuServiceImpl() {
        this.menuRepository = ServiceLocator.getService(MenuRepositoryImpl.class.getName());
        this.menuItemService = ServiceLocator.getService(MenuItemServiceImpl.class.getName());
        this.mapper = ServiceLocator.getService(ModelMapper.class.getName());
    }

    /**
     * Retrieves a list of all entities of type MenuDto.
     *
     * @return A list of all entities.
     */
    @Override
    public List<MenuDto> getAll() {
        List<MenuDto> menus = mapper.mapList(menuRepository.getAll(), MenuDto.class);

        menus.forEach(menu -> {
            List<MenuItemDto> items = menuItemService.getByMenuId(menu.getId());
            menu.setItems(items);
        });

        return menus;
    }

    /**
     * Retrieves an entity of type MenuDto by its unique identifier.
     *
     * @param id The unique identifier of the entity.
     * @return The entity with the specified ID, or null if not found.
     */
    @Override
    public MenuDto getById(int id) {
        Optional<Menu> existedMenu = menuRepository.getById(id);

        if (existedMenu.isEmpty()) {
            System.out.println("Menu with id " + id + " does not exist!");
            return null;
        }

        MenuDto menuDto = mapper.map(existedMenu.get(), MenuDto.class);
        List<MenuItemDto> itemsDto = menuItemService.getByMenuId(id);

        menuDto.setItems(itemsDto);

        return menuDto;
    }

    /**
     * Creates a new entity of type MenuDto.
     *
     * @param menuDto The entity to create.
     * @return The created entity.
     */
    @Override
    public MenuDto create(MenuDto menuDto) {
        Menu createdMenu = mapper.map(menuDto, Menu.class);
        MenuDto createdMenuDto = mapper.map(menuRepository.create(createdMenu), MenuDto.class);

        System.out.println("Create menu successfully!");
        return createdMenuDto;
    }

    /**
     * Updates an existing entity of type MenuDto.
     *
     * @param updatedMenu The entity to update.
     * @return The updated entity.
     */
    @Override
    public MenuDto update(MenuDto updatedMenu) {
        Menu existingMenu = menuRepository.getById(updatedMenu.getId())
                .orElseThrow(() -> new NotFoundException("Menu with id " + updatedMenu.getId() + " not found. Update failed."));

        existingMenu.setCategory(updatedMenu.getCategory());

        return mapper.map(menuRepository.update(existingMenu), MenuDto.class);
    }

    /**
     * Deletes an entity of type MenuDto by its unique identifier.
     *
     * @param deletedId The unique identifier of the entity to delete.
     */
    @Override
    public void deleteById(int deletedId) {
        Optional<Menu> existingMenu = menuRepository.getById(deletedId);

        if (existingMenu.isEmpty()) {
            System.out.println("Menu with id " + deletedId + " not found. Delete failed.");
            return;
        }

        menuItemService.deleteAllMenuItemsByMenuId(deletedId);
        menuRepository.deleteById(deletedId);

        System.out.println("Delete menu with id " + deletedId + " successfully!");
    }
}
