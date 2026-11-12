import javafx.scene.chart.PieChart;
import javafx.scene.control.ProgressIndicator;

import java.time.LocalDateTime;
import javafx.beans.property.ReadOnlyObjectWrapper;
import java.util.LinkedHashMap;
import java.util.Map;
import javafx.util.StringConverter;
import javafx.collections.transformation.FilteredList;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class PharmacyUI {

    private BorderPane root;
    private ObservableList<Medicine> medicineList =
        FXCollections.observableArrayList();
    private Label totalMedicinesValue;
    private Label availableStockValue;
    private Label nearExpiryValue;
    private Label expiredMedicinesValue;
    private PieChart medicineStatusChart;
private ProgressIndicator healthTracker;
private Label healthPercentageLabel;
private Label healthyStatusLabel;
private Label nearAlertValue;
private Label expiredAlertValue;

    private final String NAVY = "#192D4B";
    private final String BACKGROUND = "#F2F5FA";

    public Scene createScene() {
        medicineList.setAll(PharmacyFileManager.loadMedicines());
        root = new BorderPane();
        root.setTop(createHeader());
        root.setLeft(createSidebar());
        root.setCenter(createDashboard());

        root.setStyle("-fx-background-color: " + BACKGROUND + ";");

        return new Scene(root, 1100, 700);
    }
    
// Top Header
private HBox createHeader() {

    HBox header = new HBox(15);
    header.setPadding(new Insets(18, 28, 18, 28));
    header.setAlignment(Pos.CENTER_LEFT);

    header.setStyle(
        "-fx-background-color: white;" +
        "-fx-border-color: #D5DCE5;" +
        "-fx-border-width: 0 0 1 0;"
    );

    VBox titleBox = new VBox(3);

    Label title = new Label("Pharmatrix");
    title.setFont(Font.font("Arial", 34));
    title.setStyle(
        "-fx-text-fill: #123B50;" +
        "-fx-font-weight: bold;"
    );

    Label subtitle = new Label(
        "Pharmacy Management & Inventory System"
    );

    subtitle.setFont(Font.font("Arial", 15));
    subtitle.setStyle(
        "-fx-text-fill: #31566B;" +
        "-fx-font-style: italic;"
    );

    titleBox.getChildren().addAll(title, subtitle);

    Region spacer = new Region();
    HBox.setHgrow(spacer, Priority.ALWAYS);

    Label slogan = new Label(
        "Better Health. Smarter Management."
    );

    slogan.setFont(Font.font("Arial", 15));
    slogan.setStyle(
        "-fx-text-fill: #176477;" +
        "-fx-font-style: italic;"
    );

    header.getChildren().addAll(
        titleBox, spacer, slogan
    );

    return header;
}

    // Sidebar
    private VBox createSidebar() {

        VBox sidebar = new VBox(15);

        sidebar.setPadding(new Insets(25, 15, 20, 15));
        sidebar.setPrefWidth(220);
        sidebar.setStyle("-fx-background-color: " + NAVY + ";");

        Label logo = new Label("Pharmatrix");
        logo.setTextFill(Color.WHITE);
        logo.setFont(Font.font("Arial", 19));
        Label subtitle = new Label(
    "Pharmacy Management & Expiry Tracker"
);
subtitle.setFont(Font.font("Arial", 11));
subtitle.setStyle("-fx-text-fill: #64748B;");

        Button dashboard = createButton("Dashboard");
        Button medicines = createButton("Medicines");
        Button search = createButton("Search Medicines");
        Button expiry = createButton("Expiry Management");
        Button billing = createButton("Billing");
        Button reports = createButton("Stock Reports");
        Button history = createButton("Billing History");

        dashboard.setOnAction(e ->
            root.setCenter(createDashboard()));

        medicines.setOnAction(e ->
            root.setCenter(createMedicinePage()));

        search.setOnAction(e ->
            root.setCenter(createSearchPage()));

        expiry.setOnAction(e ->
            root.setCenter(createExpiryPage()));

        billing.setOnAction(e ->
            root.setCenter(createBillingPage()));

        reports.setOnAction(e ->
            root.setCenter(createReportsPage()));
        history.setOnAction(e ->
            root.setCenter(createBillingHistoryPage()));

        sidebar.getChildren().addAll(
            dashboard,
            medicines,
            search,
            expiry,
            billing,
            history,
            reports
        );

        return sidebar;
    }

    // Navigation buttons
    private Button createButton(String name) {

        Button button = new Button(name);

        button.setMaxWidth(Double.MAX_VALUE);
        button.setAlignment(Pos.CENTER_LEFT);
        button.setPadding(new Insets(12));

        button.setStyle(
            "-fx-background-color: #192D4B;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 14px;" +
            "-fx-background-radius: 6;"
        );

        return button;
    }

    // Dashboard
    private VBox createDashboard() {

    VBox dashboard = new VBox(16);
    dashboard.setPadding(new Insets(22));
    dashboard.setStyle(
        "-fx-background-color: " + BACKGROUND + ";"
    );

    Label heading = new Label("Dashboard Overview");
    heading.setFont(Font.font("Arial", 23));
    heading.setStyle(
        "-fx-text-fill: #192D4B;" +
        "-fx-font-weight: bold;"
    );

    // Pharmacy banner
    StackPane banner = new StackPane();

    banner.setMinHeight(145);
    banner.setPrefHeight(145);
    banner.setPadding(new Insets(18, 25, 18, 25));

    banner.setStyle(
        "-fx-background-color: linear-gradient(to right, #D9F2F7, #B9E5ED, #EAF7FA);" +
        "-fx-background-radius: 16;" +
        "-fx-border-color: #B8DDE7;" +
        "-fx-border-radius: 16;"
    );

    VBox bannerText = new VBox(7);

    Label bannerTitle = new Label(
        "Manage Medicines.\nCare Better."
    );

    bannerTitle.setFont(Font.font("Arial", 25));
    bannerTitle.setStyle(
        "-fx-text-fill: #123B50;" +
        "-fx-font-weight: bold;"
    );

    Label bannerSubtitle = new Label(
        "Simple, smart and reliable pharmacy inventory management"
    );

    bannerSubtitle.setFont(Font.font("Arial", 12));

    bannerSubtitle.setStyle(
        "-fx-text-fill: #31566B;"
    );

    bannerText.getChildren().addAll(
        bannerTitle, bannerSubtitle
    );

    Label medicineArt = new Label("💊     ✚     🩺");
    medicineArt.setFont(Font.font("Arial", 32));

    HBox bannerContent = new HBox();

    bannerContent.setAlignment(Pos.CENTER_LEFT);

    Region bannerSpacer = new Region();
    HBox.setHgrow(bannerSpacer, Priority.ALWAYS);

    bannerContent.getChildren().addAll(
        bannerText, bannerSpacer, medicineArt
    );

    banner.getChildren().add(bannerContent);

    // Dashboard cards
    totalMedicinesValue = new Label("0");
    availableStockValue = new Label("0");
    nearExpiryValue = new Label("0");
    expiredMedicinesValue = new Label("0");

    GridPane cards = new GridPane();

    cards.setHgap(12);
    cards.setVgap(12);

    for (int i = 0; i < 4; i++) {
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(25);
        column.setHgrow(Priority.ALWAYS);
        cards.getColumnConstraints().add(column);
    }

    cards.add(
        createCard("Total Medicines", totalMedicinesValue), 0, 0
    );

    cards.add(
        createCard("Available Stock", availableStockValue), 1, 0
    );

    cards.add(
        createCard("Near Expiry", nearExpiryValue), 2, 0
    );

    cards.add(
        createCard("Expired Medicines", expiredMedicinesValue), 3, 0
    );

    // Pie chart
    medicineStatusChart = new PieChart();

    medicineStatusChart.setTitle("Medicine Status Overview");
    medicineStatusChart.setLegendVisible(true);
    medicineStatusChart.setLabelsVisible(true);
    medicineStatusChart.setPrefSize(340, 230);

    VBox chartPanel = createDashboardPanel();

    chartPanel.getChildren().add(medicineStatusChart);

    // Inventory health tracker
    healthTracker = new ProgressIndicator(0);

    healthTracker.setPrefSize(125, 125);
    healthTracker.setStyle("-fx-accent: #079B78;");

    healthPercentageLabel = new Label("0%");

    healthPercentageLabel.setFont(Font.font("Arial", 27));
    healthPercentageLabel.setStyle(
        "-fx-text-fill: #075E50;" +
        "-fx-font-weight: bold;"
    );

    healthyStatusLabel = new Label("Healthy");

    healthyStatusLabel.setFont(Font.font("Arial", 15));

    StackPane trackerCircle = new StackPane(
        healthTracker,
        new VBox(
            0,
            healthPercentageLabel,
            healthyStatusLabel
        )
    );

    trackerCircle.setAlignment(Pos.CENTER);

    VBox trackerPanel = createDashboardPanel();

    Label trackerTitle = new Label(
        "Inventory Health Tracker"
    );

    trackerTitle.setFont(Font.font("Arial", 16));
    trackerTitle.setStyle(
        "-fx-text-fill: #192D4B;" +
        "-fx-font-weight: bold;"
    );

    trackerPanel.setAlignment(Pos.TOP_CENTER);

    trackerPanel.getChildren().addAll(
        trackerTitle,
        trackerCircle
    );

    // Expiry alerts
    VBox alertsPanel = createDashboardPanel();

    Label alertsTitle = new Label("Expiry Alerts");

    alertsTitle.setFont(Font.font("Arial", 16));
    alertsTitle.setStyle(
        "-fx-text-fill: #192D4B;" +
        "-fx-font-weight: bold;"
    );

    nearAlertValue = new Label("0");
    nearAlertValue.setFont(Font.font("Arial", 21));
    nearAlertValue.setStyle(
        "-fx-text-fill: #E69A36;" +
        "-fx-font-weight: bold;"
    );

    expiredAlertValue = new Label("0");
    expiredAlertValue.setFont(Font.font("Arial", 21));
    expiredAlertValue.setStyle(
        "-fx-text-fill: #D9534F;" +
        "-fx-font-weight: bold;"
    );

    HBox nearAlert = new HBox(
        8,
        new Label("🕒 Near Expiry"),
        nearAlertValue
    );

    HBox expiredAlert = new HBox(
        8,
        new Label("⚠ Expired"),
        expiredAlertValue
    );

    Button expiryButton = new Button(
        "View Expiry Details"
    );

    expiryButton.setMaxWidth(Double.MAX_VALUE);

    expiryButton.setStyle(
        "-fx-background-color: #087E8B;" +
        "-fx-text-fill: white;" +
        "-fx-background-radius: 7;" +
        "-fx-padding: 9 14;"
    );

    expiryButton.setOnAction(e ->
        root.setCenter(createExpiryPage())
    );

    alertsPanel.getChildren().addAll(
        alertsTitle,
        nearAlert,
        expiredAlert,
        expiryButton
    );

    // Arrange three dashboard panels
    GridPane lowerPanels = new GridPane();

    lowerPanels.setHgap(12);
    lowerPanels.setVgap(12);

    ColumnConstraints left = new ColumnConstraints();
    left.setPercentWidth(42);
    left.setHgrow(Priority.ALWAYS);

    ColumnConstraints middle = new ColumnConstraints();
    middle.setPercentWidth(30);
    middle.setHgrow(Priority.ALWAYS);

    ColumnConstraints right = new ColumnConstraints();
    right.setPercentWidth(28);
    right.setHgrow(Priority.ALWAYS);

    lowerPanels.getColumnConstraints().addAll(
        left, middle, right
    );

    lowerPanels.add(chartPanel, 0, 0);
    lowerPanels.add(trackerPanel, 1, 0);
    lowerPanels.add(alertsPanel, 2, 0);

    dashboard.getChildren().addAll(
        heading,
        banner,
        cards,
        lowerPanels
    );

    updateDashboard();

    ScrollPane scrollPane = new ScrollPane(dashboard);
    scrollPane.setFitToWidth(true);

    scrollPane.setStyle(
        "-fx-background: " + BACKGROUND + ";" +
        "-fx-background-color: " + BACKGROUND + ";"
    );

    VBox wrapper = new VBox(scrollPane);

    VBox.setVgrow(scrollPane, Priority.ALWAYS);

    return wrapper;
}
private VBox createDashboardPanel() {

    VBox panel = new VBox(13);

    panel.setPadding(new Insets(15));
    panel.setMinHeight(220);

    panel.setStyle(
        "-fx-background-color: white;" +
        "-fx-background-radius: 13;" +
        "-fx-border-color: #D9E3EF;" +
        "-fx-border-radius: 13;" +
        "-fx-effect: dropshadow(gaussian, rgba(25,45,75,0.08), 8, 0, 0, 2);"
    );

    return panel;
}
    // Dashboard cards
    
private VBox createCard(String title, Label number) {

    VBox card = new VBox(12);
    card.setPadding(new Insets(20));
    card.setMaxWidth(Double.MAX_VALUE);
card.setPrefHeight(112);

    String accentColor = "#168C91";

    if (title.equals("Total Medicines")) {
        accentColor = "#247BA0";
    } else if (title.equals("Available Stock")) {
        accentColor = "#26966F";
    } else if (title.equals("Near Expiry")) {
        accentColor = "#E69A36";
    } else if (title.equals("Expired Medicines")) {
        accentColor = "#D9534F";
    }

    Label heading = new Label(title);
    heading.setFont(Font.font("Arial", 15));
    heading.setStyle("-fx-text-fill: #64748B;");

    number.setFont(Font.font("Arial", 30));
    number.setStyle("-fx-text-fill: " + accentColor
            + "; -fx-font-weight: bold;");

    Region accent = new Region();
    accent.setPrefHeight(5);
    accent.setMaxWidth(Double.MAX_VALUE);
    accent.setStyle("-fx-background-color: " + accentColor
            + "; -fx-background-radius: 5;");

    card.getChildren().addAll(accent, heading, number);

    card.setStyle(
        "-fx-background-color: white;"
        + "-fx-background-radius: 12;"
        + "-fx-border-radius: 12;"
        + "-fx-border-color: #E2E8F0;"
        + "-fx-border-width: 1;"
        + "-fx-effect: dropshadow(gaussian, "
        + "rgba(25,45,75,0.10), 10, 0, 0, 3);"
    );

    return card;
}

    // Other pages
    private VBox createPage(String title) {

        VBox page = new VBox(20);
        page.setPadding(new Insets(30));

        Label heading = new Label(title);
        heading.setFont(Font.font("Arial", 28));

        page.getChildren().add(heading);

        return page;
    }
    private VBox createMedicinePage() {

    VBox page = new VBox(15);
    page.setPadding(new Insets(25));

    Label heading = new Label("Medicine Management");
    heading.setFont(Font.font("Arial", 26));

    // Input fields
    TextField idField = new TextField();
    TextField nameField = new TextField();
    TextField categoryField = new TextField();
    TextField manufacturerField = new TextField();
    TextField priceField = new TextField();
    TextField quantityField = new TextField();

    DatePicker expiryPicker = new DatePicker();

    idField.setPromptText("Medicine ID");
    nameField.setPromptText("Medicine Name");
    categoryField.setPromptText("Category");
    manufacturerField.setPromptText("Manufacturer");
    priceField.setPromptText("Price");
    quantityField.setPromptText("Quantity");

    // Form layout
    GridPane form = new GridPane();
    form.setHgap(12);
    form.setVgap(12);

    form.add(new Label("Medicine ID:"), 0, 0);
    form.add(idField, 1, 0);

    form.add(new Label("Medicine Name:"), 2, 0);
    form.add(nameField, 3, 0);

    form.add(new Label("Category:"), 0, 1);
    form.add(categoryField, 1, 1);

    form.add(new Label("Manufacturer:"), 2, 1);
    form.add(manufacturerField, 3, 1);

    form.add(new Label("Price:"), 0, 2);
    form.add(priceField, 1, 2);

    form.add(new Label("Quantity:"), 2, 2);
    form.add(quantityField, 3, 2);

    form.add(new Label("Expiry Date:"), 0, 3);
    form.add(expiryPicker, 1, 3);

    // Add button
    Button addButton = new Button("Add Medicine");

    addButton.setStyle(
        "-fx-background-color: #192D4B;" +
        "-fx-text-fill: white;" +
        "-fx-padding: 10 20;"
    );

    // Table
    TableView<Medicine> table = new TableView<>();
    table.setItems(medicineList);

    TableColumn<Medicine, String> idCol =
        new TableColumn<>("ID");
    idCol.setCellValueFactory(
        new PropertyValueFactory<>("id"));

    TableColumn<Medicine, String> nameCol =
        new TableColumn<>("Name");
    nameCol.setCellValueFactory(
        new PropertyValueFactory<>("name"));

    TableColumn<Medicine, String> categoryCol =
        new TableColumn<>("Category");
    categoryCol.setCellValueFactory(
        new PropertyValueFactory<>("category"));

    TableColumn<Medicine, String> manufacturerCol =
        new TableColumn<>("Manufacturer");
    manufacturerCol.setCellValueFactory(
        new PropertyValueFactory<>("manufacturer"));

    TableColumn<Medicine, Double> priceCol =
        new TableColumn<>("Price");
    priceCol.setCellValueFactory(
        new PropertyValueFactory<>("price"));

    TableColumn<Medicine, Integer> quantityCol =
        new TableColumn<>("Quantity");
    quantityCol.setCellValueFactory(
        new PropertyValueFactory<>("quantity"));

    TableColumn<Medicine, LocalDate> expiryCol =
        new TableColumn<>("Expiry Date");
    expiryCol.setCellValueFactory(
        new PropertyValueFactory<>("expiryDate"));

    table.getColumns().addAll(
        idCol, nameCol, categoryCol,
        manufacturerCol, priceCol,
        quantityCol, expiryCol
    );

    // Add medicine to table
    addButton.setOnAction(e -> {

        try {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String category = categoryField.getText().trim();
            String manufacturer = manufacturerField.getText().trim();

            if (id.isEmpty() || name.isEmpty() ||
                category.isEmpty() || manufacturer.isEmpty() ||
                priceField.getText().isEmpty() ||
                quantityField.getText().isEmpty() ||
                expiryPicker.getValue() == null) {

                showMessage("Please fill all fields.");
                return;
            }

            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());

            if (price <= 0 || quantity < 0) {
                showMessage("Enter a valid price and quantity.");
                return;
            }

            Medicine medicine = new Medicine(
                id, name, category, manufacturer,
                price, quantity, expiryPicker.getValue()
            );

            medicineList.add(medicine);

            PharmacyFileManager.saveMedicines(medicineList);

            updateDashboard();

            idField.clear();
            nameField.clear();
            categoryField.clear();
            manufacturerField.clear();
            priceField.clear();
            quantityField.clear();
            expiryPicker.setValue(null);

        } catch (NumberFormatException ex) {
            showMessage("Price must be a number and quantity must be a whole number.");
        }
    });

    page.getChildren().addAll(
        heading,
        new Label("Enter Medicine Details"),
        form,
        addButton,
        new Label("Medicine List"),
        table
    );

    return page;
}

private void showMessage(String message) {

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Smart Pharmacy");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
private void updateDashboard() {

    int total = medicineList.size();
    int stock = 0;
    int nearExpiry = 0;
    int expired = 0;
    int healthy = 0;

    LocalDate today = LocalDate.now();

    for (Medicine medicine : medicineList) {

        stock += medicine.getQuantity();

        LocalDate expiry = medicine.getExpiryDate();

        if (expiry.isBefore(today)) {

            expired++;

        } else if (!expiry.isAfter(today.plusDays(30))) {

            nearExpiry++;

        } else if (medicine.getQuantity() > 0) {

            healthy++;
        }
    }

    // Update dashboard cards
    if (totalMedicinesValue != null) {

        totalMedicinesValue.setText(
            String.valueOf(total)
        );

        availableStockValue.setText(
            String.valueOf(stock)
        );

        nearExpiryValue.setText(
            String.valueOf(nearExpiry)
        );

        expiredMedicinesValue.setText(
            String.valueOf(expired)
        );
    }

    // Update expiry alerts
    if (nearAlertValue != null) {

        nearAlertValue.setText(
            String.valueOf(nearExpiry)
        );

        expiredAlertValue.setText(
            String.valueOf(expired)
        );
    }

    // Update health tracker
    double percentage = total == 0
        ? 0
        : (double) healthy / total;

    if (healthTracker != null) {

        healthTracker.setProgress(percentage);

        healthPercentageLabel.setText(
            Math.round(percentage * 100) + "%"
        );
    }

    // Update pie chart
    if (medicineStatusChart != null) {

        medicineStatusChart.getData().setAll(

            new PieChart.Data(
                "Healthy (" + healthy + ")", healthy
            ),

            new PieChart.Data(
                "Near Expiry (" + nearExpiry + ")", nearExpiry
            ),

            new PieChart.Data(
                "Expired (" + expired + ")", expired
            )
        );
    }
}
private VBox createSearchPage() {

    VBox page = new VBox(15);
    page.setPadding(new Insets(25));

    Label heading = new Label("Search Medicines");
    heading.setFont(Font.font("Arial", 26));

    TextField searchField = new TextField();
    searchField.setPromptText("Search by medicine name or category");
    searchField.setMaxWidth(400);

    // Connect Search with Medicine Management list
    FilteredList<Medicine> filteredList =
        new FilteredList<>(medicineList, medicine -> true);

    TableView<Medicine> table = new TableView<>();
    table.setItems(filteredList);

    TableColumn<Medicine, String> idCol =
        new TableColumn<>("ID");
    idCol.setCellValueFactory(
        new PropertyValueFactory<>("id"));

    TableColumn<Medicine, String> nameCol =
        new TableColumn<>("Name");
    nameCol.setCellValueFactory(
        new PropertyValueFactory<>("name"));

    TableColumn<Medicine, String> categoryCol =
        new TableColumn<>("Category");
    categoryCol.setCellValueFactory(
        new PropertyValueFactory<>("category"));

    TableColumn<Medicine, Integer> quantityCol =
        new TableColumn<>("Quantity");
    quantityCol.setCellValueFactory(
        new PropertyValueFactory<>("quantity"));

    TableColumn<Medicine, LocalDate> expiryCol =
        new TableColumn<>("Expiry Date");
    expiryCol.setCellValueFactory(
        new PropertyValueFactory<>("expiryDate"));

    table.getColumns().addAll(
        idCol, nameCol, categoryCol,
        quantityCol, expiryCol
    );

    // Search while typing
    searchField.textProperty().addListener(
        (observable, oldValue, newValue) -> {

            String keyword = newValue.trim().toLowerCase();

            filteredList.setPredicate(medicine -> {

                return keyword.isEmpty()
                    || medicine.getName().toLowerCase()
                        .contains(keyword)
                    || medicine.getCategory().toLowerCase()
                        .contains(keyword);
            });
        }
    );

    page.getChildren().addAll(
        heading,
        new Label("Search by medicine name or category"),
        searchField,
        table
    );

    return page;
}
private VBox createExpiryPage() {

    VBox page = new VBox(15);
    page.setPadding(new Insets(25));

    Label heading = new Label("Expiry Management");
    heading.setFont(Font.font("Arial", 26));

    // Near expiry table
    Label nearLabel = new Label("Near Expiry Medicines (Next 30 Days)");
    nearLabel.setFont(Font.font("Arial", 18));

    TableView<Medicine> nearTable = new TableView<>();

    // Expired medicines table
    Label expiredLabel = new Label("Expired Medicines");
    expiredLabel.setFont(Font.font("Arial", 18));

    TableView<Medicine> expiredTable = new TableView<>();

    // Columns for both tables
    TableColumn<Medicine, String> idCol1 =
        new TableColumn<>("ID");
    idCol1.setCellValueFactory(
        new PropertyValueFactory<>("id"));

    TableColumn<Medicine, String> nameCol1 =
        new TableColumn<>("Name");
    nameCol1.setCellValueFactory(
        new PropertyValueFactory<>("name"));

    TableColumn<Medicine, Integer> quantityCol1 =
        new TableColumn<>("Quantity");
    quantityCol1.setCellValueFactory(
        new PropertyValueFactory<>("quantity"));

    TableColumn<Medicine, LocalDate> expiryCol1 =
        new TableColumn<>("Expiry Date");
    expiryCol1.setCellValueFactory(
        new PropertyValueFactory<>("expiryDate"));

    TableColumn<Medicine, String> idCol2 =
        new TableColumn<>("ID");
    idCol2.setCellValueFactory(
        new PropertyValueFactory<>("id"));

    TableColumn<Medicine, String> nameCol2 =
        new TableColumn<>("Name");
    nameCol2.setCellValueFactory(
        new PropertyValueFactory<>("name"));

    TableColumn<Medicine, Integer> quantityCol2 =
        new TableColumn<>("Quantity");
    quantityCol2.setCellValueFactory(
        new PropertyValueFactory<>("quantity"));

    TableColumn<Medicine, LocalDate> expiryCol2 =
        new TableColumn<>("Expiry Date");
    expiryCol2.setCellValueFactory(
        new PropertyValueFactory<>("expiryDate"));

    nearTable.getColumns().addAll(
        idCol1, nameCol1, quantityCol1, expiryCol1
    );

    expiredTable.getColumns().addAll(
        idCol2, nameCol2, quantityCol2, expiryCol2
    );

    // Connect both tables to Medicine Management data
    ObservableList<Medicine> nearList =
        FXCollections.observableArrayList();

    ObservableList<Medicine> expiredList =
        FXCollections.observableArrayList();

    LocalDate today = LocalDate.now();

    for (Medicine medicine : medicineList) {

        LocalDate expiry = medicine.getExpiryDate();

        if (expiry.isBefore(today)) {
            expiredList.add(medicine);
        }
        else if (!expiry.isAfter(today.plusDays(30))) {
            nearList.add(medicine);
        }
    }

    nearTable.setItems(nearList);
    expiredTable.setItems(expiredList);

    page.getChildren().addAll(
        heading,
        nearLabel,
        nearTable,
        expiredLabel,
        expiredTable
    );

    return page;
}
private VBox createBillingPage() {

    VBox page = new VBox(15);
    page.setPadding(new Insets(25));

    Label heading = new Label("Customer Billing");
    heading.setFont(Font.font("Arial", 26));

    // Medicine selection
    ComboBox<Medicine> medicineBox = new ComboBox<>();
    medicineBox.setItems(medicineList);
    medicineBox.setPromptText("Select Medicine");
    medicineBox.setMaxWidth(300);

    medicineBox.setConverter(new StringConverter<Medicine>() {

        @Override
        public String toString(Medicine medicine) {
            return medicine == null ? "" : medicine.getName();
        }

        @Override
        public Medicine fromString(String value) {
            return null;
        }
    });

    // Quantity input
    TextField quantityField = new TextField();
    quantityField.setPromptText("Enter Quantity");
    quantityField.setMaxWidth(300);

    // Cart stores selected medicines and their quantities
    ObservableList<Medicine> cart =
        FXCollections.observableArrayList();

    Map<Medicine, Integer> cartQuantities =
        new LinkedHashMap<>();

    // Cart table
    TableView<Medicine> cartTable = new TableView<>();
    cartTable.setItems(cart);

    TableColumn<Medicine, String> nameCol =
        new TableColumn<>("Medicine");

    nameCol.setCellValueFactory(
        new PropertyValueFactory<>("name"));

    TableColumn<Medicine, Double> priceCol =
        new TableColumn<>("Price");

    priceCol.setCellValueFactory(
        new PropertyValueFactory<>("price"));

    TableColumn<Medicine, Integer> quantityCol =
        new TableColumn<>("Quantity");

    quantityCol.setCellValueFactory(data ->
        new ReadOnlyObjectWrapper<>(
            cartQuantities.get(data.getValue())
        )
    );

    TableColumn<Medicine, Double> subtotalCol =
        new TableColumn<>("Subtotal");

    subtotalCol.setCellValueFactory(data -> {

        Medicine medicine = data.getValue();

        double subtotal =
            medicine.getPrice()
            * cartQuantities.get(medicine);

        return new ReadOnlyObjectWrapper<>(subtotal);
    });

    cartTable.getColumns().addAll(
        nameCol, priceCol, quantityCol, subtotalCol
    );

    // Total amount
    Label totalLabel = new Label("Total Amount: ₹0.0");
    totalLabel.setFont(Font.font("Arial", 20));

    // Update cart total
    Runnable updateCart = () -> {

        double total = 0;

        for (Medicine medicine : cart) {

            total += medicine.getPrice()
                    * cartQuantities.get(medicine);
        }

        cartTable.refresh();

        totalLabel.setText("Total Amount: ₹" + total);
    };

    // Add item button
    Button addItemButton = new Button("Add Item");

    addItemButton.setOnAction(e -> {

        Medicine selected = medicineBox.getValue();

        if (selected == null) {
            showMessage("Please select a medicine.");
            return;
        }

        try {

            int quantity = Integer.parseInt(
                quantityField.getText().trim()
            );

            if (quantity <= 0) {
                showMessage("Enter a valid quantity.");
                return;
            }

            if (selected.getExpiryDate().isBefore(LocalDate.now())) {
                showMessage("Expired medicine cannot be added.");
                return;
            }

            int alreadyAdded =
                cartQuantities.getOrDefault(selected, 0);

            if (alreadyAdded + quantity > selected.getQuantity()) {
                showMessage("Insufficient stock available.");
                return;
            }

            // Add medicine or increase its cart quantity
            if (!cartQuantities.containsKey(selected)) {
                cart.add(selected);
                cartQuantities.put(selected, quantity);
            }
            else {
                cartQuantities.put(
                    selected,
                    alreadyAdded + quantity
                );
            }

            updateCart.run();

            quantityField.clear();
            medicineBox.setValue(null);

        } catch (NumberFormatException ex) {

            showMessage("Enter a valid whole number.");
        }
    });

    // Remove selected item
    Button removeButton = new Button("Remove Selected Item");

    removeButton.setOnAction(e -> {

        Medicine selected = cartTable.getSelectionModel()
                                     .getSelectedItem();

        if (selected == null) {
            showMessage("Please select an item to remove.");
            return;
        }

        cart.remove(selected);
        cartQuantities.remove(selected);

        updateCart.run();
    });

    // Complete bill button
    Button completeBillButton = new Button("Complete Bill");

    completeBillButton.setStyle(
        "-fx-background-color: #192D4B;" +
        "-fx-text-fill: white;" +
        "-fx-padding: 10 20;"
    );

    completeBillButton.setOnAction(e -> {

        if (cart.isEmpty()) {
            showMessage("Please add medicines to the bill.");
            return;
        }

        // Final stock validation before completing sale
        for (Medicine medicine : cart) {

            int quantity = cartQuantities.get(medicine);

            if (quantity > medicine.getQuantity()) {
                showMessage("Stock changed. Please check the quantity.");
                return;
            }

            if (medicine.getExpiryDate().isBefore(LocalDate.now())) {
                showMessage("Expired medicine cannot be sold.");
                return;
            }
        }

        double total = 0;
        StringBuilder billDetails = new StringBuilder();

        // Calculate bill and reduce stock
        for (Medicine medicine : cart) {

            int quantity = cartQuantities.get(medicine);

            double subtotal =
                medicine.getPrice() * quantity;

            total += subtotal;

            billDetails.append(
                medicine.getName()
                + " - Quantity: " + quantity
                + " - Amount: ₹" + subtotal
                + "\n"
            );

            medicine.setQuantity(
                medicine.getQuantity() - quantity
            );
        }
        // Save updated medicine stock
PharmacyFileManager.saveMedicines(medicineList);

// Generate unique Bill ID
String billId = "BILL-" + System.currentTimeMillis();

// Get current date and time
LocalDateTime billDateTime = LocalDateTime.now();

// Create Bill object
Bill bill = new Bill(
    billId,
    billDateTime,
    billDetails.toString(),
    total
);

// Save bill into file
BillingHistoryFileManager.saveBill(bill);

// Update Dashboard
updateDashboard();
        showMessage(
            "Bill Generated Successfully!\n\n"
            + billDetails
            + "\nTotal Amount: ₹" + total
        );

        // Clear cart after successful sale
        cart.clear();
        cartQuantities.clear();

        updateCart.run();
    });

    page.getChildren().addAll(
        heading,
        new Label("Select Medicine:"),
        medicineBox,
        new Label("Enter Quantity:"),
        quantityField,
        addItemButton,
        new Label("Shopping Cart"),
        cartTable,
        removeButton,
        totalLabel,
        completeBillButton
    );

    return page;
}
private VBox createReportsPage() {

    VBox page = new VBox(20);
    page.setPadding(new Insets(30));

    Label heading = new Label("Stock Reports");
    heading.setFont(Font.font("Arial", 26));

    int totalMedicines = medicineList.size();
    int totalStock = 0;
    int lowStock = 0;
    double stockValue = 0;

    for (Medicine medicine : medicineList) {

        totalStock += medicine.getQuantity();

        if (medicine.getQuantity() <= 10) {
            lowStock++;
        }

        stockValue += medicine.getPrice()
                * medicine.getQuantity();
    }

    Label totalLabel = new Label(
        "Total Medicines: " + totalMedicines
    );

    Label stockLabel = new Label(
        "Available Stock: " + totalStock
    );

    Label lowStockLabel = new Label(
        "Low Stock Medicines: " + lowStock
    );

    Label valueLabel = new Label(
        "Total Stock Value: ₹" + stockValue
    );

    // Medicine stock table
    TableView<Medicine> table = new TableView<>();
    table.setItems(medicineList);

    TableColumn<Medicine, String> nameCol =
        new TableColumn<>("Medicine Name");
    nameCol.setCellValueFactory(
        new PropertyValueFactory<>("name"));

    TableColumn<Medicine, Integer> quantityCol =
        new TableColumn<>("Quantity");
    quantityCol.setCellValueFactory(
        new PropertyValueFactory<>("quantity"));

    TableColumn<Medicine, Double> priceCol =
        new TableColumn<>("Price");
    priceCol.setCellValueFactory(
        new PropertyValueFactory<>("price"));

    table.getColumns().addAll(
        nameCol, quantityCol, priceCol
    );

    page.getChildren().addAll(
        heading,
        totalLabel,
        stockLabel,
        lowStockLabel,
        valueLabel,
        new Label("Medicine Stock Details"),
        table
    );

    return page;
}

private VBox createBillingHistoryPage() {

    VBox page = new VBox(15);
    page.setPadding(new Insets(25));

    Label heading = new Label("Billing History");
    heading.setFont(Font.font("Arial", 26));

    Label description = new Label(
        "View all previously completed customer bills"
    );

    // Load saved bills from file
    ObservableList<Bill> billList =
        FXCollections.observableArrayList(
            BillingHistoryFileManager.loadBills()
        );

    // Billing history table
    TableView<Bill> table = new TableView<>();
    table.setItems(billList);

    TableColumn<Bill, String> idCol =
        new TableColumn<>("Bill ID");

    idCol.setCellValueFactory(
        new PropertyValueFactory<>("billId")
    );

    TableColumn<Bill, LocalDateTime> dateCol =
        new TableColumn<>("Date & Time");

    dateCol.setCellValueFactory(
        new PropertyValueFactory<>("billDateTime")
    );

    TableColumn<Bill, String> detailsCol =
        new TableColumn<>("Medicine Details");

    detailsCol.setCellValueFactory(
        new PropertyValueFactory<>("details")
    );

    TableColumn<Bill, Double> totalCol =
        new TableColumn<>("Total Amount");

    totalCol.setCellValueFactory(
        new PropertyValueFactory<>("total")
    );

    table.getColumns().addAll(
        idCol,
        dateCol,
        detailsCol,
        totalCol
    );

    // Refresh button
    Button refreshButton = new Button("Refresh History");

    refreshButton.setOnAction(e -> {

        billList.setAll(
            BillingHistoryFileManager.loadBills()
        );

        table.refresh();
    });

    page.getChildren().addAll(
        heading,
        description,
        refreshButton,
        table
    );

    return page;
}

}