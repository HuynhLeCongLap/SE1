package com.example.bankapp;

public interface TransactionParser {
    Transaction parse(String data);
}
