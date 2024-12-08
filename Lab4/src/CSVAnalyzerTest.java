import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class CSVAnalyzerTest {

	public static List<Map<String, String>> parseCSV(String filePath) throws IOException {
	    List<Map<String, String>> data = new ArrayList<>();
	    BufferedReader reader = new BufferedReader(new FileReader(filePath));
	    String[] headers = reader.readLine().split(",");
	    String line;

	    while ((line = reader.readLine()) != null) {
	        if (line.trim().isEmpty()) continue;

	        String[] values = line.split(",");
	        if (values.length != headers.length) {
	            System.err.println("Invalid row: " + line);
	            continue; 
	        }

	        Map<String, String> row = new HashMap<>();
	        for (int i = 0; i < headers.length; i++) {
	            row.put(headers[i], values[i].trim());
	        }
	        data.add(row);
	    }
	    reader.close();
	    return data;
	}
    public void testAnalyzeCSV() throws IOException {
        String filePath = "./src/test.csv"; 
        List<Map<String, String>> data = parseCSV(filePath);

        assertNotNull(data);
        assertFalse(data.isEmpty());
        assertEquals(5, data.size()); 
    }
    public void testCalculateTotalAmount() throws IOException {
        String filePath = "./src/test.csv";
        List<Map<String, String>> data = parseCSV(filePath);

        double totalAmount = data.stream()
                .mapToDouble(row -> Double.parseDouble(row.get("Amount")))
                .sum();

        assertEquals(1000.0, totalAmount); 
    }
    public void testCalculateTotalAmountByMonth() throws Exception {
        String filePath = "./src/test.csv"; // Provide valid path
        List<Map<String, String>> data = parseCSV(filePath);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Map<String, Double> monthlyTotals = new HashMap<>();
        for (Map<String, String> row : data) {
            Date date = formatter.parse(row.get("Date"));
            String month = new SimpleDateFormat("yyyy-MM").format(date);
            double amount = Double.parseDouble(row.get("Amount"));

            monthlyTotals.put(month, monthlyTotals.getOrDefault(month, 0.0) + amount);
        }

        assertTrue(monthlyTotals.containsKey("2023-01")); 
        assertEquals(500.0, monthlyTotals.get("2023-01")); 
    }
    public void testCalculateTotalAmountByType() throws IOException {
        String filePath = "./src/test.csv"; 
        List<Map<String, String>> data = parseCSV(filePath);

        Map<String, Double> typeTotals = new HashMap<>();
        for (Map<String, String> row : data) {
            String type = row.get("Transaction Type");
            double amount = Double.parseDouble(row.get("Amount"));

            typeTotals.put(type, typeTotals.getOrDefault(type, 0.0) + amount);
        }
        assertTrue(typeTotals.containsKey("Food")); 
        assertEquals(200.0, typeTotals.get("Food")); 
    }
    public void testHandleInvalidData() throws IOException {
        String filePath = "./src/invalid.csv"; 
        assertThrows(NumberFormatException.class, () -> {
            List<Map<String, String>> data = parseCSV(filePath);
            data.stream().mapToDouble(row -> Double.parseDouble(row.get("Amount"))).sum();
        });
    }
    
}
