package com.lhduc.views;

import com.lhduc.common.enums.RestaurantManagementOption;
import com.lhduc.common.patterns.servicelocator.ServiceLocator;
import com.lhduc.exceptions.ForceExitApplicationException;
import com.lhduc.utils.UserInputUtil;

public class RestaurantConsoleView {
    public RestaurantConsoleView() {

    }

    public void show() {
        while (true) {
            try {
                System.out.println("\nRestaurant console view options: ");
                for (int i = 0; i < RestaurantManagementOption.values().length; i++) {
                    System.out.println((i + 1) + ". " + RestaurantManagementOption.values()[i].getDescription());
                }

                int userChoice = UserInputUtil.enterInteger("---> Your choice", RestaurantManagementOption.values().length);
                this.mapOption(RestaurantManagementOption.values()[userChoice - 1]);
            } catch (ForceExitApplicationException e) {
                System.out.println(e.getMessage());
                break;
            }
        }

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
