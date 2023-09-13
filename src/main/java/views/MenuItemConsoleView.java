package views;


import common.patterns.servicelocator.ServiceLocator;
import controllers.MenuItemController;
import common.enums.CrudOption;
import exceptions.ApplicationRuntimeException;
import exceptions.NotFoundException;
import models.dtos.MenuItemDto;
import utils.MenuDisplayUtil;
import utils.UserInputUtil;

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
