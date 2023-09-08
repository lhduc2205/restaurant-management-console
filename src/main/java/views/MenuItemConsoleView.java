package views;


import controllers.MenuItemController;
import cores.enums.CrudMenuOption;
import cores.enums.Origin;
import cores.utils.MenuDisplayUtil;
import cores.utils.PrettierPrinter;
import cores.utils.UserInputUtil;
import dtos.MenuItemDto;

import java.util.Scanner;

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
