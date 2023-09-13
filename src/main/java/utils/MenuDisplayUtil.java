package utils;

import common.enums.CrudOption;
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

    public static void displaySearchOptions() {
        for (int i = 0; i < SearchMenuOption.values().length; i++) {
            System.out.println((i + 1) + ". " + SearchMenuOption.values()[i].getDescription());
        }
    }

    public static void displayMenu(List<MenuDto> menus) {
        if (menus.isEmpty()) {
            System.out.println("- There's no menu here, let create them! -");
            return;
        }
        for (MenuDto menu : menus) {
            displayMenu(menu);
            System.out.println("-------------------------------------");
        }
    }

    public static void displayMenu(MenuDto menuDto) {
        System.out.println("\nMENU " + (menuDto.getId())  + "-------------------");
        System.out.println("+ Category: " + menuDto.getCategory());
        System.out.println("+");
        displayMenuItem(menuDto.getItems());
    }

    public static void displayMenuItem(List<MenuItemDto> menuItemsDto) {
        if (menuItemsDto.isEmpty()) {
            System.out.println("+\tMenu items are empty.");
            return;
        }

        for (int i = 0; i < menuItemsDto.size(); i++) {
            displayMenuItem(i + 1, menuItemsDto.get(i));
            if (i < menuItemsDto.size() - 1) {
                System.out.println("+\t-------------");
            }
        }
    }

    public static void displayMenuItem(Integer index, MenuItemDto menuItemDto) {
        if (index == null) {
            System.out.println("+\tName: " + menuItemDto.getName());
        } else {
            System.out.println("+\t" + index + ". " + menuItemDto.getName());
        }

        if (menuItemDto.getId() > 0) {
            System.out.println("+\tItem id: "+ menuItemDto.getId());
        }

        System.out.println("+\tPrice: "+ menuItemDto.getPrice());
        System.out.println("+\tOrigin: "+ menuItemDto.getOrigin());
        System.out.println("+\tDescription: "+ menuItemDto.getDescription());
        System.out.println("+\tMenu id: "+ menuItemDto.getMenuId());
    }
}
