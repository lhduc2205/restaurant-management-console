package databases.impl;

import cores.constants.DatabaseConstants;
import databases.JsonDatabase;

public class MenuJsonDatabase extends JsonDatabase {
    public MenuJsonDatabase() {}

    @Override
    protected String getFileName() {
        return DatabaseConstants.MENU_JSON;
    }
}
