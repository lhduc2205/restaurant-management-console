package views;

import cores.constants.MessageConstant;
import cores.enums.CrudMenuOption;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class ConsoleViewManager {
    protected abstract String getOptionTitle();

    protected abstract void doAction(CrudMenuOption option);

    public void chooseOption() {
        this.printOptions();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.print("--> Your option is: ");
                int option = scanner.nextInt();

                if (option < 0 || option > CrudMenuOption.values().length) {
                    throw new IndexOutOfBoundsException();
                }

                this.doAction(CrudMenuOption.values()[option - 1]);
                this.chooseOptionAgain();
                break;
            } catch (InputMismatchException e) {
                if (scanner.next().equalsIgnoreCase(MessageConstant.EXIT)) {
                    System.out.println("\n" + MessageConstant.APPLICATION_TERMINATED);
                    return;
                }
                System.out.println("*Please enter the number");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("*Please enter the number in range (1 -> " + CrudMenuOption.values().length + ")");
            } finally {
                scanner.close();
            }
        }
    }

    private void printOptions() {
        System.out.println("\nPlease choose one of below options:");
        System.out.println("1. Show " + getOptionTitle());
        System.out.println("2. Create " + getOptionTitle());
        System.out.println("3. Update " + getOptionTitle());
        System.out.println("4. Delete " + getOptionTitle());
    }

    private void chooseOptionAgain() {
        System.out.print("\nDo you want to go back previous options? (Y/N): ");
        Scanner scanner = new Scanner(System.in);
        String yourChoose = scanner.nextLine();

        if (yourChoose.equalsIgnoreCase("y")) {
            chooseOption();
            scanner.close();
        }
    }
}
