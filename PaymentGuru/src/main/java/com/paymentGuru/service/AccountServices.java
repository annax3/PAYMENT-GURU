package com.paymentGuru.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paymentGuru.model.BankAccount;
import com.paymentGuru.model.Customer;

@Service
public interface AccountServices {
	public Customer addAccount(BankAccount Account, String uniqueId);
	// stop adding same bank account

	public Customer deleteAccount(BankAccount Account, String uniqueId);

	public BankAccount ViewAccount(String accountNo, String uniqueId);
	
	// View All account (wallet wallet)
}
