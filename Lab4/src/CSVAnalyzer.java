import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CSVAnalyzer {
    public static List<Map<String, String>> parseCSV(String filePath) throws IOException {
        List<Map<String, String>> data = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String[] headers = reader.readLine().split(",");
        String line;
        while ((line = reader.readLine()) != null) {
            String[] values = line.split(",");
            Map<String, String> row = new HashMap<>();
            for (int i = 0; i < headers.length; i++) {
                row.put(headers[i], values[i]);
            }
            data.add(row);
        }
        reader.close();
        return data;
    }

    public static double calculateTotalAmount(List<Map<String, String>> data) {
        return data.stream()
                .mapToDouble(row -> Double.parseDouble(row.get("Amount")))
                .sum();
    }
    public static Map<String, Double> calculateTotalByMonth(List<Map<String, String>> data) throws Exception {
        Map<String, Double> monthlyTotals = new HashMap<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        for (Map<String, String> row : data) {
            String dateStr = row.get("Date");
            if (dateStr == null || dateStr.isEmpty()) continue;

            Date date = formatter.parse(dateStr);
            String month = new SimpleDateFormat("yyyy-MM").format(date);
            double amount = Double.parseDouble(row.get("Amount"));

            monthlyTotals.put(month, monthlyTotals.getOrDefault(month, 0.0) + amount);
        }

        return monthlyTotals;
    }
    public static Map<String, Double> calculateTotalByType(List<Map<String, String>> data) {
        Map<String, Double> typeTotals = new HashMap<>();

        for (Map<String, String> row : data) {
            String type = row.get("Transaction Type");
            double amount = Double.parseDouble(row.get("Amount"));

            typeTotals.put(type, typeTotals.getOrDefault(type, 0.0) + amount);
        }

        return typeTotals;
    }

    public static void main(String[] args) {
        String validFilePath = "./src/test.csv"; 
        String invalidFilePath = "./src/invalid.csv";

        try {
            System.out.println("Running analysis on valid CSV file...");
            List<Map<String, String>> validData = parseCSV(validFilePath);
            System.out.println("Data parsed successfully!");
            double totalAmount = calculateTotalAmount(validData);
            System.out.println("Total Transaction Amount: " + totalAmount);
            Map<String, Double> monthlyTotals = calculateTotalByMonth(validData);
            System.out.println("Total Transaction Amount by Month:");
            monthlyTotals.forEach((month, amount) -> System.out.println(month + ": " + amount));
            Map<String, Double> typeTotals = calculateTotalByType(validData);
            System.out.println("Total Transaction Amount by Type:");
            typeTotals.forEach((type, amount) -> System.out.println(type + ": " + amount));
            System.out.println("\nRunning analysis on invalid CSV file...");
            try {
                List<Map<String, String>> invalidData = parseCSV(invalidFilePath);
                double invalidTotalAmount = calculateTotalAmount(invalidData);
                System.out.println("Total Transaction Amount (Invalid File): " + invalidTotalAmount);
            } catch (NumberFormatException e) {
                System.err.println("Error processing invalid data: " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error processing data: " + e.getMessage());
        }
    }
}
