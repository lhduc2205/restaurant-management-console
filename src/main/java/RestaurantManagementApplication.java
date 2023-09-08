import cores.dependency_container.DependencyContainer;
import views.MenuConsoleView;

public class RestaurantManagementApplication {
    public static void main(String[] args) {
        RestaurantManagementApplication application = new RestaurantManagementApplication();
        application.start();
    }

    private void start() {
        this.displayApplicationTitle();
        MenuConsoleView menuConsoleView = DependencyContainer.menuConsoleView;
        menuConsoleView.chooseOption();
    }

    private void displayApplicationTitle() {
        System.out.println("\n--- RESTAURANT APPLICATION ---");
    }
}