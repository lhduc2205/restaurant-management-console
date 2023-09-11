package views;


import controllers.MenuItemController;
import common.enums.CrudMenuOption;

public class MenuItemConsoleView extends ConsoleViewManager {
    private final MenuItemController controller;

    public MenuItemConsoleView(MenuItemController controller) {
        this.controller = controller;
    }


    @Override
    protected String getOptionTitle() {
        return null;
    }

    @Override
    protected void doAction(CrudMenuOption option) {

    }
}
