package databases.impl.json;

import common.constants.DatabaseConstants;
import databases.JsonDatabase;

public class MenuItemJsonDatabase extends JsonDatabase {
    @Override
    protected String getFileName() {
        return DatabaseConstants.MENU_ITEM_JSON;
    }
}
