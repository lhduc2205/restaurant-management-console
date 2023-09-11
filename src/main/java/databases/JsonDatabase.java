package databases;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.patterns.servicelocator.ServiceLocator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class JsonDatabase implements Database {
    private final ObjectMapper objectMapper;

    protected abstract String getFileName();

    public JsonDatabase() {
        this.objectMapper = ServiceLocator.getService(ObjectMapper.class.getName());
    }

    @Override
    public <T> List<T> readData(Class<T> valueType) {
        try {
            return objectMapper.readValue(new File(getFileName()), objectMapper.getTypeFactory().constructCollectionType(List.class, valueType));
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
    public <T> void saveAll(List<T> data) {
        try {
            objectMapper.writeValue(new File(getFileName()), data);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
