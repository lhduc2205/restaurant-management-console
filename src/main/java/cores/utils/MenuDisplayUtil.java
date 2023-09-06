package cores.utils;

import cores.enums.CrudMenuOption;
import cores.enums.MenuCategory;
import cores.enums.Origin;
import cores.enums.SearchMenuOption;

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
