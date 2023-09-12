package views;

import common.enums.RestaurantManagementOption;
import common.patterns.servicelocator.ServiceLocator;
import utils.UserInputUtil;

import java.util.Scanner;

public class RestaurantConsoleView {
    public RestaurantConsoleView() {

    }

    public void show() {
        System.out.println("\nPlease choose one of below options: ");
        for (int i = 0; i < RestaurantManagementOption.values().length; i++) {
            System.out.println((i + 1) + ". " + RestaurantManagementOption.values()[i].getDescription());
        }

        int userChoice = UserInputUtil.enterInteger("\n---> Your choice", RestaurantManagementOption.values().length);
        this.mapOption(RestaurantManagementOption.values()[userChoice - 1]);
    }

    private void mapOption(RestaurantManagementOption option) {
        switch (option) {
            case MENU: {
                ConsoleViewManager menuConsoleView = ServiceLocator.getService(MenuConsoleView.class.getName());
                menuConsoleView.chooseOption();
                break;
            }
            case MENU_ITEM: {
                break;
            }
            case ORDER: {
                break;
            }
        }
    }
}
