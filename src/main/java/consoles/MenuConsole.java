package consoles;

import controllers.MenuController;
import cores.enums.CrudMenuOption;
import cores.enums.MenuCategory;
import cores.enums.SearchMenuOption;
import cores.utils.MenuDisplayUtil;
import cores.utils.PrettierPrinter;
import cores.utils.UserInputUtil;
import models.Menu;

import java.util.Scanner;

public class MenuConsole extends ConsoleManager {
    private final String MENU_TITLE = "menu";

    private final MenuController menuController;

    private final MenuItemConsole menuItemConsole;

    public MenuConsole(MenuController menuController, MenuItemConsole menuItemConsole) {
        this.menuController = menuController;
        this.menuItemConsole = menuItemConsole;
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
                    Menu menu = new Menu(getMenuIdFromUserInput(), chooseMenuCategory());
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
        Menu menu = new Menu(menuCategory);
        menuController.create(menu);
        menuItemConsole.askForCreation(menu.getId());
    }

    private int getMenuIdFromUserInput() {
        return UserInputUtil.enterInteger("Enter menu id: ");
    }

    private MenuCategory chooseMenuCategory() {
        System.out.println("Choose type: ");
        MenuDisplayUtil.displayCategories();
        int option = UserInputUtil.enterInteger("Your option is: ", MenuCategory.values().length);
        return MenuCategory.values()[option - 1];
    }
}
