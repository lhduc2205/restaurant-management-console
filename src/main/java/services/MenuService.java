package services;

import models.Menu;

import java.util.List;

/**
 * This interface defines the contract for handling business logic and interacting with menu repository.
 */
public interface MenuService {
    public List<Menu> getAllMenu();

    public Menu getMenuById(int id);

    public Menu createMenu(Menu menu);

    public void updateMenu(Menu menu);

    public void deleteMenu(Menu menu);

    public void deleteMenuById(int id);
}
