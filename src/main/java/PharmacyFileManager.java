
import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

public class PharmacyFileManager {

    private static final String FILE_NAME = "pharmacy_data/medicines.txt";

    // Save all medicines into the file
    public static void saveMedicines(List<Medicine> medicines) {

        try {
            Path path = Paths.get(FILE_NAME);

            Files.createDirectories(path.getParent());

            BufferedWriter writer = Files.newBufferedWriter(path);

            for (Medicine m : medicines) {

                writer.write(
                    m.getId() + "|" +
                    m.getName() + "|" +
                    m.getCategory() + "|" +
                    m.getManufacturer() + "|" +
                    m.getPrice() + "|" +
                    m.getQuantity() + "|" +
                    m.getExpiryDate()
                );

                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving medicines: " + e.getMessage());
        }
    }

    // Load medicines from the file
    public static List<Medicine> loadMedicines() {

        List<Medicine> medicines = new ArrayList<>();

        Path path = Paths.get(FILE_NAME);

        if (!Files.exists(path)) {
            return medicines;
        }

        try {
            BufferedReader reader = Files.newBufferedReader(path);

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|", -1);

                if (data.length == 7) {

                    Medicine medicine = new Medicine(
                        data[0],
                        data[1],
                        data[2],
                        data[3],
                        Double.parseDouble(data[4]),
                        Integer.parseInt(data[5]),
                        LocalDate.parse(data[6])
                    );

                    medicines.add(medicine);
                }
            }

            reader.close();

        } catch (Exception e) {
            System.out.println("Error loading medicines: " + e.getMessage());
        }

        return medicines;
    }
}
