import java.time.LocalDate;

public class Medicine {

    private String id;
    private String name;
    private String category;
    private String manufacturer;
    private double price;
    private int quantity;
    private LocalDate expiryDate;

    public Medicine(String id, String name, String category,
                    String manufacturer, double price,
                    int quantity, LocalDate expiryDate) {

        this.id = id;
        this.name = name;
        this.category = category;
        this.manufacturer = manufacturer;
        this.price = price;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
    this.quantity = quantity;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }
}