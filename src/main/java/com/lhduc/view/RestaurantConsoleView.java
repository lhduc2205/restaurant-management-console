package com.lhduc.view;

import com.lhduc.common.enums.RestaurantManagementOption;
import com.lhduc.common.pattern.servicelocator.ServiceLocator;
import com.lhduc.exception.ForceExitApplicationException;
import com.lhduc.util.PrettierPrinter;
import com.lhduc.util.UserInputUtil;

public class RestaurantConsoleView {
    public void show() {
        while (true) {
            try {
                this.displayOptions();

                int userChoice = UserInputUtil.enterInteger("Your choice", RestaurantManagementOption.values().length);
                this.mapOption(RestaurantManagementOption.values()[userChoice - 1]);
            } catch (ForceExitApplicationException e) {
                System.out.println(e.getMessage());
                break;
            }
        }

    }

    private void displayOptions() {
        System.out.println("\nPlease Choose an Option to Perform CRUD Operations");
        String[] options = new String[RestaurantManagementOption.values().length];
        for (int i = 0; i < RestaurantManagementOption.values().length; i++) {
            options[i] = RestaurantManagementOption.values()[i].getDescription();
        }

        PrettierPrinter.displayTable(options);
    }

    private void mapOption(RestaurantManagementOption option) {
        switch (option) {
            case MENU: {
                MenuConsoleView menuConsoleView = ServiceLocator.getService(MenuConsoleView.class.getName());
                menuConsoleView.chooseOption();
                break;
            }
            case MENU_ITEM: {
                MenuItemConsoleView menuItemConsoleView = ServiceLocator.getService(MenuItemConsoleView.class.getName());
                menuItemConsoleView.chooseOption();
                break;
            }
            case ORDER: {
                OrderConSoleView orderConSoleView = ServiceLocator.getService(OrderConSoleView.class.getName());
                orderConSoleView.chooseOption();
                break;
            }
            case ORDER_DETAIL: {
                OrderDetailConsoleView orderDetailConsoleView = ServiceLocator.getService(OrderDetailConsoleView.class.getName());
                orderDetailConsoleView.chooseOption();
                break;
            }
        }
    }
}
