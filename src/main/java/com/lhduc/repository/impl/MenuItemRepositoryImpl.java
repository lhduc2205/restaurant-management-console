package com.lhduc.repository.impl;

import com.lhduc.common.constant.AppConstant;
import com.lhduc.datasource.Datasource;
import com.lhduc.model.entity.MenuItem;
import com.lhduc.repository.MenuItemRepository;
import com.lhduc.util.DatasourceUtil;
import com.lhduc.util.IdGenerator;

import java.util.List;
import java.util.Optional;

public class MenuItemRepositoryImpl implements MenuItemRepository {
    private final Datasource datasource;
    private final IdGenerator idGenerator;

    public MenuItemRepositoryImpl() {
        this.datasource = DatasourceUtil.getDatasourceInstance();
        this.idGenerator = new IdGenerator(AppConstant.MENU_ITEM_ID_TXT_FILE);
    }

    public MenuItemRepositoryImpl(Datasource datasource) {
        this.datasource = datasource;
        this.idGenerator = new IdGenerator(AppConstant.MENU_ITEM_ID_TXT_FILE);
    }

    @Override
    public List<MenuItem> getAll() {
        return this.datasource.readData(MenuItem.class);
    }

    @Override
    public List<MenuItem> getByMenuId(int menuId) {
        List<MenuItem> menuItems = this.getAll();
        return menuItems.stream().filter(item -> item.getMenuId() == menuId).toList();
    }

    @Override
    public Optional<MenuItem> getById(int id) {
        List<MenuItem> menuItems = this.datasource.readData(MenuItem.class);
        return menuItems.stream().filter(item -> item.getId() == id).findFirst();
    }

    @Override
    public void create(MenuItem menuItem) {
        List<MenuItem> menuItems = this.getAll();
        MenuItem createdMenuItem = new MenuItem(menuItem);
        this.generateId(createdMenuItem);

        menuItems.add(createdMenuItem);

        this.save(menuItems);
    }

    @Override
    public void update(MenuItem menuItem) {
        List<MenuItem> menuItems = this.getAll();
        Optional<MenuItem> existedMenuItem = this.getById(menuItem.getId());

        if (existedMenuItem.isEmpty()) {
            return;
        }

        menuItems.remove(existedMenuItem.get());
        menuItems.add(menuItem);

        this.save(menuItems);
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

    private void generateId(MenuItem menuItem) {
        menuItem.setId(idGenerator.getGeneratedId());
    }
}
