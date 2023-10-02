package com.lhduc.util;

import com.lhduc.config.ApplicationConfig;
import com.lhduc.datasource.CsvDatasource;
import com.lhduc.datasource.Datasource;
import com.lhduc.datasource.JsonDatasource;

import static com.lhduc.common.constant.AppConstant.DATABASE_PROPERTY;
import static com.lhduc.common.constant.AppConstant.JSON;

public class DatasourceUtil {
    public static Datasource getDatasourceInstance() {
        String databaseProperty = ApplicationConfig.getProperty(DATABASE_PROPERTY);
        switch (databaseProperty) {
            case JSON: {
                return new JsonDatasource();
            }
            default: {
                return new CsvDatasource();
            }
        }
    }
}
