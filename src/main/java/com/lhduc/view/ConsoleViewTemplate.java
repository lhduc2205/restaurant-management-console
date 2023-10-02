package com.lhduc.view;

import com.lhduc.common.enums.CrudOption;
import com.lhduc.util.PrettierPrinter;
import com.lhduc.util.UserInputUtil;

import java.util.Arrays;
import java.util.List;

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
     *
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
            int option = UserInputUtil.enterInteger("Your option is", CrudOption.getLength());
            CrudOption crudOption = CrudOption.get(option - 1);

            if (crudOption == CrudOption.BACK) {
                break;
            }

            this.doAction(crudOption);
        }
    }

    /**
     * Prints available CRUD options to the console and provides back to previous view option for user.
     */
    private void printOptions() {
        List<String> options = Arrays.stream(CrudOption.values()).map(o -> {
            if (o == CrudOption.BACK) {
                return o.getDescription();
            } else {
                return o.getDescription(this.getOptionTitle());
            }
        }).toList();

        System.out.println("\n💡(" + getOptionTitle() + ")");
        PrettierPrinter.displayTable(options);
    }
}
