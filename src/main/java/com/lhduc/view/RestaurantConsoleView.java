package com.lhduc.view;

import com.lhduc.common.enums.RestaurantManagementOption;
import com.lhduc.common.pattern.servicelocator.ServiceLocator;
import com.lhduc.exception.ForceExitApplicationException;
import com.lhduc.util.PrettierPrinter;
import com.lhduc.util.UserInputUtil;

import java.util.Arrays;
import java.util.List;

public class RestaurantConsoleView {
    public void show() {
        while (true) {
            try {
                this.displayOptions();

                int userChoice = UserInputUtil.enterInteger("Your choice", RestaurantManagementOption.getLength());
                this.mapOption(RestaurantManagementOption.get(userChoice - 1));
            } catch (ForceExitApplicationException e) {
                System.out.println(e.getMessage());
                break;
            }
        }

    }

    private void displayOptions() {
        List<String> options = RestaurantManagementOption.getDescriptions();

        System.out.println();
        PrettierPrinter.displayTable(options);
    }

    private void mapOption(RestaurantManagementOption option) {
        switch (option) {
            case MENU: {
                MenuConsoleView menuConsoleView = ServiceLocator.getService(MenuConsoleView.class);
                menuConsoleView.chooseOption();
                break;
            }
            case MENU_ITEM: {
                MenuItemConsoleView menuItemConsoleView = ServiceLocator.getService(MenuItemConsoleView.class);
                menuItemConsoleView.chooseOption();
                break;
            }
            case ORDER: {
                OrderConSoleView orderConSoleView = ServiceLocator.getService(OrderConSoleView.class);
                orderConSoleView.chooseOption();
                break;
            }
            case ORDER_DETAIL: {
                OrderDetailConsoleView orderDetailConsoleView = ServiceLocator.getService(OrderDetailConsoleView.class);
                orderDetailConsoleView.chooseOption();
                break;
            }
        }
    }
}
