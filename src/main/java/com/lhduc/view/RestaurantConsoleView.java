package com.lhduc.view;

import com.lhduc.common.constant.MessageConstant;
import com.lhduc.common.enums.RestaurantManagementOption;
import com.lhduc.exception.ApplicationRuntimeException;
import com.lhduc.util.PrettierPrinter;
import com.lhduc.util.UserInputUtil;

import java.util.List;

public class RestaurantConsoleView {
    public void show() {
        while (true) {
            try {
                this.displayRestaurantOptions();

                int userChoice = UserInputUtil.enterInteger(MessageConstant.YOUR_CHOICE, RestaurantManagementOption.getLength());
                this.mapOption(RestaurantManagementOption.get(userChoice - 1));
            } catch (ApplicationRuntimeException e) {
                System.out.println(e.getMessage());
                break;
            }
        }

    }

    private void displayRestaurantOptions() {
        List<String> restaurantOptions = RestaurantManagementOption.getDescriptions();

        System.out.println();
        PrettierPrinter.displayTable(restaurantOptions);
    }

    private void mapOption(RestaurantManagementOption option) {
        switch (option) {
            case MENU: {
                MenuConsoleView menuConsoleView = new MenuConsoleView();
                menuConsoleView.chooseOption();
                break;
            }
            case MENU_ITEM: {
                MenuItemConsoleView menuItemConsoleView = new MenuItemConsoleView();
                menuItemConsoleView.chooseOption();
                break;
            }
            case ORDER: {
                OrderConSoleView orderConSoleView = new OrderConSoleView();
                orderConSoleView.chooseOption();
                break;
            }
            case ORDER_DETAIL: {
                OrderDetailConsoleView orderDetailConsoleView = new OrderDetailConsoleView();
                orderDetailConsoleView.chooseOption();
                break;
            }
        }
    }
}
