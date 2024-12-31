public class Main {
    public static void main(String[] args) {
        BankStatement statement1 = new BankStatement();
        statement1.addTransaction(new Transaction(1000, "deposit"));
        statement1.addTransaction(new Transaction(-500, "withdraw"));
        statement1.addTransaction(new Transaction(200, "deposit"));

        Report report1 = analyse(statement1);

        System.out.println("Test 1:");
        System.out.println("Ending Balance: " + report1.getEndingBalance());
        System.out.println("Total Deposit: " + report1.getTotalDeposit());
        System.out.println("Total Withdraw: " + report1.getTotalWithdraw());

        BankStatement statement2 = new BankStatement();
        statement2.addTransaction(new Transaction(-1500, "withdraw"));
        statement2.addTransaction(new Transaction(500, "deposit"));
        statement2.addTransaction(new Transaction(-300, "withdraw"));

        Report report2 = analyse(statement2);

        System.out.println("Test 2:");
        System.out.println("Ending Balance: " + report2.getEndingBalance());
        System.out.println("Total Deposit: " + report2.getTotalDeposit());
        System.out.println("Total Withdraw: " + report2.getTotalWithdraw());
    }

    public static Report analyse(BankStatement statement) {
        // Implement your method logic here
        int endingBalance = 0;
        int totalDeposit = 0;
        int totalWithdraw = 0;

        for (Transaction transaction : statement.getTransactions()) {
            if (transaction.getType().equals("deposit")) {
                totalDeposit += transaction.getAmount();
            } else if (transaction.getType().equals("withdraw")) {
                totalWithdraw += transaction.getAmount();
            }
            endingBalance += transaction.getAmount();
        }

        return new Report(endingBalance, totalDeposit, totalWithdraw);
    }
}
