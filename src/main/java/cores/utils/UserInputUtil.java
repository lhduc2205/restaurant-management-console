package cores.utils;

import cores.enums.MenuCategory;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInputUtil {
    public static int enterInteger(String title) {
        while (true) {
            try {
                System.out.print(title + ": ");
                Scanner scanner = new Scanner(System.in);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("*Please enter the number");
            }
        }
    }

    public static double enterDouble(String title) {
        while (true) {
            try {
                System.out.print(title + ": ");
                Scanner scanner = new Scanner(System.in);
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("*Please enter the number");
            }
        }
    }

    public static int enterInteger(String title, int optionLength) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.print(title + ": ");
                int input = scanner.nextInt();
                if (input < 1 || input > optionLength) {
                    throw new IndexOutOfBoundsException("*Please enter the number in range (1 -> " + optionLength + ")");
                }
                return input;
            } catch (InputMismatchException e) {
                System.out.println("*Please enter the number");
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
