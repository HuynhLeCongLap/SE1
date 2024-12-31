package com.example.bankapp;

public class Transaction {
    private String id;
    private double amount;
    private String date;

    public Transaction() {
        // No-arg constructor
    }

    public Transaction(String id, double amount, String date) {
        this.id = id;
        this.amount = amount;
        this.date = date;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}
