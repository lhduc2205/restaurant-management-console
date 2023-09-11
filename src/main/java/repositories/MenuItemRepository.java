package repositories;

import exceptions.NotFoundException;
import exceptions.ResourceAlreadyExistsException;
import models.entities.MenuItem;

import java.util.List;
import java.util.Optional;

public interface MenuItemRepository extends CrudRepository<MenuItem> {
    public List<MenuItem> getByMenuId(int menuId);

    public Optional<MenuItem> getById(int id) throws NotFoundException;

    public void deleteAllMenuItemsByMenuId(int menuId);
}
