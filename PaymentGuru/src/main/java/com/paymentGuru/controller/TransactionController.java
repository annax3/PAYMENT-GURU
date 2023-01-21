package com.paymentGuru.controller;

import com.paymentGuru.model.Transaction;
import com.paymentGuru.model.Wallet;
import com.paymentGuru.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
    @RequestMapping("/transaction")
    public class TransactionController {
    @Autowired
    private TransactionService tService;

    @PostMapping("/addTransaction")
    public Transaction addTransaction(@RequestBody Transaction trans) {
        return tService.addTransaction(trans);
    }

    @PostMapping("/viewAllTransaction")
    public List<Transaction> viewAllTransaction(@RequestBody Wallet wallet) {
        return tService.viewAllTransaction(wallet);
    }

    @PostMapping("/viewTransactionByDate")
    public List<Transaction> viewTransactionByDate(@RequestBody LocalDateTime from, LocalDateTime to) {
        return tService.viewTransactionByDate(from, to);
    }

    @PostMapping("/viewAllTransactionByType")
    public List<Transaction> viewAllTransactionByType(@RequestBody String type) {
        return tService.viewAllTransactionByType(type);
    }
}
