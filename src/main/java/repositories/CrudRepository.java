package repositories;

import exceptions.NotFoundException;
import exceptions.ResourceAlreadyExistsException;

import java.util.List;
import java.util.Optional;

/**
 * This interface defines the CRUD contract for interacting with menu data.
 */
public interface CrudRepository<T> {
    public List<T> getAll();

    public Optional<T> getById(int id) throws NotFoundException;

    public T create(T t) throws ResourceAlreadyExistsException;

    public T update(T t) throws NotFoundException;

    public void delete(T t) throws NotFoundException;

    public void deleteById(int id) throws NotFoundException;
}
