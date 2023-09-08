package services;

import dtos.MenuDto;

import java.util.List;

/**
 * This interface defines the contract for handling business logic and interacting with menu repository.
 */
public interface MenuService {
    public List<MenuDto> getAllMenu();

    public MenuDto getMenuById(int id);

    public MenuDto createMenu(MenuDto menu);

    public void updateMenu(MenuDto menu);

    public void deleteMenu(MenuDto menu);

    public void deleteMenuById(int id);
}
