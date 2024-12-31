package com.example.bankapp;

import java.util.ArrayList;
import java.util.List;

public class BankStatement {
    private List<Transaction> transactions;

    public BankStatement() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
