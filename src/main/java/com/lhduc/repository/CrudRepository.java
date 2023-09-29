package com.lhduc.repository;

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
     */
    public Optional<T> getById(int id);

    /**
     * Creates a new entity of type T in the repository.
     *
     * @param t The entity to create.
     * @return The created entity.
     */
    public Optional<T> create(T t);

    /**
     * Updates an existing entity of type T in the repository.
     *
     * @param t The entity to update.
     * @return The updated entity.
     */
    public Optional<T> update(T t);

    /**
     * Deletes an entity of type T by its unique identifier from the repository.
     *
     * @param id The unique identifier of the entity to delete.
     */
    public void deleteById(int id);
}
