package com.paymentGuru.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paymentGuru.model.BankAccount;
import com.paymentGuru.model.Customer;

@Service
public interface AccountServices {
	public Customer addAccount(BankAccount Account, String uniqueId);

	public Customer deleteAccount(Integer accountId, String uniqueId);

	public BankAccount ViewAccount(String accountNo, String uniqueId);

	public List<BankAccount> ViewAllAccount(Integer walletId, String uniqueId);

	//showBalance: Prashant Anand
	public Customer showBalance(String mobileNo);
}
