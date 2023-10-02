package com.lhduc.datasource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lhduc.common.constant.FolderConstant;
import com.lhduc.exception.ApplicationRuntimeException;
import com.lhduc.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonDatasource implements Datasource {
    private final ObjectMapper objectMapper;

    public JsonDatasource() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public <T> List<T> readData(Class<T> valueType) {
        try {
            String fileName = this.getFileName(valueType);
            File file = FileUtil.ensureFileAndDirectoryExistence(fileName);

            if (file.length() == 0) {
                return new ArrayList<>();
            }

            objectMapper.findAndRegisterModules();
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
            throw new ApplicationRuntimeException(e.getMessage(), e.getCause());
        }
    }

    private String getFileName(Class<?> valueType) {
        return FolderConstant.JSON_DATABASE_PATH + "/" + valueType.getSimpleName().toLowerCase() + ".json";
    }
}
