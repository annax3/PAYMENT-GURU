package com.paymentGuru.controller;

import com.paymentGuru.model.Customer;
import com.paymentGuru.model.Wallet;
import com.paymentGuru.service.AccountServices;
import com.paymentGuru.service.CustomerService;
import com.paymentGuru.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {

	@Autowired
	private AccountServices accountServices;
	@Autowired
	private WalletService walletService;

	@GetMapping("/balance/{uniqueId}")
	public ResponseEntity<String> showBalance(@PathVariable String uniqueId) {
		String balance = walletService.showBalance(uniqueId);
		return new ResponseEntity<String>(balance, HttpStatus.OK);
	}

	@PostMapping("/addMoney/{BankId}/{amount}/{uniqueId}")
	public ResponseEntity<Wallet> addMoneytoWallet(@PathVariable Integer BankId, @PathVariable Long amount,
			@PathVariable String uniqueId) {
		Wallet wallet = walletService.addMoneytoWallet(BankId, amount, uniqueId);
		return new ResponseEntity<Wallet>(wallet, HttpStatus.OK);
	}

	@PostMapping("/bankTrf/{BankId}/{amount}/{uniqueId}")
	public ResponseEntity<Wallet> transferToBank(@PathVariable Integer BankId, @PathVariable Long amount,
			@PathVariable String uniqueId) {
		Wallet wallet = walletService.transferToBank(BankId, amount, uniqueId);
		return new ResponseEntity<Wallet>(wallet, HttpStatus.OK);
	}

	@PostMapping("/bankTrf/{sourceMobileNo}/{targetMobileNo}/{amount}/{uniqueId}")
	public ResponseEntity<Wallet> transferToBank(@PathVariable String sourceMobileNo,
			@PathVariable String targetMobileNo, @PathVariable Long amount, @PathVariable String uniqueId) {
		Wallet wallet = walletService.fundTransfer(sourceMobileNo, targetMobileNo, amount, uniqueId);
		return new ResponseEntity<Wallet>(wallet, HttpStatus.OK);
	}

}
