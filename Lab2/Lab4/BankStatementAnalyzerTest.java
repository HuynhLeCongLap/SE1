import org.junit.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BankStatementAnalyzerTest {

    @Test
    public void testCalculateTotalProfitLoss() {
        List<BankStatementAnalyzer.Transaction> transactions = new ArrayList<>();
        transactions.add(new BankStatementAnalyzer.Transaction(new Date(), 500.0, "Salary"));
        transactions.add(new BankStatementAnalyzer.Transaction(new Date(), -200.0, "Groceries"));
        transactions.add(new BankStatementAnalyzer.Transaction(new Date(), -100.0, "Rent"));

        double total = BankStatementAnalyzer.calculateTotalProfitLoss(transactions);
        assertEquals(200.0, total, 0.001); // Expecting total profit loss to be 200.0
    }

    @Test
    public void testCalculateTotalProfitLoss_NoTransactions() {
        List<BankStatementAnalyzer.Transaction> transactions = new ArrayList<>();
        double total = BankStatementAnalyzer.calculateTotalProfitLoss(transactions);
        assertEquals(0.0, total, 0.001); // Expecting total to be 0.0
    }

    @Test
    public void testCalculateTotalProfitLoss_AllLosses() {
        List<BankStatementAnalyzer.Transaction> transactions = new ArrayList<>();
        transactions.add(new BankStatementAnalyzer.Transaction(new Date(), -500.0, "Rent"));
        transactions.add(new BankStatementAnalyzer.Transaction(new Date(), -300.0, "Utilities"));

        double total = BankStatementAnalyzer.calculateTotalProfitLoss(transactions);
        assertEquals(-800.0, total, 0.001); // Expecting total profit loss to be -800.0
    }

    @Test
    public void testCalculateTotalProfitLoss_AllProfits() {
        List<BankStatementAnalyzer.Transaction> transactions = new ArrayList<>();
        transactions.add(new BankStatementAnalyzer.Transaction(new Date(), 500.0, "Salary"));
        transactions.add(new BankStatementAnalyzer.Transaction(new Date(), 300.0, "Freelance"));

        double total = BankStatementAnalyzer.calculateTotalProfitLoss(transactions);
        assertEquals(800.0, total, 0.001); // Expecting total profit loss to be 800.0
    }
}
