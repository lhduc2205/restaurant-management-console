package com.lhduc.views;

import com.lhduc.common.constants.MessageConstant;
import com.lhduc.common.enums.CrudOption;
import com.lhduc.exceptions.ForceExitApplicationException;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class ConsoleViewTemplate {
    protected abstract String getOptionTitle();

    protected abstract void doAction(CrudOption option);

    public void chooseOption() {
        while (true) {
            this.printOptions();
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.print("--> Your option is: ");
                int option = scanner.nextInt();

                if (option == 5) {
                    break;
                }

                if (option < 0 || option > CrudOption.values().length) {
                    throw new IndexOutOfBoundsException();
                }

                this.doAction(CrudOption.values()[option - 1]);
            } catch (InputMismatchException e) {
                if (scanner.next().equalsIgnoreCase(MessageConstant.EXIT)) {
                    throw new ForceExitApplicationException(MessageConstant.APPLICATION_TERMINATED);
                }
                System.out.println("*Please enter the number");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("*Please enter the number in range (1 -> " + CrudOption.values().length + ")");
            }
        }
    }

    private void printOptions() {
        System.out.println("\nPlease choose one of below options:");
        System.out.println("1. Show " + getOptionTitle());
        System.out.println("2. Create " + getOptionTitle());
        System.out.println("3. Update " + getOptionTitle());
        System.out.println("4. Delete " + getOptionTitle());
        System.out.println("5. Back to previous view");
    }

    private void chooseOptionAgain() {
        System.out.print("\nDo you want to go back previous options? (Y/N): ");
        Scanner scanner = new Scanner(System.in);
        String yourChoose = scanner.nextLine();

        if (yourChoose.equalsIgnoreCase("y")) {
            chooseOption();
        }
    }
}
