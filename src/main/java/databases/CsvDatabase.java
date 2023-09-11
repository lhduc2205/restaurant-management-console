package databases;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public abstract class CsvDatabase implements Database {
    protected abstract String getFileName();

    @Override
    public <T> List<T> readData(Class<T> valueType) {
        try {
            File file = new File(getFileName());
            if (!file.exists()) {
                file.createNewFile();
            }

            List<T> beans = new CsvToBeanBuilder(new FileReader(getFileName()))
                    .withType(valueType).build().parse();

            return beans;
        } catch (IOException e) {
            return new ArrayList<>();
        }

    }

    @Override
    public <T> void saveAll(List<T> items) {
        try {
            Writer writer = new FileWriter(getFileName());
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
            beanToCsv.write(items);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
