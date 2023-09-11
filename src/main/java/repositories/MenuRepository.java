package repositories;

import exceptions.NotFoundException;
import exceptions.ResourceAlreadyExistsException;
import models.entities.Menu;

import java.util.List;
import java.util.Optional;

/**
 * This interface defines the contract for interacting with menu data.
 */
public interface MenuRepository {
    /**
     * Retrieves all menus from the repository.
     *
     * @return A list of all menus.
     */
    public List<Menu> getAll();

    /**
     * If the menu present, retrieves one by its ID from the repository, otherwise throws exception.
     *
     * @param id The ID of the menu to retrieve.
     * @return The menu with the specified ID, or null if not found.
     * @throws NotFoundException If the menu isn't exist.
     */
    public Optional<Menu> getById(int id) throws NotFoundException;

    /**
     * If it does not contain menu base on id, creates a new menu in the repository, otherwise throws exception.
     *
     * @param menu The menu to be created.
     * @return The menu after created.
     * @throws ResourceAlreadyExistsException If the menu is already exist.
     */
    public Menu create(Menu menu) throws ResourceAlreadyExistsException;

    /**
     * If the menu present, updates an existing menu in the repository, otherwise throws exception.
     *
     * @param menu The updated menu.
     * @throws NotFoundException If the menu isn't exist.
     */
    public Menu update(Menu menu) throws NotFoundException;

    /**
     * If the menu present, Deletes one from the repository, otherwise throws exception.
     *
     * @param menu The menu to be deleted.
     * @throws NotFoundException If the menu isn't exist.
     */
    public void delete(Menu menu) throws NotFoundException;

    /**
     * If the menu present, deletes one by its ID from the repository, otherwise throws exception.
     *
     * @param id The ID of the menu to delete.
     * @throws NotFoundException If the menu isn't exist.
     */
    public void deleteById(int id) throws NotFoundException;
}
