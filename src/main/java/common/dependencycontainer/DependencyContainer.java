package common.dependencycontainer;

import controllers.MenuController;
import controllers.MenuItemController;
import models.mappers.ModelMapper;
import databases.Database;
import databases.impl.csv.MenuCsvDatabase;
import databases.impl.csv.MenuItemCsvDatabase;
import repositories.MenuItemRepository;
import repositories.MenuRepository;
import repositories.impl.MenuItemRepositoryImpl;
import repositories.impl.MenuRepositoryImpl;
import services.MenuItemService;
import services.MenuService;
import services.impl.MenuItemServiceImpl;
import services.impl.MenuServiceImpl;
import views.MenuConsoleView;
import views.MenuItemConsoleView;

public class DependencyContainer {
    // Databases
//    public static Database menuDatabase = new CsvDatabase(AppPath.CSV_DATABASE + "/menu.csv");
//    public static Database menuItemDatabase = new CsvDatabase(AppPath.CSV_DATABASE + "/menu_item.csv");
//    public static Database menuDatabase = new MenuJsonDatabase();
//    public static Database menuItemDatabase = new MenuItemJsonDatabase();
    public static Database menuDatabase = new MenuCsvDatabase();
    public static Database menuItemDatabase = new MenuItemCsvDatabase();

    // Mappers
    public static ModelMapper modelMapper = new ModelMapper();

    // Repositories
    public static MenuItemRepository menuItemRepository = new MenuItemRepositoryImpl(menuItemDatabase);
    public static MenuRepository menuRepository = new MenuRepositoryImpl(menuDatabase);

    // Services
    public static MenuService menuService = new MenuServiceImpl(menuRepository, menuItemRepository, modelMapper);
    public static MenuItemService menuItemService = new MenuItemServiceImpl(menuItemRepository, modelMapper);

    // Controllers
    public static MenuController menuController = new MenuController(menuService);
    public static MenuItemController menuItemController = new MenuItemController(menuItemService);

    // Views

    public static MenuItemConsoleView menuItemConsoleView = new MenuItemConsoleView(menuItemController);
    public static MenuConsoleView menuConsoleView = new MenuConsoleView(menuController, menuItemController);
}
