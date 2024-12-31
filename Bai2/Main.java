package com.example.bankapp;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Đường dẫn tới tệp CSV
        String filePath = "D:\\Lap\\Bai2\\bank_statement.csv";
        
        CsvTransactionReader csvReader = new CsvTransactionReader();
        List<Transaction> transactions = csvReader.readTransactions(filePath);

        BankStatement statement = new BankStatement();
        if (transactions != null) {
            for (Transaction transaction : transactions) {
                statement.addTransaction(transaction);
            }

            // Tạo báo cáo
            Report report = new Report();
            report.generateReport(statement);
        } else {
            System.out.println("Failed to read transactions.");
        }
    }
}
