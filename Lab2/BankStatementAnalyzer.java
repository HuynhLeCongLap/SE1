import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class BankStatementAnalyzer {
    private List<Transaction> transactions;

    public BankStatementAnalyzer(String filePath) throws IOException {
        transactions = loadTransactions(filePath);
    }

    
    private List<Transaction> loadTransactions(String filePath) throws IOException {
        List<Transaction> transactions = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            try {
                Date date = sdf.parse(fields[0]);
                double amount = Double.parseDouble(fields[1]);
                String description = fields[2];
                transactions.add(new Transaction(date, amount, description));
            } catch (Exception e) {
                System.out.println("Error parsing line: " + line);
            }
        }
        reader.close();
        return transactions;
    }

   
    public double calculateProfitLoss() {
        return transactions.stream().mapToDouble(Transaction::getAmount).sum();
    }

  
    public long countTransactionsForMonth(String month) {
        return transactions.stream()
                .filter(t -> new SimpleDateFormat("MM-yyyy").format(t.getDate()).equals(month))
                .count();
    }

 
    public List<Transaction> getTopExpenses(int topN) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)  // Chỉ xét các khoản chi
                .sorted(Comparator.comparingDouble(Transaction::getAmount))
                .limit(topN)
                .collect(Collectors.toList());
    }

    
    public String getTopSpendingCategory() {
        Map<String, Double> categoryTotals = new HashMap<>();

        for (Transaction t : transactions) {
            if (t.getAmount() < 0) {  // Chỉ xem xét các khoản chi
                categoryTotals.merge(t.getDescription(), t.getAmount(), Double::sum);
            }
        }

        return categoryTotals.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Không có chi tiêu");
    }

    public static void main(String[] args) {
        try {
            BankStatementAnalyzer analyzer = new BankStatementAnalyzer("bank_statements.csv");

            
            double totalProfitLoss = analyzer.calculateProfitLoss();
            System.out.println("1.Total Profit/Loss: " + totalProfitLoss);
            System.out.println("Status: " + (totalProfitLoss >= 0 ? "Positive" : "Negative"));

            
            long februaryTransactions = analyzer.countTransactionsForMonth("02-2017");
            System.out.println("2.Transactions in February 2017: " + februaryTransactions);

           
            List<Transaction> topExpenses = analyzer.getTopExpenses(10);
            System.out.println("3.Top 10 Expenses:");
            topExpenses.forEach(System.out::println);

            
            String topCategory = analyzer.getTopSpendingCategory();
            System.out.println("4.Category with most spending:" + topCategory);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   public class Transaction {
    Date date;
    double amount;
    String description;

    public Transaction(Date date, double amount, String description) {
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }


        public String getDateString() {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            return sdf.format(date);
        }
    }
}