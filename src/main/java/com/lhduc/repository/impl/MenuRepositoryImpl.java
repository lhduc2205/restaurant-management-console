package com.lhduc.repository.impl;

import com.lhduc.common.constant.AppConstant;
import com.lhduc.datasource.Datasource;
import com.lhduc.model.entity.Menu;
import com.lhduc.repository.MenuRepository;
import com.lhduc.util.DatasourceUtil;
import com.lhduc.util.IdGenerator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MenuRepositoryImpl implements MenuRepository {
    private final Datasource datasource;
    private final IdGenerator idGenerator;

    public MenuRepositoryImpl() {
        this.datasource = DatasourceUtil.getDatasourceInstance();
        this.idGenerator = new IdGenerator(AppConstant.MENU_ID_TXT_FILE);
    }

    public MenuRepositoryImpl(Datasource datasource) {
        this.datasource = datasource;
        this.idGenerator = new IdGenerator(AppConstant.MENU_ID_TXT_FILE);
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
    public void create(Menu menu) {
        List<Menu> menus = this.getAll();

        Menu createdMenu = new Menu(menu);
        this.generateId(createdMenu);

        menus.add(createdMenu);

        this.save(menus);
    }

    @Override
    public void update(Menu menu) {
        List<Menu> menus = this.getAll();
        Optional<Menu> existedMenu = this.getById(menu.getId());

        if (existedMenu.isEmpty()) {
            return;
        }

        menus.remove(existedMenu.get());

        Menu updatedMenu = new Menu(menu);
        menus.add(updatedMenu);

        this.save(menus);
    }

    @Override
    public void deleteById(int id) {
        List<Menu> menus = this.getAll();
        menus.removeIf(m -> m.getId() == id);

        this.save(menus);
    }

    private void save(List<Menu> menus) {
        this.datasource.saveAll(menus, Menu.class);
    }

    private void generateId(Menu menu) {
        menu.setId(idGenerator.getGeneratedId());
    }
}
