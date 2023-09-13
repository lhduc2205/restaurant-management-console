package services.impl;

import common.patterns.servicelocator.ServiceLocator;
import exceptions.NotFoundException;
import models.dtos.MenuDto;
import models.entities.Menu;
import models.mappers.ModelMapper;
import models.dtos.MenuItemDto;
import models.entities.MenuItem;
import repositories.MenuItemRepository;
import repositories.MenuRepository;
import repositories.impl.MenuItemRepositoryImpl;
import repositories.impl.MenuRepositoryImpl;
import services.MenuItemService;

import java.util.List;
import java.util.Optional;

public class MenuItemServiceImpl implements MenuItemService {
    private final MenuRepository menuRepository;
    private final MenuItemRepository menuItemRepository;
    private final ModelMapper mapper;

    public MenuItemServiceImpl() {
        this.menuRepository = ServiceLocator.getService(MenuRepositoryImpl.class.getName());
        this.menuItemRepository = ServiceLocator.getService(MenuItemRepositoryImpl.class.getName());
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
        MenuItem existedMenuItem = menuItemRepository.getById(id)
                .orElseThrow(() -> new NotFoundException("Menu Item with id " + id + " does not exist!"));

        if (existedMenuItem.isDeleted()) {
            throw new NotFoundException("Menu Item with id " + id + " does not exist!");
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
                .orElseThrow(() -> new NotFoundException("Menu with id " + menuItemDto.getMenuId() + " not found. Update failed."));

        MenuItem menuItem = mapper.map(menuItemDto, MenuItem.class);

        System.out.println("Create menu item successfully! ");
        MenuItem menuItemResult = menuItemRepository.create(menuItem);

        return mapper.map(menuItemResult, MenuItemDto.class);
    }

    /**
     * Updates an existing entity of type MenuItemDto.
     *
     * @param menuItemDto The entity to update.
     * @return The updated entity.
     */
    @Override
    public MenuItemDto update(MenuItemDto menuItemDto) {
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
        menuItemRepository.deleteById(id);

        System.out.println("Delete menu with id " + id + " successfully!");
    }

    @Override
    public void deleteAllMenuItemsByMenuId(int menuId) {
        menuItemRepository.deleteAllMenuItemsByMenuId(menuId);
    }
}
