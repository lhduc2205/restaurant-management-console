package com.lhduc.view;


import com.lhduc.common.constant.MessageConstant;
import com.lhduc.common.filtered.ConditionCreator;
import com.lhduc.common.filtered.FilterCondition;
import com.lhduc.controller.MenuItemController;
import com.lhduc.common.enums.CrudOption;
import com.lhduc.exception.ApplicationRuntimeException;
import com.lhduc.exception.NotFoundException;
import com.lhduc.model.dto.MenuItemDto;
import com.lhduc.util.MenuDisplayUtil;
import com.lhduc.util.UserInputUtil;

import java.util.List;

public class MenuItemConsoleView extends ConsoleViewTemplate {
    private final MenuItemController menuItemController;
    private final ConditionCreator<MenuItemDto> conditionCreator;

    public MenuItemConsoleView() {
        this.menuItemController = new MenuItemController();
        this.conditionCreator = new ConditionCreator<>(MenuItemDto.class);
    }


    @Override
    protected String getOptionTitle() {
        return "Menu Item";
    }

    @Override
    protected void doAction(CrudOption option) {
        option.displayTitle(getOptionTitle());
        try {
            switch (option) {
                case SHOW: {
                    this.showMenuItem();
                    break;
                }
                case CREATE: {
                    this.createMenuItem();
                    break;
                }
                case UPDATE: {
                    this.updateMenuItem();
                    break;
                }
                case DELETE: {
                    this.deleteMenuItem();
                    break;
                }
                case FILTER: {
                    this.filterMenuItem();
                    break;
                }
            }
        } catch (ApplicationRuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showMenuItem() {
        MenuDisplayUtil.displayMenuItem(menuItemController.getAll());
    }

    private void createMenuItem() {
        MenuItemDto menuItemDto = UserInputUtil.getMenuItemFromPrompt();
        menuItemController.create(menuItemDto);
        System.out.println(MessageConstant.CREATED_SUCCESSFULLY);
    }

    private void updateMenuItem() {
        int menuItemId = UserInputUtil.enterInteger("Enter menu item id");
        try {
            MenuItemDto menuItem = menuItemController.getById(menuItemId);
            MenuItemDto newMenuItem = UserInputUtil.getMenuItemFromPrompt(menuItem.getMenuId());
            newMenuItem.setId(menuItem.getId());
            menuItemController.update(newMenuItem);

            System.out.println(MessageConstant.UPDATED_SUCCESSFULLY);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteMenuItem() {
        int menuItemId = UserInputUtil.enterInteger("Enter menu item id");
        try {
            menuItemController.deleteById(menuItemId);
            System.out.println(MessageConstant.DELETED_SUCCESSFULLY);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void filterMenuItem() {
        final FilterCondition conditions = conditionCreator.createConditions();
        List<MenuItemDto> items = menuItemController.getAll(conditions);

        MenuDisplayUtil.displayMenuItem(items);
    }
}
