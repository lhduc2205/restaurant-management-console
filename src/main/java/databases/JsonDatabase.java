package databases;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonDatabase implements Database {
    private final String fileName;
    private final ObjectMapper objectMapper;

    public JsonDatabase(String fileName) {
        this.fileName = fileName;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public <T> List<T> readData(Class<T> valueType) {
        try {
            return objectMapper.readValue(new File(fileName), objectMapper.getTypeFactory().constructCollectionType(List.class, valueType));
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
            objectMapper.writeValue(new File(fileName), data);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
