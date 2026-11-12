
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        PharmacyUI ui = new PharmacyUI();

        stage.setTitle("Pharmatrix - Pharmacy Management System");
        stage.setScene(ui.createScene());

        stage.setWidth(1100);
        stage.setHeight(700);

        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

