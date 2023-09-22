package com.lhduc.repositories;

import com.lhduc.exceptions.NotFoundException;
import com.lhduc.models.entities.MenuItem;

import java.util.List;

public interface MenuItemRepository extends CrudRepository<MenuItem> {
    public List<MenuItem> getByMenuId(int menuId);

    public void deleteAllMenuItemsByMenuId(int menuId);
}
