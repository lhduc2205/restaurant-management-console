package com.lhduc.util;

import com.lhduc.common.constant.MessageConstant;
import com.lhduc.common.enums.Origin;
import com.lhduc.model.dto.MenuItemDto;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInputUtil {
    public static int enterInteger(String title) {
        while (true) {
            try {
                System.out.print("👉 " + title + ": ");
                Scanner scanner = new Scanner(System.in);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(MessageConstant.PLEASE_ENTER_NUMBER);
            }
        }
    }

    public static double enterDouble(String title) {
        while (true) {
            try {
                System.out.print("👉 " + title + ": ");
                Scanner scanner = new Scanner(System.in);
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println(MessageConstant.PLEASE_ENTER_NUMBER);
            }
        }
    }

    public static int enterInteger(String title, int optionLength) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.print("👉 " + title + ": ");
                int input = scanner.nextInt();
                if (input < 1 || input > optionLength) {
                    throw new IndexOutOfBoundsException("⚠️ Please enter the number in range (1 -> " + optionLength + ")");
                }
                return input;
            } catch (InputMismatchException e) {
                System.out.println(MessageConstant.PLEASE_ENTER_NUMBER);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static boolean getUserChoiceForYesNoOption(String title) {
        System.out.print(title);
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();

        return option.equalsIgnoreCase("y");
    }

    public static MenuItemDto getMenuItemFromPrompt(int menuId) {
        Scanner scanner = new Scanner(System.in);

        return getSimpleMenuItemInformation(scanner, menuId);
    }

    public static MenuItemDto getMenuItemFromPrompt() {
        Scanner scanner = new Scanner(System.in);

        int menuId = UserInputUtil.enterInteger("Enter menuId");

        return getSimpleMenuItemInformation(scanner, menuId);
    }

    private static MenuItemDto getSimpleMenuItemInformation(Scanner scanner, int menuId) {
        System.out.print("👉 Enter name: ");
        String name = scanner.nextLine();

        System.out.print("👉 Enter description: ");
        String desc = scanner.nextLine();

        double price = UserInputUtil.enterDouble("Enter price");

        System.out.println("Choose origin: ");
        MenuDisplayUtil.displayOrigin();
        System.out.print("👉 Your option is: ");
        Origin origin = Origin.values()[scanner.nextInt() - 1];

        return new MenuItemDto(name, desc, price, origin, menuId);
    }
}
