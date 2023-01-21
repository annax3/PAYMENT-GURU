package com.paymentGuru.controller;

import com.paymentGuru.model.Transaction;
import com.paymentGuru.model.Wallet;
import com.paymentGuru.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class TransactionController {

	@Autowired
	private TransactionService tService;

	@GetMapping("/viewAllTransaction/{uniqueId}")
	public ResponseEntity<List<Transaction>> viewAllTransaction(@PathVariable String uniqueId) {
		return new ResponseEntity<List<Transaction>>(tService.viewAllTransaction(uniqueId), HttpStatus.ACCEPTED);
	}

//
//	@PostMapping("/viewTransactionByDate")
//	public List<Transaction> viewTransactionByDate(@RequestBody LocalDateTime from, LocalDateTime to) {
//		return tService.viewTransactionByDate(from, to);
//	}
//
	@GetMapping("/viewAllTransactionType/{type}/{uniqueId}")
	public ResponseEntity<List<Transaction>> viewAllTransaction(@PathVariable String type,
			@PathVariable String uniqueId) {
		return new ResponseEntity<List<Transaction>>(tService.viewAllTransactionByType(type, uniqueId),
				HttpStatus.ACCEPTED);
	}

//	@PostMapping("/addTransaction/{uniqueId}")
//	public Transaction addTransaction(@RequestBody Transaction trans, @PathVariable String uniqueId) {
//		return tService.addTransaction12(trans, uniqueId);
//	}

}
