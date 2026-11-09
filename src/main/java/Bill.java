
import java.time.LocalDateTime;

public class Bill {

    private String billId;
    private LocalDateTime billDateTime;
    private String details;
    private double total;

    public Bill(String billId, LocalDateTime billDateTime,
                String details, double total) {

        this.billId = billId;
        this.billDateTime = billDateTime;
        this.details = details;
        this.total = total;
    }

    public String getBillId() {
        return billId;
    }

    public LocalDateTime getBillDateTime() {
        return billDateTime;
    }

    public String getDetails() {
        return details;
    }

    public double getTotal() {
        return total;
    }
}
