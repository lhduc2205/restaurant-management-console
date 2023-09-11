package utils;

import common.enums.CrudMenuOption;
import common.enums.MenuCategory;
import common.enums.Origin;
import common.enums.SearchMenuOption;

public class MenuDisplayUtil {
    public static void displayCategories() {
        for (int i = 0; i < MenuCategory.values().length; i++) {
            System.out.println((i + 1) + ". " + MenuCategory.values()[i]);
        }
    }

    public static void displayOrigin() {
        for (int i = 0; i < Origin.values().length; i++) {
            System.out.println((i + 1) + ". " + Origin.values()[i]);
        }
    }

    public static void displayTitle(CrudMenuOption option) {
        System.out.println("\n(" + option.getDescription() + "): ");
    }

    public static void displaySearchOptions() {
        for (int i = 0; i < SearchMenuOption.values().length; i++) {
            System.out.println((i + 1) + ". " + SearchMenuOption.values()[i].getDescription());
        }
    }
}
