
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.Font;

public class Main extends Application {

    private final String USERNAME = "admin";
    private final String PASSWORD = "admin123";

    @Override
    public void start(Stage stage) {

        // Login page layout
        VBox loginBox = new VBox(18);

        loginBox.setAlignment(Pos.CENTER);
        loginBox.setPadding(new Insets(35));
        loginBox.setMaxWidth(400);

        loginBox.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 15;"
        );

        // Application title
        Label title = new Label("SMART PHARMACY");
        title.setFont(Font.font("Arial", 25));
        title.setStyle("-fx-text-fill: #192D4B;");

        Label subtitle = new Label(
            "Medicine Management System"
        );

        subtitle.setStyle("-fx-text-fill: gray;");

        // Username
        Label userLabel = new Label("Username");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter username");

        usernameField.setMaxWidth(300);

        // Password
        Label passLabel = new Label("Password");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");

        passwordField.setMaxWidth(300);

        // Error message
        Label message = new Label();
        message.setStyle("-fx-text-fill: red;");

        // Login button
        Button loginButton = new Button("LOGIN");

        loginButton.setMaxWidth(300);

        loginButton.setStyle(
            "-fx-background-color: #192D4B;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 15px;" +
            "-fx-padding: 12;"
        );

        // Login validation
        loginButton.setOnAction(e -> {

            String username = usernameField.getText();
            String password = passwordField.getText();

            if (username.equals(USERNAME) &&
                password.equals(PASSWORD)) {

                message.setText("");

                // Open Pharmacy Dashboard
                PharmacyUI ui = new PharmacyUI();

                stage.setTitle(
                    "Smart Pharmacy Management System"
                );

                stage.setScene(ui.createScene());

                stage.setWidth(1100);
                stage.setHeight(700);
                stage.centerOnScreen();

            } else {

                message.setText(
                    "Invalid username or password!"
                );
            }
        });

        // Press Enter to login
        passwordField.setOnAction(e ->
            loginButton.fire()
        );

        loginBox.getChildren().addAll(
            title,
            subtitle,
            userLabel,
            usernameField,
            passLabel,
            passwordField,
            message,
            loginButton
        );

        // Background
        StackPane root = new StackPane(loginBox);

        root.setPadding(new Insets(30));

        root.setStyle(
            "-fx-background-color: #192D4B;"
        );

        Scene scene = new Scene(root, 500, 550);

        stage.setTitle("Smart Pharmacy - Login");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
