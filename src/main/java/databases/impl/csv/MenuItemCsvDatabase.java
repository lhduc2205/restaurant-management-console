package databases.impl.csv;

import common.constants.DatabaseConstants;
import databases.CsvDatabase;

public class MenuItemCsvDatabase extends CsvDatabase {
    @Override
    protected String getFileName() {
        return DatabaseConstants.MENU_ITEM_CSV;
    }
}
