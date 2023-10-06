package com.lhduc;

import com.lhduc.common.constant.AppConstant;
import com.lhduc.config.ApplicationConfig;
import com.lhduc.exception.ApplicationRuntimeException;
import com.lhduc.view.RestaurantConsoleView;

public class RestaurantManagementApplication {
    public static void main(String[] args) {
        RestaurantManagementApplication application = new RestaurantManagementApplication();
        application.start();
    }

    private void start() {
        displayApplicationTitle();

        try {
            this.runConfig();
            this.displayConfigInformation();
            RestaurantConsoleView restaurantConsoleView = new RestaurantConsoleView();
            restaurantConsoleView.show();
        } catch (ApplicationRuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void runConfig() {
        ApplicationConfig.readProperties();
    }

    private void displayConfigInformation() {
        System.out.println("⚡️Is running in JAR: " + ApplicationConfig.isRunningFromJAR());

        String databaseType = ApplicationConfig.getProperty(AppConstant.DATABASE_PROPERTY);
        if (databaseType == null) {
            System.out.println("⚡️Database Config is empty ⚡️");
        } else {
            System.out.println("⚡️Database config: " + databaseType.toUpperCase());
        }
    }

    private void displayApplicationTitle() {
        System.out.println("""
                ㅤ ／￣￣ヽ＿＿
                　/^ヽ ・     ●
                 ｜# ｜　＿＿ノ
                　`―-)=(   ／￣∨￣＼
                　　／ㅤ ) l          |
                　c(　　ﾉ  ＼        ／
                　  _｣ LL_   ＼  ／
                　 (＿＿)_)
                """);
        System.out.println("\n✨✨ RESTAURANT APPLICATION ✨✨\n");
    }
}