package com.paymentGuru.service;

import com.paymentGuru.model.Transaction;
import com.paymentGuru.model.Wallet;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {
    public Transaction addTransaction(Transaction trans);

    public List<Transaction> viewAllTransaction(Wallet wallet);

    public List<Transaction> viewTransactionByDate(LocalDateTime from, LocalDateTime to);

    public List<Transaction> viewAllTransactionByType(String type);
}
