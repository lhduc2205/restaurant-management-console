package repositories.impl;

import common.patterns.servicelocator.ServiceLocator;
import exceptions.NotFoundException;
import databases.Database;
import models.entities.Menu;
import repositories.MenuRepository;

import java.util.List;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

public class MenuRepositoryImpl implements MenuRepository {
    private SortedSet<Menu> menus = new TreeSet<>();
    private final Database database;

    public MenuRepositoryImpl() {
        this.database = ServiceLocator.getService(Database.class.getName());
        this.getAll();
    }

    @Override
    public List<Menu> getAll() {
        List<Menu> menusFromDb = this.database.readData(Menu.class);
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
        this.database.saveAll(this.menus.stream().toList(), Menu.class);
    }
    
    private int generateId() {
        return menus.isEmpty() ? 1 : menus.last().getId() + 1;
    }
}
