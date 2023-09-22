package com.lhduc.utils;

public class PrettierPrinter {
    public static void displayTable(String[] options) {
        System.out.printf("+----------------------------------+%n");
        System.out.printf("| Option | %-23s |%n", "Description");
        System.out.printf("+----------------------------------+%n");
        for (int i = 0; i < options.length; i++) {
            System.out.printf("|   %-4d | %-23s |%n", i + 1, options[i]);
        }
        System.out.printf("+----------------------------------+%n");
    }
}
