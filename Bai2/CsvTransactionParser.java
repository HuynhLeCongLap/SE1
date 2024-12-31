package com.example.bankapp;

public class CsvTransactionParser implements TransactionParser {
    public Transaction parse(String data) {
        // Logic to parse CSV data and create a Transaction object
        String[] parts = data.split(",");
        return new Transaction(parts[0], Double.parseDouble(parts[1]), parts[2]);
    }
}
