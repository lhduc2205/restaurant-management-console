package views;

import common.patterns.servicelocator.ServiceLocator;
import controllers.MenuController;
import controllers.MenuItemController;
import common.enums.CrudMenuOption;
import common.enums.MenuCategory;
import common.enums.Origin;
import common.enums.SearchMenuOption;
import utils.MenuDisplayUtil;
import utils.UserInputUtil;
import models.dtos.MenuDto;
import models.dtos.MenuItemDto;

import java.util.Scanner;

public class MenuConsoleView extends ConsoleViewManager {
    private final String MENU_TITLE = "menu";

    private final MenuController menuController;
    private final MenuItemController menuItemController;

    public MenuConsoleView() {
        this.menuController = ServiceLocator.getService(MenuController.class.getName());
        this.menuItemController = ServiceLocator.getService(MenuItemController.class.getName());
    }

    @Override
    protected String getOptionTitle() {
        return MENU_TITLE;
    }

    @Override
    protected void doAction(CrudMenuOption option) {
        MenuDisplayUtil.displayTitle(option);
        switch (option) {
            case SHOW: {
                this.showMenu();
                break;
            }
            case CREATE: {
                this.createMenu();
                break;
            }
            case UPDATE: {
                MenuDto menu = new MenuDto(getMenuIdFromUserInput(), chooseMenuCategory());
                menuController.update(menu);
                break;
            }
            case DELETE: {
                menuController.deleteById(getMenuIdFromUserInput());
                break;
            }
        }
    }

    private void showMenu() {
//        PrettierPrinter.print(menuController.getAll());
        MenuDisplayUtil.displayMenu(menuController.getAll());
    }

    private void displaySearchOption() {
        System.out.println("\n--- Search Menu ---");
        MenuDisplayUtil.displaySearchOptions();
        int option = UserInputUtil.enterInteger("Enter option for searching", SearchMenuOption.values().length);
        System.out.println(option);
    }

    private void createMenu() {
        MenuCategory menuCategory = this.chooseMenuCategory();
        MenuDto menuDtoResult = menuController.create(new MenuDto(menuCategory));
        this.askForMenuItemCreation(menuDtoResult);
    }

    private void askForMenuItemCreation(MenuDto menuDto) {
        System.out.print("Do you want to create menu item? (Y/N): ");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();

        if (option.equalsIgnoreCase("y")) {
            createMenuItem(menuDto);
        }
    }

    private void createMenuItem(MenuDto menuDto) {
        System.out.println("\n(Create menu item): ");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter description: ");
        String desc = scanner.nextLine();

        double price = UserInputUtil.enterDouble("Enter price");

        System.out.println("Choose origin: ");
        MenuDisplayUtil.displayOrigin();
        System.out.print("Your option is: ");
        Origin origin = Origin.values()[scanner.nextInt() - 1];

        MenuItemDto menuItemDto = new MenuItemDto(name, desc, price, origin, menuDto.getId());
        System.out.println("--> Menu item: ");
        menuDto.addItem(menuItemDto);

        menuItemController.create(menuItemDto);
        MenuDisplayUtil.displayMenu(menuDto);
    }

    private int getMenuIdFromUserInput() {
        return UserInputUtil.enterInteger("Enter menu id");
    }

    private MenuCategory chooseMenuCategory() {
        System.out.println("Choose type: ");
        MenuDisplayUtil.displayCategories();
        int option = UserInputUtil.enterInteger("Your option is", MenuCategory.values().length);
        return MenuCategory.values()[option - 1];
    }
}
