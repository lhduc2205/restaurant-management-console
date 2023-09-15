package com.lhduc.repositories.impl;

import com.lhduc.common.patterns.servicelocator.ServiceLocator;
import com.lhduc.exceptions.NotFoundException;
import com.lhduc.datasources.Datasource;
import com.lhduc.models.entities.Menu;
import com.lhduc.repositories.MenuRepository;

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
    public Menu create(Menu menu) {
        if (menus.contains(menu)) {
            System.out.println("Menu with id: " + menu.getId() + " is already exist!");
            return null;
        }

        menu.setId(this.generateId());
        this.menus.add(menu);

        this.save();

        return menu;
    }

    @Override
    public Menu update(Menu menu) {
        Menu existedMenu = menus.stream().filter(m -> menu.getId() == m.getId()).findFirst().orElse(null);

        if (existedMenu == null) {
            throw new NotFoundException("Menu with id " + menu.getId() + " does not exist");
        }

        menus.remove(existedMenu);
        menus.add(menu);

        this.save();

        return menu;
    }

    @Override
    public void deleteById(int id) throws NotFoundException {
        Menu existedMenu = this.menus.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Menu with id " + id + " does not exist"));

        this.menus.remove(existedMenu);

        this.save();
    }

    private void save() {
        this.datasource.saveAll(this.menus.stream().toList(), Menu.class);
    }
    
    private int generateId() {
        return menus.isEmpty() ? 1 : menus.last().getId() + 1;
    }
}
