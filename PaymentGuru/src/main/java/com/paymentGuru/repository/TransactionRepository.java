package com.paymentGuru.repository;

import com.paymentGuru.model.Transaction;
import com.paymentGuru.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    List<Transaction> findByTransactionDateBetween(LocalDateTime from, LocalDateTime to);
    List<Transaction> findByTransactionType(String type);

    List<Transaction> findByWallet(Wallet wallet);
}
