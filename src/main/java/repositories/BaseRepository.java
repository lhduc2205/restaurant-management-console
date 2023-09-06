package repositories;

import databases.Database;

import java.util.List;

public abstract class BaseRepository<T> {
    public BaseRepository() {}

    protected abstract Database getDatabase();

    protected abstract List<T> getData();

    public void save() {
        getDatabase().saveAll(getData());
    }

    public void saveAll(List<?> data) {
        getDatabase().saveAll(data);
    }
}
