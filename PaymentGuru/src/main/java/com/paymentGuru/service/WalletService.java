package com.paymentGuru.service;

import com.paymentGuru.model.Customer;
import com.paymentGuru.model.Wallet;

import java.math.BigDecimal;
import java.util.List;

public interface WalletService {
    public Customer showBalance(String mobileNo);
    public boolean fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount);
    public Customer depositAmount(Customer customer, BigDecimal amount);
    public List<Customer> getCustomers();
    public Customer addMoney(Wallet wallet, Double amount);
}
