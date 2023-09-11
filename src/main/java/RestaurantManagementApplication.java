import common.dependencycontainer.DependencyContainer;
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

//        try {
//            InputStream inputStream = new FileInputStream(new File(AppPath.RESOURCES + "/properties.yaml"));
//
//
//            Yaml yaml = new Yaml();
//            Map<String, Map<String, String>> data = yaml.load(inputStream);
//            System.out.println(data.get("database").get("type"));
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }

    private void displayApplicationTitle() {
        System.out.println("\n--- RESTAURANT APPLICATION ---");
    }
}