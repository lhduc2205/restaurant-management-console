package com.lhduc.datasource;

import com.lhduc.exception.ApplicationRuntimeException;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.lhduc.common.constant.FolderConstant;
import com.lhduc.util.FileUtil;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class CsvDatasource implements Datasource {
    @Override
    public <T> List<T> readData(Class<T> valueType) {
        File file = FileUtil.ensureFileAndDirectoryExistence(this.getFileName(valueType));

        if (file.length() == 0) {
            return new ArrayList<>();
        }

        try (FileReader fileReader = new FileReader(file)) {
            return new CsvToBeanBuilder<T>(fileReader).withType(valueType).build().parse();
        } catch (IOException e) {
            throw new ApplicationRuntimeException("Can not read file: " + this.getFileName(valueType));
        }
    }

    @Override
    public <T> void saveAll(List<T> items, Class<T> classType) {
        try {
            Writer writer = new FileWriter(getFileName(classType));
            StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(writer).build();
            beanToCsv.write(items);
            writer.close();
        } catch (Exception e) {
            throw new ApplicationRuntimeException(e.getMessage());
        }
    }

    private String getFileName(Class<?> valueType) {
        return FolderConstant.CSV_DATABASE_FOLDER_PATH + valueType.getSimpleName().toLowerCase() + ".csv";
    }
}
