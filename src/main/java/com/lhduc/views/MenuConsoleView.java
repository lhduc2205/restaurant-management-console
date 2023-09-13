package com.lhduc.views;

import com.lhduc.common.patterns.servicelocator.ServiceLocator;
import com.lhduc.controllers.MenuController;
import com.lhduc.controllers.MenuItemController;
import com.lhduc.common.enums.CrudOption;
import com.lhduc.common.enums.MenuCategory;
import com.lhduc.common.enums.SearchMenuOption;
import com.lhduc.utils.MenuDisplayUtil;
import com.lhduc.utils.UserInputUtil;
import com.lhduc.models.dtos.MenuDto;
import com.lhduc.models.dtos.MenuItemDto;

import java.util.Scanner;

public class MenuConsoleView extends ConsoleViewTemplate {
    private final MenuController menuController;
    private final MenuItemController menuItemController;

    public MenuConsoleView() {
        this.menuController = ServiceLocator.getService(MenuController.class.getName());
        this.menuItemController = ServiceLocator.getService(MenuItemController.class.getName());
    }

    @Override
    protected String getOptionTitle() {
        return "Menu";
    }

    @Override
    protected void doAction(CrudOption option) {
        option.displayTitle(getOptionTitle());
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
                this.updateMenu();
                break;
            }
            case DELETE: {
                menuController.deleteById(getMenuIdFromUserInput());
                break;
            }
        }
    }

    private void showMenu() {
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
        MenuItemDto menuItemDto = UserInputUtil.getMenuItemFromPrompt(menuDto.getId());
        menuItemDto = menuItemController.create(menuItemDto);

        System.out.println("\n--> Result: ");
        MenuDisplayUtil.displayMenuItem(null, menuItemDto);
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

    private void updateMenu() {
        int menuIdFromUserInput = getMenuIdFromUserInput();
        MenuDto existedMenu = menuController.getById(menuIdFromUserInput);

        if (existedMenu == null) {
            return;
        }

        MenuDto updatedMenu = new MenuDto(menuIdFromUserInput, chooseMenuCategory());
        menuController.update(updatedMenu);
        boolean isAgreeMenuItemCreated = UserInputUtil.getUserChoiceForYesNoOption("Do you want to update menu item of Menu "+ updatedMenu.getId() +"? (Y/N): ");

        if (isAgreeMenuItemCreated) {
            this.updateMenuItem(updatedMenu.getId());
        }
    }

    private void updateMenuItem(int menuId) {
        int menuItemId = UserInputUtil.enterInteger("Enter id");

        MenuItemDto menuItemDto = menuItemController.getById(menuItemId);

        if (menuItemDto == null) {
            return;
        }

        MenuItemDto menuItemFromPrompt = UserInputUtil.getMenuItemFromPrompt(menuId);
        menuItemFromPrompt.setId(menuItemId);

        menuItemFromPrompt = menuItemController.update(menuItemFromPrompt);

        System.out.println("\n--> Result: ");
        MenuDisplayUtil.displayMenuItem(null, menuItemFromPrompt);
    }
}
