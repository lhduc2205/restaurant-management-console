package com.lhduc.views;


import com.lhduc.common.patterns.servicelocator.ServiceLocator;
import com.lhduc.controllers.MenuItemController;
import com.lhduc.common.enums.CrudOption;
import com.lhduc.exceptions.ApplicationRuntimeException;
import com.lhduc.models.dtos.MenuItemDto;
import com.lhduc.utils.MenuDisplayUtil;
import com.lhduc.utils.UserInputUtil;

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
    }

    private void updateMenuItem() {
    }

    private void deleteMenuItem() {
        int menuItemId = UserInputUtil.enterInteger("Enter menu item id");
        menuItemController.deleteById(menuItemId);
    }
}
