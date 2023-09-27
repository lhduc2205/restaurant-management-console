package com.lhduc.repository.impl;

import com.lhduc.common.pattern.servicelocator.ServiceLocator;
import com.lhduc.datasource.Datasource;
import com.lhduc.model.entity.Menu;
import com.lhduc.repository.MenuRepository;

import java.util.List;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

public class MenuRepositoryImpl implements MenuRepository {
    private SortedSet<Menu> menus = new TreeSet<>();
    private final Datasource datasource;

    public MenuRepositoryImpl() {
        this.datasource = ServiceLocator.getService(Datasource.class.getName());
        this.getAll();
    }

    public MenuRepositoryImpl(Datasource datasource) {
        this.datasource = datasource;
        this.getAll();
    }

    @Override
    public List<Menu> getAll() {
        List<Menu> menusFromDb = this.datasource.readData(Menu.class);
        this.menus = new TreeSet<>(menusFromDb);
        return menus.stream().toList();
    }

    @Override
    public Optional<Menu> getById(int id) {
        return menus.stream().filter(e -> e.getId() == id).findFirst();
    }

    @Override
    public Optional<Menu> create(Menu menu) {
        Menu createdMenu = new Menu(menu);
        createdMenu.setId(this.generateId());

        this.menus.add(createdMenu);

        this.save();

        return Optional.of(createdMenu);
    }

    @Override
    public Optional<Menu> update(Menu menu) {
        Optional<Menu> existedMenu = this.getById(menu.getId());

        if (existedMenu.isEmpty()) {
            return Optional.empty();
        }

        this.menus.remove(existedMenu.get());

        Menu updatedMenu = new Menu(menu);
        this.menus.add(updatedMenu);

        this.save();

        return Optional.of(updatedMenu);
    }

    @Override
    public void deleteById(int id) {
        this.menus.removeIf(m -> m.getId() == id);

        this.save();
    }

    private void save() {
        this.datasource.saveAll(this.menus.stream().toList(), Menu.class);
    }

    private int generateId() {
        return menus.isEmpty() ? 1 : menus.last().getId() + 1;
    }

    public List<Menu> getMenus() {
        return menus.stream().toList();
    }

    public void setMenus(List<Menu> menus) {
        this.menus = new TreeSet<>(menus);
    }
}
