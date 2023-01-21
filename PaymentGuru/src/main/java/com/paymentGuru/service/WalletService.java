package com.paymentGuru.service;

import com.paymentGuru.model.Customer;
import com.paymentGuru.model.Wallet;

import java.math.BigDecimal;
import java.util.List;

public interface WalletService {

	public String showBalance(String uniqueId);

	public Wallet addMoneytoWallet(Integer BankId, Long amount, String uniqueId);

//    public boolean fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount);
//    public Customer depositAmount(Customer customer, BigDecimal amount);
//    public List<Customer> getCustomers();

}
