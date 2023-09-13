import common.patterns.servicelocator.ServiceLocator;
import configs.ApplicationConfig;
import exceptions.ApplicationRuntimeException;
import views.MenuConsoleView;
import views.RestaurantConsoleView;

public class RestaurantManagementApplication {
    public static void main(String[] args) {
        RestaurantManagementApplication application = new RestaurantManagementApplication();
        application.runConfig();
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
        System.out.println("** Database Config is " + ApplicationConfig.getProperties().get("database").toUpperCase() + " **");
    }

    private void displayApplicationTitle() {
        System.out.println("\n--- RESTAURANT APPLICATION ---");
    }
}