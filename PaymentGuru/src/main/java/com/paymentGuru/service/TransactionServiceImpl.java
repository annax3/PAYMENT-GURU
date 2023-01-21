package com.paymentGuru.service;

import com.paymentGuru.exception.TransactionException;
import com.paymentGuru.model.Transaction;
import com.paymentGuru.model.Wallet;
import com.paymentGuru.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private WalletService walletService;

    @Override
    public Transaction addTransaction(Transaction trans) {
        Wallet wallet = trans.getWallet();
        if(wallet == null) {
            throw new TransactionException("Cannot add transaction without a wallet");
        }
        trans = transactionRepository.save(trans);
        walletService.updateWallet(wallet);
        return trans;
    }

    @Override
    public List<Transaction> viewAllTransaction(Wallet wallet) {
        List<Transaction> transList = transactionRepository.findByWallet(wallet);
        if(transList.isEmpty()) {
            throw new TransactionException("No transactions found for this wallet");
        }
        return transList;
    }

    @Override
    public List<Transaction> viewTransactionByDate(LocalDateTime from, LocalDateTime to) {
        List<Transaction> transList = transactionRepository.findByTransactionDateBetween(from, to);
        if(transList.isEmpty()) {
            throw new TransactionException("No transactions found for the specified date range");
        }
        return transList;
    }

    @Override
    public List<Transaction> viewAllTransactionByType(String type) {
        List<Transaction> transList = transactionRepository.findByTransactionType(type);
        if(transList.isEmpty()) {
            throw new TransactionException("No transactions found for the specified type");
        }
        return transList;
    }

}

