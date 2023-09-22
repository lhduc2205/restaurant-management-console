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

    public MenuItemRepositoryImpl(Datasource datasource) {
        this.datasource = datasource;
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
    public Optional<MenuItem> getById(int id) {
        return menuItems.stream().filter(item -> item.getId() == id).findFirst();
    }

    @Override
    public Optional<MenuItem> create(MenuItem menuItem) {
        MenuItem createdMenuItem = new MenuItem(menuItem);
        createdMenuItem.setId(this.generateId());

        this.menuItems.add(createdMenuItem);

        this.save();

        return Optional.of(createdMenuItem);
    }

    @Override
    public Optional<MenuItem> update(MenuItem menuItem) {
        Optional<MenuItem> existedMenuItem = this.getById(menuItem.getId());

        if (existedMenuItem.isEmpty()) {
            return Optional.empty();
        }

        menuItems.remove(existedMenuItem.get());
        menuItems.add(menuItem);

        this.save();

        return Optional.of(menuItem);
    }

    @Override
    public void deleteById(int id) {
        this.menuItems.forEach(item -> {
            if (item.getId() == id) {
                item.setDeleted(true);
            }
        });

        this.save();
    }

    @Override
    public void deleteAllMenuItemsByMenuId(int menuId) {
        for (MenuItem menuItem : menuItems) {
            if (menuItem.getMenuId() == menuId) {
                menuItem.setDeleted(true);
            }
        }

        this.save();
    }

    private void save() {
        this.datasource.saveAll(this.menuItems.stream().toList(), MenuItem.class);
    }

    private int generateId() {
        return menuItems.isEmpty() ? 1 : menuItems.last().getId() + 1;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems.stream().toList();
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = new TreeSet<>(menuItems);
    }
}
