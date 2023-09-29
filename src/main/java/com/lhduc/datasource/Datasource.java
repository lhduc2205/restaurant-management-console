package com.lhduc.datasource;

import com.lhduc.exception.ApplicationRuntimeException;

import java.util.List;

/**
 * The Datasource interface defines methods for reading and saving data of a specified type.
 */
public interface Datasource {
    /**
     * Reads data of a specified type from the datasource.
     *
     * @param <T>       The type of data to read.
     * @param classType The Class object representing the type T.
     * @return A List containing the data of the specified type.
     * @throws ApplicationRuntimeException if the classType parameter is null or if
     *                                  there is an issue reading the data.
     */
    public <T> List<T> readData(Class<T> classType) throws ApplicationRuntimeException;

    /**
     * Saves a list of data of a specified type to the datasource.
     *
     * @param <T>       The type of data to save.
     * @param data      The List of data to be saved.
     * @param classType The Class object representing the type T.
     * @throws ApplicationRuntimeException if the data or classType parameter is null,
     *                                  or if there is an issue saving the data.
     */
    public <T> void saveAll(List<T> data, Class<T> classType) throws ApplicationRuntimeException;
}
