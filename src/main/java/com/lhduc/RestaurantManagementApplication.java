package com.lhduc;

import com.lhduc.common.patterns.servicelocator.ServiceLocator;
import com.lhduc.configs.ApplicationConfig;
import com.lhduc.exceptions.ApplicationRuntimeException;
import com.lhduc.views.RestaurantConsoleView;

public class RestaurantManagementApplication {
    public static void main(String[] args) {
        RestaurantManagementApplication application = new RestaurantManagementApplication();
        application.runConfig();
        application.displaySomeInformation();
        application.start();
    }

    private void start() {
        this.displayApplicationTitle();
        try {
            RestaurantConsoleView restaurantConsoleView = ServiceLocator.getService(RestaurantConsoleView.class.getName());
            restaurantConsoleView.show();
        } catch (ApplicationRuntimeException e) {
            e.printStackTrace();
        }
    }

    private void runConfig() {
        ApplicationConfig.readProperties();
    }

    private void displaySomeInformation() {
        System.out.println("** Application is running in jar file: " + ApplicationConfig.isRunningFromJAR());

        String databaseType = ApplicationConfig.getProperty("database");
        if (databaseType == null) {
            System.out.println("** Database Config is empty **");
            return;
        }
        System.out.println("** Database Config is " + databaseType.toUpperCase() + " **");
    }

    private void displayApplicationTitle() {
        System.out.println("\n--- RESTAURANT APPLICATION ---");
    }
}