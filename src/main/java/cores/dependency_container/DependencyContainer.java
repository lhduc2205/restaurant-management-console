package cores.dependency_container;

import controllers.MenuController;
import controllers.MenuItemController;
import cores.constants.AppPath;
import cores.utils.MapperUtil;
import databases.CsvDatabase;
import databases.Database;
import databases.JsonDatabase;
import databases.impl.MenuItemJsonDatabase;
import databases.impl.MenuJsonDatabase;
import org.modelmapper.ModelMapper;
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
    public static Database menuDatabase = new MenuJsonDatabase();
    public static Database menuItemDatabase = new MenuItemJsonDatabase();

    // Mappers
    public static ModelMapper modelMapper = new ModelMapper();
    public static MapperUtil mapperUtil = new MapperUtil(modelMapper);

    // Repositories
    public static MenuItemRepository menuItemRepository = new MenuItemRepositoryImpl(menuItemDatabase);
    public static MenuRepository menuRepository = new MenuRepositoryImpl(menuDatabase);

    // Services
    public static MenuService menuService = new MenuServiceImpl(menuRepository, menuItemRepository, mapperUtil);
    public static MenuItemService menuItemService = new MenuItemServiceImpl(menuItemRepository, mapperUtil);

    // Controllers
    public static MenuController menuController = new MenuController(menuService);
    public static MenuItemController menuItemController = new MenuItemController(menuItemService);

    // Views

    public static MenuItemConsoleView menuItemConsoleView = new MenuItemConsoleView(menuItemController);
    public static MenuConsoleView menuConsoleView = new MenuConsoleView(menuController, menuItemController);
}
