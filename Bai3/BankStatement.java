import java.util.ArrayList;
import java.util.List;

class BankStatement {
    private List<Transaction> transactions;

    public BankStatement() {
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}

class Transaction {
    private int amount;
    private String type;

    public Transaction(int amount, String type) {
        this.amount = amount;
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }
}

class Report {
    private int endingBalance;
    private int totalDeposit;
    private int totalWithdraw;

    public Report(int endingBalance, int totalDeposit, int totalWithdraw) {
        this.endingBalance = endinglance;
        this.totalDeposit = totalDeposit;
        this.totalWithdraw = totalWithdraw;
    }

    public int getEndingBalance() {
        return endingBalance;
    }

    public int getTotalDeposit() {
        return totalDeposit;
    }

    public int getTotalWithdraw() {
        return totalWithdraw;
    }
}
