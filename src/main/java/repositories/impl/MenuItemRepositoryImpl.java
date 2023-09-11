package repositories.impl;

import databases.impl.csv.MenuItemCsvDatabase;
import exceptions.NotFoundException;
import exceptions.ResourceAlreadyExistsException;
import common.patterns.servicelocator.ServiceLocator;
import databases.Database;
import models.entities.MenuItem;
import repositories.BaseRepository;
import repositories.MenuItemRepository;

import java.util.List;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

public class MenuItemRepositoryImpl extends BaseRepository<MenuItem> implements MenuItemRepository {
    private SortedSet<MenuItem> menuItems = new TreeSet<>();
    private final Database database;

    public MenuItemRepositoryImpl() {
        this.database = ServiceLocator.getService(MenuItemCsvDatabase.class.getName());
        this.getAll();
    }

    @Override
    public List<MenuItem> getAll() {
        List<MenuItem> menuItemsFromDb = this.database.readData(MenuItem.class);
        this.menuItems = new TreeSet<>(menuItemsFromDb);
        return menuItems.stream().toList();
    }

    @Override
    public List<MenuItem> getByMenuId(int menuId) {
        return menuItems.stream().filter(item -> item.getMenuId() == menuId).toList();
    }

    @Override
    public Optional<MenuItem> getById(int id) throws NotFoundException {
        return Optional.empty();
    }

    @Override
    public MenuItem create(MenuItem menuItem) throws ResourceAlreadyExistsException, NotFoundException {

//        menuItem.setId(this.generateId(existedMenu.getItems()));
//
//        existedMenu.getItems().add(menuItem);
//        menuRepository.update(existedMenu);

        menuItem.setId(this.generateId());

        this.menuItems.add(menuItem);

        super.save();

        return menuItem;
    }

    @Override
    public MenuItem update(MenuItem menuItem) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(MenuItem menuItem) throws NotFoundException {

    }

    @Override
    public void deleteById(int id) throws NotFoundException {

    }

    @Override
    public void deleteAllMenuItemsByMenuId(int menuId) {
        menuItems.removeIf(menuItem -> menuItem.getMenuId() == menuId);

        super.save();
    }

    @Override
    protected Database getDatabase() {
        return database;
    }

    @Override
    protected List<MenuItem> getData() {
        return this.menuItems.stream().toList();
    }

    private int generateId() {
        return menuItems.isEmpty() ? 1 : menuItems.last().getId() + 1;
    }

}
