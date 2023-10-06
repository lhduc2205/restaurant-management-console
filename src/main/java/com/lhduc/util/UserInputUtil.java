package com.lhduc.util;

import com.lhduc.common.constant.MessageConstant;
import com.lhduc.common.enums.Origin;
import com.lhduc.exception.InvalidInputException;
import com.lhduc.model.dto.MenuItemDto;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInputUtil {
    private static boolean isNegativeNumber(int number) {
        return number < 0;
    }

    private static boolean isNegativeNumber(double number) {
        return number < 0;
    }

    public static int enterInteger(String title) {
        while (true) {
            try {
                System.out.print("👉 " + title + ": ");
                Scanner scanner = new Scanner(System.in);
                int input = scanner.nextInt();
                if (isNegativeNumber(input)) {
                    throw new InvalidInputException(MessageConstant.NOT_ALLOWED_NEGATIVE_NUMBER);
                }

                return input;
            } catch (InputMismatchException e) {
                System.out.println(MessageConstant.PLEASE_ENTER_NUMBER);
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static double enterDouble(String title) {
        while (true) {
            try {
                System.out.print("👉 " + title + ": ");
                Scanner scanner = new Scanner(System.in);
                double input = scanner.nextDouble();
                if (isNegativeNumber(input)) {
                    throw new InvalidInputException(MessageConstant.NOT_ALLOWED_NEGATIVE_NUMBER);
                }

                return input;
            } catch (InputMismatchException e) {
                System.out.println(MessageConstant.PLEASE_ENTER_NUMBER);
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int enterInteger(String title, int optionLength) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.print("👉 " + title + ": ");
                int input = scanner.nextInt();

                if (isNegativeNumber(input)) {
                    throw new InvalidInputException(MessageConstant.NOT_ALLOWED_NEGATIVE_NUMBER);
                }

                if (input < 1 || input > optionLength) {
                    throw new IndexOutOfBoundsException("⚠️ Please enter the number in range (1 -> " + optionLength + ")");
                }
                return input;
            } catch (InputMismatchException e) {
                System.out.println(MessageConstant.PLEASE_ENTER_NUMBER);
            } catch (IndexOutOfBoundsException | InvalidInputException e) {
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

        return option.trim().equalsIgnoreCase("y") || option.trim().isEmpty();
    }

    public static MenuItemDto getMenuItemFromPrompt(int menuId) {
        return getSimpleMenuItemInformation(menuId);
    }

    public static MenuItemDto getMenuItemFromPrompt() {
        int menuId = UserInputUtil.enterInteger(MessageConstant.ENTER_MENU_ID);

        return getSimpleMenuItemInformation(menuId);
    }

    private static MenuItemDto getSimpleMenuItemInformation(int menuId) {
        String name = UserInputUtil.enterString(MessageConstant.ENTER_NAME);
        String desc = UserInputUtil.enterString(MessageConstant.ENTER_DESC);
        double price = UserInputUtil.enterDouble(MessageConstant.ENTER_PRICE);

        System.out.println(MessageConstant.CHOOSE_ORIGIN);
        MenuDisplayUtil.displayOrigin();
        int originIndex = UserInputUtil.enterInteger(MessageConstant.YOUR_OPTION_IS, Origin.values().length);
        Origin origin = Origin.values()[originIndex - 1];

        return new MenuItemDto(name, desc, price, origin, menuId);
    }
}
