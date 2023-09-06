package controllers;

import cores.constants.AppPath;
import consoles.MenuItemConsole;
import consoles.MenuConsole;
import consoles.ConsoleManager;
import databases.CsvDatabase;
import databases.Database;
import databases.JsonDatabase;
import repositories.MenuItemRepository;
import repositories.MenuRepository;
import repositories.impl.MenuItemRepositoryImpl;
import repositories.impl.MenuRepositoryImpl;
import services.MenuItemService;
import services.MenuService;
import services.impl.MenuItemServiceImpl;
import services.impl.MenuServiceImpl;

public class ApplicationController {
    private final MenuController menuController;
    private final MenuItemController menuItemController;

    public ApplicationController() {
//        Database menuDatabase = new JsonDatabase(AppPath.JSON_DATABASE + "/menu.json");
//        Database menuItemDatabase = new JsonDatabase(AppPath.JSON_DATABASE + "/menu_item.json");
        Database menuDatabase = new CsvDatabase(AppPath.CSV_DATABASE + "/menu.csv");
        Database menuItemDatabase = new CsvDatabase(AppPath.CSV_DATABASE + "/menu_item.csv");

        MenuItemRepository menuItemRepository = new MenuItemRepositoryImpl(menuItemDatabase);
        MenuItemService menuItemService = new MenuItemServiceImpl(menuItemRepository);

        MenuRepository menuRepository = new MenuRepositoryImpl(menuDatabase);
        MenuService menuService = new MenuServiceImpl(menuRepository, menuItemRepository);

        this.menuController = new MenuController(menuService);
        this.menuItemController = new MenuItemController(menuItemService);
    }

    public void start() {
        this.displayApplicationTitle();

        MenuItemConsole menuItemConsole = new MenuItemConsole(menuItemController);
        ConsoleManager menuConsoleManager = new MenuConsole(menuController, menuItemConsole);

        menuConsoleManager.chooseOption();
    }

    private void displayApplicationTitle() {
        System.out.println("\n--- RESTAURANT APPLICATION ---");
    }
}
