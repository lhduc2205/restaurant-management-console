package com.lhduc.databases;

import com.lhduc.configs.ApplicationConfig;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.lhduc.common.constants.FolderConstant;
import com.lhduc.utils.FileUtil;

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
            File file = FileUtil.createDirectoryAndFileIfNotExist(this.getFileName(valueType));

            return new CsvToBeanBuilder<T>(new FileReader(file)).withType(valueType).build().parse();
        } catch (IOException e) {
            return new ArrayList<>();
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
            e.printStackTrace();
        }
    }

    private String getFileName(Class<?> valueType) {
        if (ApplicationConfig.isRunningFromJAR()) {
            return FolderConstant.CSV_DATABASE_PATH + valueType.getSimpleName().toLowerCase() + ".csv";
        }
        return FolderConstant.CSV_DATABASE_PATH + "/" + valueType.getSimpleName().toLowerCase() + ".csv";
    }
}
