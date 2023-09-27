package com.lhduc.repository;

import com.lhduc.model.entity.MenuItem;

import java.util.List;

public interface MenuItemRepository extends CrudRepository<MenuItem> {
    public List<MenuItem> getByMenuId(int menuId);

    public void deleteAllMenuItemsByMenuId(int menuId);
}
