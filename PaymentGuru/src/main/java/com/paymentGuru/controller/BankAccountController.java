package com.paymentGuru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	
}
