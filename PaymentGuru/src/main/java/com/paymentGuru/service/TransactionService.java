package com.paymentGuru.service;

import com.paymentGuru.model.Transaction;
import com.paymentGuru.model.Wallet;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {
	public Transaction addTransaction12(Transaction trans, String uniqueId);

	// {"transactionType":"Payment","LocalDateTime":"1999-06-21","amount":"10000"
	// ,"description":"wallet transfer"}

	public List<Transaction> viewAllTransaction(String uniqueId);

	// public List<Transaction> viewTransactionByDate(LocalDateTime from,
	// LocalDateTime to);

	public List<Transaction> viewAllTransactionByType(String type, String uniqueId);
}
