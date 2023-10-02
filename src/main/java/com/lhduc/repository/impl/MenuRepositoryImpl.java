package com.lhduc.repository.impl;

import com.lhduc.datasource.Datasource;
import com.lhduc.model.entity.Menu;
import com.lhduc.repository.MenuRepository;
import com.lhduc.util.DatasourceUtil;

import java.util.List;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

public class MenuRepositoryImpl implements MenuRepository {
    private final Datasource datasource;

    public MenuRepositoryImpl() {
        this.datasource = DatasourceUtil.getDatasourceInstance();
    }

    public MenuRepositoryImpl(Datasource datasource) {
        this.datasource = datasource;
    }

    @Override
    public List<Menu> getAll() {
        return this.datasource.readData(Menu.class);
    }

    @Override
    public Optional<Menu> getById(int id) {
        List<Menu> menus = this.getAll();

        return menus.stream().filter(e -> e.getId() == id).findFirst();
    }

    @Override
    public Optional<Menu> create(Menu menu) {
        List<Menu> menus = this.getAll();

        Menu createdMenu = new Menu(menu);
        createdMenu.setId(this.generateId(menus));

        menus.add(createdMenu);

        this.save(menus);

        return Optional.of(createdMenu);
    }

    @Override
    public Optional<Menu> update(Menu menu) {
        List<Menu> menus = this.getAll();
        Optional<Menu> existedMenu = this.getById(menu.getId());

        if (existedMenu.isEmpty()) {
            return Optional.empty();
        }

        menus.remove(existedMenu.get());

        Menu updatedMenu = new Menu(menu);
        menus.add(updatedMenu);

        this.save(menus);

        return Optional.of(updatedMenu);
    }

    @Override
    public void deleteById(int id) {
        List<Menu> menus = this.getAll();
        menus.removeIf(m -> m.getId() == id);

        this.save(menus);
    }

    private void save(List<Menu> menus) {
        this.datasource.saveAll(menus.stream().toList(), Menu.class);
    }

    private int generateId(List<Menu> menus) {
        return menus.isEmpty() ? 1 : menus.get(menus.size() - 1).getId() + 1;
    }
}
