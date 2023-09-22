package com.lhduc.views;

import com.lhduc.common.constants.MessageConstant;
import com.lhduc.common.enums.CrudOption;
import com.lhduc.exceptions.ForceExitApplicationException;
import com.lhduc.utils.PrettierPrinter;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The ConsoleViewTemplate is an abstract class serves as a Template for creating
 * console-base views for CRUD operations.
 */
public abstract class ConsoleViewTemplate {

    /**
     * Retrieves the title for CRUD options menu.
     *
     * @return A String representing the title for CRUD options menu.
     */
    protected abstract String getOptionTitle();

    /**
     * Performs an action based on the selected CRUD option.
     * @param option The selected CRUD option to perform an action for.
     */
    protected abstract void doAction(CrudOption option);

    /**
     * Presents a menu of CRUD options to the user and handles their choice of action.
     * The user can choose from options to view, create, update, or delete items, or
     * go back to the previous view.
     */
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

    /**
     * Prints available CRUD options to the console and provides back to previous view option for user.
     */
    private void printOptions() {
        String[] options = {
                "Show " + getOptionTitle(),
                "Create " + getOptionTitle(),
                "Update " + getOptionTitle(),
                "Delete " + getOptionTitle(),
                "Back to previous view",
        };
        System.out.println("\n(Selected field: " + getOptionTitle() + ")");
        PrettierPrinter.displayTable(options);
    }
}
