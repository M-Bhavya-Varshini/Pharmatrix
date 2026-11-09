
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Base64;

public class BillingHistoryFileManager {

    private static final String FILE_NAME =
        "pharmacy_data/billing_history.txt";

    // Save one completed bill
    public static void saveBill(Bill bill) {

        try {
            Path path = Paths.get(FILE_NAME);

            Files.createDirectories(path.getParent());

            String encodedDetails =
                Base64.getEncoder().encodeToString(
                    bill.getDetails().getBytes(StandardCharsets.UTF_8)
                );

            String record =
                bill.getBillId() + "|" +
                bill.getBillDateTime() + "|" +
                bill.getTotal() + "|" +
                encodedDetails;

            Files.writeString(
                path,
                record + System.lineSeparator(),
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
            );

        } catch (IOException e) {
            System.out.println(
                "Error saving bill: " + e.getMessage()
            );
        }
    }

    // Load all previous bills
    public static List<Bill> loadBills() {

        List<Bill> bills = new ArrayList<>();

        Path path = Paths.get(FILE_NAME);

        if (!Files.exists(path)) {
            return bills;
        }

        try (BufferedReader reader =
                 Files.newBufferedReader(path, StandardCharsets.UTF_8)) {

            String line;

            while ((line = reader.readLine()) != null) {

                try {
                    String[] data = line.split("\\|", 4);

                    if (data.length == 4) {

                        String details =
                            new String(
                                Base64.getDecoder().decode(data[3]),
                                StandardCharsets.UTF_8
                            );

                        Bill bill = new Bill(
                            data[0],
                            LocalDateTime.parse(data[1]),
                            details,
                            Double.parseDouble(data[2])
                        );

                        bills.add(bill);
                    }

                } catch (Exception e) {
                    System.out.println(
                        "Skipping invalid bill record."
                    );
                }
            }

        } catch (IOException e) {
            System.out.println(
                "Error loading bills: " + e.getMessage()
            );
        }

        return bills;
    }
}
