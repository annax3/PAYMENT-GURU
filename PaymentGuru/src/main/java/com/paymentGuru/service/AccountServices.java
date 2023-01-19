package com.paymentGuru.service;

import org.springframework.stereotype.Service;

import com.paymentGuru.model.BankAccount;
import com.paymentGuru.model.Customer;

@Service
public interface AccountServices {
	public Customer addAccount(BankAccount Account, String uniqueId);
	public Customer deleteAccount(BankAccount Account, String uniqueId);
	
	// remove account (Bank Account)
	// View Account (Wallet)
	// View All account (wallet wallet)
}
