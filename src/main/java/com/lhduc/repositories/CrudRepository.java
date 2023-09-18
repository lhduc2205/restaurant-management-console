package com.lhduc.repositories;

import com.lhduc.exceptions.NotFoundException;
import com.lhduc.exceptions.ResourceAlreadyExistsException;

import java.util.List;
import java.util.Optional;

/**
 * The CrudRepository interface defines CRUD (Create, Read, Update, Delete) operations
 * for managing entities of type T in a data repository.
 *
 * @param <T> The type of entity managed by this repository.
 */
public interface CrudRepository<T> {

    /**
     * Retrieves a list of all entities of type T from the repository.
     *
     * @return A list of all entities in the repository.
     */
    public List<T> getAll();

    /**
     * Retrieves an entity of type T by its unique identifier.
     *
     * @param id The unique identifier of the entity.
     * @return An optional containing the entity, or an empty optional if not found.
     * @throws NotFoundException If the entity with the specified ID is not found.
     */
    public Optional<T> getById(int id) throws NotFoundException;

    /**
     * Creates a new entity of type T in the repository.
     *
     * @param t The entity to create.
     * @return The created entity.
     * @throws ResourceAlreadyExistsException If an entity with the same unique identifier already exists.
     */
    public T create(T t) throws ResourceAlreadyExistsException;

    /**
     * Updates an existing entity of type T in the repository.
     *
     * @param t The entity to update.
     * @return The updated entity.
     * @throws NotFoundException If the entity to be updated is not found.
     */
    public T update(T t) throws NotFoundException;

    /**
     * Deletes an entity of type T by its unique identifier from the repository.
     *
     * @param id The unique identifier of the entity to delete.
     * @throws NotFoundException If the entity with the specified ID is not found.
     */
    public void deleteById(int id) throws NotFoundException;
}
