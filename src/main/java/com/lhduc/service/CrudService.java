package com.lhduc.service;


import com.lhduc.exception.NotFoundException;
import com.lhduc.exception.ResourceAlreadyExistsException;

import java.util.List;

/**
 * The CrudService interface defines CRUD (Create, Read, Update, Delete) operations
 * for managing entities of type T in a service layer.
 *
 * @param <T> The type of entity managed by this service.
 */
public interface CrudService<T> {
    /**
     * Retrieves a list of all entities of type T from the repository.
     *
     * @return A list of all entities in the repository.
     */
    List<T> getAll();

    /**
     * Retrieves an entity of type T by its unique identifier.
     *
     * @param id The unique identifier of the entity.
     * @return An optional containing the entity, or an empty optional if not found.
     * @throws NotFoundException If the entity with the specified ID is not found.
     */
    T getById(int id) throws NotFoundException;

    /**
     * Creates a new entity of type T in the repository.
     *
     * @param t The entity to create.
     * @throws ResourceAlreadyExistsException If an entity with the same unique identifier already exists.
     */
    void create(T t) throws ResourceAlreadyExistsException;

    /**
     * Updates an existing entity of type T in the repository.
     *
     * @param t The entity to update.
     * @throws NotFoundException If the entity to be updated is not found.
     */
    void update(T t) throws NotFoundException;

    /**
     * Deletes an entity of type T by its unique identifier from the repository.
     *
     * @param id The unique identifier of the entity to delete.
     * @throws NotFoundException If the entity with the specified ID is not found.
     */
    void delete(int id) throws NotFoundException;
}
