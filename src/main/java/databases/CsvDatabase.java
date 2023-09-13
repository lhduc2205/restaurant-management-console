package databases;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import common.constants.AppPath;
import utils.FileUtil;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class CsvDatabase implements Database {
    @Override
    public <T> List<T> readData(Class<T> valueType) {
        try {
            File file = FileUtil.createDirectoryAndFile(this.getFileName(valueType));

            List<T> beans = new CsvToBeanBuilder(new FileReader(file))
                    .withType(valueType).build().parse();

            return beans;
        } catch (IOException e) {
            return new ArrayList<>();
        }

    }

    @Override
    public <T> void saveAll(List<T> items, Class<T> classType) {
        try {
            Writer writer = new FileWriter(getFileName(classType));
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
            beanToCsv.write(items);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getFileName(Class<?> valueType) {
        return AppPath.CSV_DATABASE + "/" + valueType.getSimpleName().toLowerCase() + ".csv";
    }
}
