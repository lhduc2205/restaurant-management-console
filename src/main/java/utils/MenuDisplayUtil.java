package utils;

import common.enums.CrudMenuOption;
import common.enums.MenuCategory;
import common.enums.Origin;
import common.enums.SearchMenuOption;
import models.dtos.MenuDto;
import models.dtos.MenuItemDto;

import java.util.List;

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

    public static void displayMenu(MenuDto menuDto) {
        System.out.println("\n--- MENU " + (menuDto.getId() + 1) + " (" + menuDto.getCategory() + ") ----");
        for (int i = 0; i < menuDto.getItems().size(); i++) {
            MenuItemDto menuItemDto = menuDto.getItems().get(i);
            System.out.println("+\t" + (i + 1) + ". " + menuItemDto.getName() + " (id: " + menuItemDto.getId() + ")");
            System.out.println("+\tPrice: "+ menuItemDto.getPrice());
            System.out.println("+\tOrigin: "+ menuItemDto.getOrigin());
            System.out.println("+\tDescription: "+ menuItemDto.getDescription());
            if (i < menuDto.getItems().size() - 1) {
                System.out.println("+\t-------------");
            }
        }
    }

    public static void displayMenu(List<MenuDto> menus) {
        for (MenuDto menu : menus) {
            displayMenu(menu);
            System.out.println("----------------------");
        }
    }
}
