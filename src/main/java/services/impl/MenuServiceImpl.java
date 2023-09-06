package services.impl;

import models.Menu;
import repositories.MenuItemRepository;
import repositories.MenuRepository;
import services.MenuService;

import java.util.List;
import java.util.Optional;

public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final MenuItemRepository menuItemRepository;

    public MenuServiceImpl(MenuRepository menuRepository, MenuItemRepository menuItemRepository) {
        this.menuRepository = menuRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public List<Menu> getAllMenu() {
        List<Menu> menus = menuRepository.getAll();

        menus.forEach(menu -> menu.setItems(menuItemRepository.getByMenuId(menu.getId())));

        return menus;
    }

    @Override
    public Menu getMenuById(int id) {
        return menuRepository.getById(id).orElse(null);
    }

    @Override
    public Menu createMenu(Menu createdMenu) {
        Optional<Menu> existingMenu = menuRepository.getById(createdMenu.getId());

        if(existingMenu.isPresent()) {
            System.out.println("Menu with id " + createdMenu.getId() + " has already existed. Create failed.");
            return null;
        }

        System.out.println("Create menu successfully!");
        return menuRepository.create(createdMenu);
    }

    @Override
    public void updateMenu(Menu updatedMenu) {
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
    public void deleteMenu(Menu menu) {
        menuRepository.delete(menu);
    }

    @Override
    public void deleteMenuById(int deletedId) {
        Optional<Menu> existingMenu = menuRepository.getById(deletedId);

        if (existingMenu.isEmpty()) {
            System.out.println("Menu with id " + deletedId + " not found. Update failed.");
            return;
        }

        menuRepository.deleteById(deletedId);
        System.out.println("Delete menu with id " + deletedId + " successfully!");
    }
}
