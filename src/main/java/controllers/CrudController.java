package controllers;

import java.util.List;

public interface CrudController<T> {
    public List<T> getAll();

    public T getById(int id);

    public T create(T t);

    public void update(T t);

    public void delete(T t);

    public void deleteById(int id);
}
