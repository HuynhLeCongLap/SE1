package com.example.bankapp;

public class Report {
    public void generateReport(BankStatement statement) {
        StatementAnalyzer analyzer = new StatementAnalyzer();
        double totalAmount = analyzer.calculateTotalAmount(statement);
        System.out.println("Total amount: " + totalAmount);
    }
}
