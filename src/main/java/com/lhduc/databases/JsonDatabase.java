package com.lhduc.databases;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lhduc.RestaurantManagementApplication;
import com.lhduc.common.constants.FolderConstant;
import com.lhduc.common.patterns.servicelocator.ServiceLocator;
import com.lhduc.configs.ApplicationConfig;
import com.lhduc.exceptions.ApplicationRuntimeException;
import com.lhduc.utils.FileUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonDatabase implements Database {
    private final ObjectMapper objectMapper;

    public JsonDatabase() {
        this.objectMapper = ServiceLocator.getService(ObjectMapper.class.getName());
    }

    @Override
    public <T> List<T> readData(Class<T> valueType) {
        try {
            String fileName = this.getFileName(valueType);
            FileUtil.createDirectoryAndFileIfNotExist(fileName);

            InputStream inputStream = new FileInputStream(fileName);
            return objectMapper.readValue(inputStream, objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, valueType));
        } catch (Exception e) {
            throw new ApplicationRuntimeException("Can not read file: " + this.getFileName(valueType));
        }
    }

//    @Override
//    public void writeData(Object data) {
//        List<Object> dataFromDb = readData(Object.class);
//        dataFromDb.add(data);
//
//        this.saveData(dataFromDb);
//    }

    @Override
    public <T> void saveAll(List<T> data, Class<T> classType) {
        try {
            objectMapper.writeValue(new File(this.getFileName(classType)), data);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String getFileName(Class<?> valueType) {
        if (ApplicationConfig.isRunningFromJAR()) {
            return FolderConstant.JSON_DATABASE_PATH + valueType.getSimpleName().toLowerCase() + ".json";
        }
        return FolderConstant.JSON_DATABASE_PATH + "/" + valueType.getSimpleName().toLowerCase() + ".json";
    }
}
