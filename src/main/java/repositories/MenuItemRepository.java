package repositories;

import cores.exceptions.NotFoundException;
import cores.exceptions.ResourceAlreadyExistsException;
import models.MenuItem;

import java.util.List;
import java.util.Optional;

public interface MenuItemRepository {
    public List<MenuItem> getAll();

    public List<MenuItem> getByMenuId(int menuId);

    public Optional<MenuItem> getById(int id) throws NotFoundException;

    public MenuItem create(MenuItem menuItem, int menuId) throws ResourceAlreadyExistsException;

    public void update(MenuItem menuItem) throws NotFoundException;

    public void delete(MenuItem menuItem) throws NotFoundException;

    public void deleteById(int id) throws NotFoundException;
}
