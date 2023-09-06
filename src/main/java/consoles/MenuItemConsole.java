package consoles;


import controllers.MenuItemController;
import cores.enums.Origin;
import cores.utils.MenuDisplayUtil;
import cores.utils.PrettierPrinter;
import cores.utils.UserInputUtil;
import models.MenuItem;

import java.util.Scanner;

public class MenuItemConsole {
    private final MenuItemController controller;

    public MenuItemConsole(MenuItemController controller) {
        this.controller = controller;
    }

    public void askForCreation(int menuId) {
        System.out.print("Do you want to create menu item? (Y/N): ");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();

        if (option.equalsIgnoreCase("y")) {
            createMenuItem(menuId);
        }
    }

    public void createMenuItem(int menuId) {
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

        MenuItem menuItem = new MenuItem(name, desc, price, origin, menuId);
        System.out.println("--> Menu item: ");
        PrettierPrinter.print(menuItem);

        controller.create(menuItem);
    }

}
