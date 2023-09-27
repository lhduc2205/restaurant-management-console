package com.lhduc.view;


import com.lhduc.common.constant.MessageConstant;
import com.lhduc.common.pattern.servicelocator.ServiceLocator;
import com.lhduc.controller.MenuItemController;
import com.lhduc.common.enums.CrudOption;
import com.lhduc.exception.ApplicationRuntimeException;
import com.lhduc.exception.NotFoundException;
import com.lhduc.model.dto.MenuItemDto;
import com.lhduc.util.MenuDisplayUtil;
import com.lhduc.util.UserInputUtil;

public class MenuItemConsoleView extends ConsoleViewTemplate {
    private final MenuItemController menuItemController;

    public MenuItemConsoleView() {
        this.menuItemController = ServiceLocator.getService(MenuItemController.class.getName());
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
}