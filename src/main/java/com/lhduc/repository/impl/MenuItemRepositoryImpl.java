package com.lhduc.repository.impl;

import com.lhduc.datasource.Datasource;
import com.lhduc.model.entity.MenuItem;
import com.lhduc.repository.MenuItemRepository;
import com.lhduc.util.DatasourceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MenuItemRepositoryImpl implements MenuItemRepository {
    private final Datasource datasource;

    public MenuItemRepositoryImpl() {
        this.datasource = DatasourceUtil.getDatasourceInstance();
    }

    public MenuItemRepositoryImpl(Datasource datasource) {
        this.datasource = datasource;
    }

    @Override
    public List<MenuItem> getAll() {
        List<MenuItem> menuItems = this.datasource.readData(MenuItem.class);
        return menuItems.stream().filter(item -> !item.isDeleted()).collect(Collectors.toList());
    }

    @Override
    public List<MenuItem> getByMenuId(int menuId) {
        List<MenuItem> menuItems = this.getAll();
        return menuItems.stream().filter(item -> item.getMenuId() == menuId).toList();
    }

    @Override
    public Optional<MenuItem> getById(int id) {
        List<MenuItem> menuItems = this.getAll();
        return menuItems.stream().filter(item -> item.getId() == id).findFirst();
    }

    @Override
    public Optional<MenuItem> create(MenuItem menuItem) {
        List<MenuItem> menuItems = this.getAll();
        MenuItem createdMenuItem = new MenuItem(menuItem);
        createdMenuItem.setId(this.generateId(menuItems));

        menuItems.add(createdMenuItem);

        this.save(menuItems);

        return Optional.of(createdMenuItem);
    }

    @Override
    public Optional<MenuItem> update(MenuItem menuItem) {
        List<MenuItem> menuItems = this.getAll();
        Optional<MenuItem> existedMenuItem = this.getById(menuItem.getId());

        if (existedMenuItem.isEmpty()) {
            return Optional.empty();
        }

        menuItems.remove(existedMenuItem.get());
        menuItems.add(menuItem);

        this.save(menuItems);

        return Optional.of(menuItem);
    }

    @Override
    public void deleteById(int id) {
        List<MenuItem> menuItems = this.getAll();
        menuItems.forEach(item -> {
            if (item.getId() == id) {
                item.setDeleted(true);
            }
        });

        this.save(menuItems);
    }

    @Override
    public void deleteAllMenuItemsByMenuId(int menuId) {
        List<MenuItem> menuItems = this.getAll();
        for (MenuItem menuItem : menuItems) {
            if (menuItem.getMenuId() == menuId) {
                menuItem.setDeleted(true);
            }
        }

        this.save(menuItems);
    }

    private void save(List<MenuItem> menuItems) {
        this.datasource.saveAll(menuItems, MenuItem.class);
    }

    private int generateId(List<MenuItem> menuItems) {
        return menuItems.isEmpty() ? 1 : menuItems.get(menuItems.size() - 1).getId() + 1;
    }
}
