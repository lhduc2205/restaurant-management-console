package com.lhduc.databases;

import java.util.List;

public interface Database {
    public <T> List<T> readData(Class<T> t);

    public <T> void saveAll(List<T> data, Class<T> classType);
}
