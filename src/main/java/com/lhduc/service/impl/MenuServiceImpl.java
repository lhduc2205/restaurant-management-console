package com.lhduc.service.impl;

import com.lhduc.common.pattern.servicelocator.ServiceLocator;
import com.lhduc.exception.NotFoundException;
import com.lhduc.model.mapper.ModelMapper;
import com.lhduc.model.dto.MenuDto;
import com.lhduc.model.dto.MenuItemDto;
import com.lhduc.model.entity.Menu;
import com.lhduc.repository.MenuRepository;
import com.lhduc.repository.impl.MenuRepositoryImpl;
import com.lhduc.service.MenuItemService;
import com.lhduc.service.MenuService;

import java.util.List;

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
        Menu existedMenu = this.checkExistedMenuById(id);

        MenuDto menuDto = mapper.map(existedMenu, MenuDto.class);
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
        Menu existingMenu = this.checkExistedMenuById(updatedMenu.getId());

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
        this.checkExistedMenuById(deletedId);

        menuItemService.deleteAllMenuItemsByMenuId(deletedId);
        menuRepository.deleteById(deletedId);
    }

    private Menu checkExistedMenuById(int id) {
        return menuRepository.getById(id).orElseThrow(() -> new NotFoundException("Menu with", id));
    }
}
