package com.lhduc.util;

import java.util.List;

public class PrettierPrinter {
    public static void displayTable(List<String> options) {
        System.out.printf("+----------------------------------+%n");
        System.out.printf("| Option | %-23s |%n", "Description");
        System.out.printf("+----------------------------------+%n");
        for (int i = 0; i < options.size(); i++) {
            System.out.printf("|   %-4d | %-23s |%n", i + 1, options.get(i));
        }
        System.out.printf("+----------------------------------+%n");
    }
}
