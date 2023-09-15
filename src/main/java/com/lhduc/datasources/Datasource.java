package com.lhduc.datasources;

import java.util.List;

public interface Datasource {
    public <T> List<T> readData(Class<T> t);

    public <T> void saveAll(List<T> data, Class<T> classType);
}
