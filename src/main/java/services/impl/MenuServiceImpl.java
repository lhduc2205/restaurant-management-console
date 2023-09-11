package services.impl;

import models.mappers.ModelMapper;
import models.dtos.MenuDto;
import models.dtos.MenuItemDto;
import models.entities.Menu;
import repositories.MenuItemRepository;
import repositories.MenuRepository;
import services.MenuService;

import java.util.List;
import java.util.Optional;

public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final MenuItemRepository menuItemRepository;
    private final ModelMapper mapper;

    public MenuServiceImpl(MenuRepository menuRepository, MenuItemRepository menuItemRepository, ModelMapper mapper) {
        this.menuRepository = menuRepository;
        this.menuItemRepository = menuItemRepository;
        this.mapper = mapper;
    }

    @Override
    public List<MenuDto> getAll() {
        List<MenuDto> menus = mapper.mapList(menuRepository.getAll(), MenuDto.class);

        menus.forEach(menu -> {
            List<MenuItemDto> items = mapper.mapList(menuItemRepository.getByMenuId(menu.getId()), MenuItemDto.class);
            menu.setItems(items);
        });

        return menus;
    }

    @Override
    public MenuDto getById(int id) {
        Optional<Menu> existedMenu = menuRepository.getById(id);

        if (existedMenu.isEmpty()) {
            System.out.println("Menu with id " + id + " does not exist!");
            return null;
        }

        MenuDto menuDto = mapper.map(existedMenu, MenuDto.class);
        List<MenuItemDto> itemsDto = mapper.mapList(menuItemRepository.getByMenuId(menuDto.getId()), MenuItemDto.class);

        menuDto.setItems(itemsDto);

        return menuDto;
    }

    @Override
    public MenuDto create(MenuDto menuDto) {
        Optional<Menu> existingMenu = menuRepository.getById(menuDto.getId());

        if (existingMenu.isPresent()) {
            System.out.println("Menu with id " + menuDto.getId() + " has already existed. Create failed.");
            return null;
        }

        Menu createdMenu = mapper.map(menuDto, Menu.class);
        MenuDto createdMenuDto = mapper.map(menuRepository.create(createdMenu), MenuDto.class);

        System.out.println("Create menu successfully!");
        return createdMenuDto;
    }

    @Override
    public void update(MenuDto updatedMenu) {
        Optional<Menu> existingMenu = menuRepository.getById(updatedMenu.getId());

        if (existingMenu.isEmpty()) {
            System.out.println("Menu with id " + updatedMenu.getId() + " not found. Update failed.");
            return;
        }

        Menu menuToUpdate = existingMenu.get();
        menuToUpdate.setCategory(updatedMenu.getCategory());

        Menu updatedMenuResult = menuRepository.update(menuToUpdate);

        if (updatedMenuResult != null) {
            System.out.println("Menu with id " + updatedMenu.getId() + " updated successfully.");
        }
    }

    @Override
    public void delete(MenuDto menuDto) {
        menuRepository.delete(mapper.map(menuDto, Menu.class));
    }

    @Override
    public void deleteById(int deletedId) {
        Optional<Menu> existingMenu = menuRepository.getById(deletedId);

        if (existingMenu.isEmpty()) {
            System.out.println("Menu with id " + deletedId + " not found. Update failed.");
            return;
        }

        menuItemRepository.deleteAllMenuItemsByMenuId(deletedId);
        menuRepository.deleteById(deletedId);

        System.out.println("Delete menu with id " + deletedId + " successfully!");
    }
}
