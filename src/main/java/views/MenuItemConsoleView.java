package views;


import common.patterns.servicelocator.ServiceLocator;
import controllers.MenuItemController;
import common.enums.CrudMenuOption;

public class MenuItemConsoleView extends ConsoleViewManager {
    private final MenuItemController controller;

    public MenuItemConsoleView() {
        this.controller = ServiceLocator.getService(MenuItemController.class.getName());
    }


    @Override
    protected String getOptionTitle() {
        return null;
    }

    @Override
    protected void doAction(CrudMenuOption option) {

    }
}
