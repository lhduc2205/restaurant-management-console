package views;

import controllers.MenuController;
import controllers.MenuItemController;
import cores.enums.CrudMenuOption;
import cores.enums.MenuCategory;
import cores.enums.Origin;
import cores.enums.SearchMenuOption;
import cores.utils.MenuDisplayUtil;
import cores.utils.PrettierPrinter;
import cores.utils.UserInputUtil;
import dtos.MenuDto;
import dtos.MenuItemDto;

import java.util.Scanner;

public class MenuConsoleView extends ConsoleViewManager {
    private final String MENU_TITLE = "menu";

    private final MenuController menuController;
    private final MenuItemController menuItemController;

    public MenuConsoleView(MenuController menuController, MenuItemController menuItemController) {
        this.menuController = menuController;
        this.menuItemController = menuItemController;
    }

    @Override
    protected String getOptionTitle() {
        return MENU_TITLE;
    }

    @Override
    protected void doAction(CrudMenuOption option) {
        MenuDisplayUtil.displayTitle(option);
        try {
            switch (option) {
                case SHOW -> {
                    this.showMenu();
//                    this.displaySearchOption();
                }
                case CREATE -> this.createMenu();
                case UPDATE -> {
                    MenuDto menu = new MenuDto(getMenuIdFromUserInput(), chooseMenuCategory());
                    menuController.update(menu);
                }
                case DELETE -> menuController.deleteById(getMenuIdFromUserInput());
            }
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showMenu() {
        PrettierPrinter.print(menuController.getAll());
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
        this.askForMenuItemCreation(menuDtoResult.getId());
    }

    private void askForMenuItemCreation(int menuId) {
        System.out.print("Do you want to create menu item? (Y/N): ");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();

        if (option.equalsIgnoreCase("y")) {
            createMenuItem(menuId);
        }
    }

    private void createMenuItem(int menuId) {
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

        MenuItemDto menuItemDto = new MenuItemDto(name, desc, price, origin, menuId);
        System.out.println("--> Menu item: ");
        PrettierPrinter.print(menuItemDto);

        menuItemController.create(menuItemDto);
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
