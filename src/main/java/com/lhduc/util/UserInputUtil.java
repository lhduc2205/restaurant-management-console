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
                double input = scanner.nextDouble();
                System.out.println(Double.MAX_VALUE);

                return input;
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

    public static String enterString(String title) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("👉 " + title + ": ");

        return scanner.nextLine();
    }

    public static boolean getUserChoiceForYesNoOption(String title) {
        System.out.print("❔ " + title);
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();

        return option.equalsIgnoreCase("y");
    }

    public static MenuItemDto getMenuItemFromPrompt(int menuId) {
        return getSimpleMenuItemInformation(menuId);
    }

    public static MenuItemDto getMenuItemFromPrompt() {
        int menuId = UserInputUtil.enterInteger("Enter menuId");

        return getSimpleMenuItemInformation(menuId);
    }

    private static MenuItemDto getSimpleMenuItemInformation(int menuId) {
        String name = UserInputUtil.enterString("Enter name");
        String desc = UserInputUtil.enterString("Enter description");
        double price = UserInputUtil.enterDouble("Enter price");

        System.out.println("Choose origin: ");
        MenuDisplayUtil.displayOrigin();
        int originIndex = UserInputUtil.enterInteger("Your option is", Origin.values().length);
        Origin origin = Origin.values()[originIndex - 1];

        return new MenuItemDto(name, desc, price, origin, menuId);
    }
}
