package databases.impl.csv;

import common.constants.DatabaseConstants;
import databases.CsvDatabase;

public class MenuCsvDatabase extends CsvDatabase {
    @Override
    protected String getFileName() {
        return DatabaseConstants.MENU_CSV;
    }
}
