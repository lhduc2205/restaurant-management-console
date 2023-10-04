package com.lhduc.view;

import com.lhduc.common.constant.MessageConstant;
import com.lhduc.common.filtered.ConditionCreator;
import com.lhduc.common.filtered.FilterCondition;
import com.lhduc.controller.MenuController;
import com.lhduc.controller.MenuItemController;
import com.lhduc.common.enums.CrudOption;
import com.lhduc.common.enums.MenuCategory;
import com.lhduc.common.enums.SearchMenuOption;
import com.lhduc.exception.NotFoundException;
import com.lhduc.util.MenuDisplayUtil;
import com.lhduc.util.UserInputUtil;
import com.lhduc.model.dto.MenuDto;
import com.lhduc.model.dto.MenuItemDto;

import java.util.List;

public class MenuConsoleView extends ConsoleViewTemplate {
    private final MenuController menuController;
    private final MenuItemController menuItemController;
    private final ConditionCreator<MenuDto> conditionCreator;

    public MenuConsoleView() {
        this.menuController = new MenuController();
        this.menuItemController = new MenuItemController();
        this.conditionCreator = new ConditionCreator<>(MenuDto.class);
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
                this.deleteMenu();
                break;
            }
            case FILTER: {
                this.filterMenu();
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
        menuController.create(new MenuDto(menuCategory));
        System.out.println(MessageConstant.CREATED_SUCCESSFULLY);
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

        try {
            MenuDto updatedMenu = new MenuDto(menuIdFromUserInput, chooseMenuCategory());
            menuController.update(updatedMenu);

            boolean isAgreeMenuItemCreated = UserInputUtil.getUserChoiceForYesNoOption("Do you want to update menu item of Menu "+ updatedMenu.getId() +"? (Y/N): ");

            if (isAgreeMenuItemCreated) {
                this.updateMenuItem(updatedMenu.getId());
            }

            System.out.println(MessageConstant.UPDATED_SUCCESSFULLY);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateMenuItem(int menuId) {
        int menuItemId = UserInputUtil.enterInteger("Enter menu item id");

        MenuItemDto menuItemFromPrompt = UserInputUtil.getMenuItemFromPrompt(menuId);
        menuItemFromPrompt.setId(menuItemId);

        menuItemController.update(menuItemFromPrompt);
    }

    private void deleteMenu() {
        try {
            menuController.deleteById(getMenuIdFromUserInput());
            System.out.println(MessageConstant.DELETED_SUCCESSFULLY);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void filterMenu() {
        FilterCondition filterCondition = conditionCreator.createConditions();
        List<MenuDto> menus = menuController.getAll(filterCondition);

        MenuDisplayUtil.displayMenu(menus);
    }
}
