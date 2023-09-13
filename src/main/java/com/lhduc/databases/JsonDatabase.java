package com.lhduc.databases;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lhduc.common.constants.AppPath;
import com.lhduc.common.patterns.servicelocator.ServiceLocator;
import com.lhduc.utils.FileUtil;

import java.io.File;
import java.io.IOException;
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
            File file = FileUtil.createDirectoryAndFile(this.getFileName(valueType));

            return objectMapper.readValue(file, objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, valueType));
        } catch (IOException ignored) {
            return new ArrayList<>();
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
        return AppPath.JSON_DATABASE + "/" + valueType.getSimpleName().toLowerCase() + ".json";
    }
}
