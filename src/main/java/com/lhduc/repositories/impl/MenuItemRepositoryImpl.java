package com.lhduc.repositories.impl;

import com.lhduc.exceptions.NotFoundException;
import com.lhduc.exceptions.ResourceAlreadyExistsException;
import com.lhduc.common.patterns.servicelocator.ServiceLocator;
import com.lhduc.datasources.Datasource;
import com.lhduc.models.entities.MenuItem;
import com.lhduc.repositories.MenuItemRepository;

import java.util.List;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

public class MenuItemRepositoryImpl implements MenuItemRepository {
    private SortedSet<MenuItem> menuItems = new TreeSet<>();
    private final Datasource datasource;

    public MenuItemRepositoryImpl() {
        this.datasource = ServiceLocator.getService(Datasource.class.getName());
        this.getAll();
    }

    @Override
    public List<MenuItem> getAll() {
        List<MenuItem> menuItemsFromDb = this.datasource.readData(MenuItem.class);
        this.menuItems = new TreeSet<>(menuItemsFromDb);
        return menuItems.stream().filter(item -> !item.isDeleted()).toList();
    }

    @Override
    public List<MenuItem> getByMenuId(int menuId) {
        return menuItems.stream().filter(item -> item.getMenuId() == menuId).toList();
    }

    @Override
    public Optional<MenuItem> getById(int id) throws NotFoundException {
        return menuItems.stream().filter(item -> item.getId() == id).findFirst();
    }

    @Override
    public MenuItem create(MenuItem menuItem) throws ResourceAlreadyExistsException, NotFoundException {
        menuItem.setId(this.generateId());
        this.menuItems.add(menuItem);

        this.save();

        return menuItem;
    }

    @Override
    public MenuItem update(MenuItem menuItem) throws NotFoundException {
        MenuItem existedMenuItem = menuItems
                .stream()
                .filter(m -> m.getId() == menuItem.getId())
                .findFirst()
                .orElseThrow(() ->  new NotFoundException("Menu with id " + menuItem.getId() + " does not exist"));

        menuItems.remove(existedMenuItem);
        menuItems.add(menuItem);

        this.save();

        return menuItem;
    }

    @Override
    public void deleteById(int id) throws NotFoundException {
        MenuItem menuItem = this.menuItems
                .stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Menu with id " + id + " does not exist"));

        this.menuItems.remove(menuItem);

        menuItem.setDeleted(true);

        this.menuItems.add(menuItem);

        this.save();
    }

    @Override
    public void deleteAllMenuItemsByMenuId(int menuId) {
        this.menuItems.forEach(item -> {
            if (item.getMenuId() == menuId) {
                item.setDeleted(true);
            }
        });

        this.save();
    }

    private void save() {
        this.datasource.saveAll(this.menuItems.stream().toList(), MenuItem.class);
    }

    private int generateId() {
        return menuItems.isEmpty() ? 1 : menuItems.last().getId() + 1;
    }

}
