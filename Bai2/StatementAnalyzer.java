package com.example.bankapp;

public class StatementAnalyzer {
    public double calculateTotalAmount(BankStatement statement) {
        double total = 0;
        for (Transaction transaction : statement.getTransactions()) {
            total += transaction.getAmount();
        }
        return total;
    }
}
