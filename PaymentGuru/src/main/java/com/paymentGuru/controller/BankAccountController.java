package com.paymentGuru.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.paymentGuru.model.BankAccount;
import com.paymentGuru.model.Customer;
import com.paymentGuru.service.AccountServices;

@RestController
public class BankAccountController {

	@Autowired
	private AccountServices aServices;

	@PostMapping("/bankaccount/{uniqueId}")
	public ResponseEntity<Customer> addBankAccountHandler(@RequestBody BankAccount Account,
			@PathVariable String uniqueId) {
		Customer customer = aServices.addAccount(Account, uniqueId);

		return new ResponseEntity<Customer>(customer, HttpStatus.ACCEPTED);

	}

	@PatchMapping("/bankaccount/{accountId}/{uniqueId}")
	public ResponseEntity<Customer> ViewAccount(@PathVariable Integer accountId, @PathVariable String uniqueId) {
		Customer customer = aServices.deleteAccount(accountId, uniqueId);

		return new ResponseEntity<Customer>(customer, HttpStatus.ACCEPTED);

	}

	@GetMapping("/bankaccount/{accountNo}/{uniqueId}")
	public ResponseEntity<BankAccount> ViewAccount(@PathVariable String accountNo, @PathVariable String uniqueId) {
		BankAccount bankAccount = aServices.ViewAccount(accountNo, uniqueId);

		return new ResponseEntity<BankAccount>(bankAccount, HttpStatus.ACCEPTED);

	}

	@GetMapping("/bankaccounts/{walletId}/{uniqueId}")
	public ResponseEntity<List<BankAccount>> ViewAllAccount(@PathVariable Integer walletId,
			@PathVariable String uniqueId) {
		List<BankAccount> bankAccounts = aServices.ViewAllAccount(walletId, uniqueId);

		return new ResponseEntity<List<BankAccount>>(bankAccounts, HttpStatus.ACCEPTED);

	}
	
	////////////////

}
