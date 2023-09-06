package databases;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import models.Menu;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvDatabase implements Database {
    private final String fileName;

    public CsvDatabase(String fileName) {
        this.fileName = fileName;
    }


    @Override
    public <T> List<T> readData(Class<T> valueType) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            List<T> beans = new CsvToBeanBuilder(new FileReader(fileName))
                    .withType(valueType).build().parse();

            return beans;
        } catch (IOException e) {
            return new ArrayList<>();
        }

    }

    @Override
    public <T> void saveAll(List<T> items) {
        try {
            Writer writer = new FileWriter(fileName);
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
            beanToCsv.write(items);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
