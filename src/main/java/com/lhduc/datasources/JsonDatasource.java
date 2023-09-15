package com.lhduc.datasources;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lhduc.common.constants.FolderConstant;
import com.lhduc.common.patterns.servicelocator.ServiceLocator;
import com.lhduc.configs.ApplicationConfig;
import com.lhduc.exceptions.ApplicationRuntimeException;
import com.lhduc.utils.FileUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonDatasource implements Datasource {
    private final ObjectMapper objectMapper;

    public JsonDatasource() {
        this.objectMapper = ServiceLocator.getService(ObjectMapper.class.getName());
    }

    @Override
    public <T> List<T> readData(Class<T> valueType) {
        try {
            String fileName = this.getFileName(valueType);
            File file = FileUtil.createDirectoryAndFileIfNotExist(fileName);

            if (file.length() == 0) {
                return new ArrayList<>();
            }

            return objectMapper.readValue(file, objectMapper.getTypeFactory()
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
        return FolderConstant.JSON_DATABASE_PATH + "/" + valueType.getSimpleName().toLowerCase() + ".json";
    }
}
