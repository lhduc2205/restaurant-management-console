package com.lhduc.view;

import com.lhduc.common.constant.MessageConstant;
import com.lhduc.common.filtered.ConditionCreator;
import com.lhduc.common.filtered.FilterCondition;
import com.lhduc.controller.MenuController;
import com.lhduc.common.enums.CrudOption;
import com.lhduc.common.enums.MenuCategory;
import com.lhduc.common.enums.SearchMenuOption;
import com.lhduc.exception.NotFoundException;
import com.lhduc.util.MenuDisplayUtil;
import com.lhduc.util.UserInputUtil;
import com.lhduc.model.dto.MenuDto;

import java.util.List;

public class MenuConsoleView extends ConsoleViewTemplate {
    private final MenuController menuController;
    private final ConditionCreator<MenuDto> conditionCreator;

    public MenuConsoleView() {
        this.menuController = new MenuController();
        this.conditionCreator = new ConditionCreator<>(MenuDto.class);
    }

    @Override
    protected String getOptionTitle() {
        return "Menu";
    }

    @Override
    protected void doAction(CrudOption option) {
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
        return UserInputUtil.enterInteger(MessageConstant.ENTER_MENU_ID);
    }

    private MenuCategory chooseMenuCategory() {
        System.out.println("Choose type: ");
        MenuDisplayUtil.displayCategories();
        int option = UserInputUtil.enterInteger(MessageConstant.YOUR_OPTION_IS, MenuCategory.values().length);
        return MenuCategory.values()[option - 1];
    }

    private void updateMenu() {
        this.showMenu();
        try {
            MenuDto updatedMenu = new MenuDto(getMenuIdFromUserInput(), chooseMenuCategory());
            menuController.update(updatedMenu);

            System.out.println(MessageConstant.UPDATED_SUCCESSFULLY);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteMenu() {
        this.showMenu();
        try {
            menuController.deleteById(getMenuIdFromUserInput());
            System.out.println(MessageConstant.DELETED_SUCCESSFULLY);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void filterMenu() {
        this.showMenu();
        FilterCondition filterCondition = conditionCreator.createConditions();
        List<MenuDto> menus = menuController.getAll(filterCondition);

        MenuDisplayUtil.displayMenu(menus);
    }
}
