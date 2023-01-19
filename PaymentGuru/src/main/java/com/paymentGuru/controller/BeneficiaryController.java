package com.paymentGuru.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paymentGuru.exception.BeneficiaryException;
import com.paymentGuru.exception.CustomerException;
import com.paymentGuru.model.Beneficiary;
import com.paymentGuru.service.BeneficiaryService;
@RestController
public class BeneficiaryController {
	@Autowired
	private BeneficiaryService beneficiaryService;

	@PostMapping("/customers/beneficiary")
	public ResponseEntity<Beneficiary> addBeneficiary(@RequestBody Beneficiary beneficiary,
			@RequestParam(required = false) String key) throws BeneficiaryException, CustomerException {
		Beneficiary savedBeneficiary = beneficiaryService.addBeneficiary(beneficiary, key);
		return new ResponseEntity<Beneficiary>(savedBeneficiary, HttpStatus.BAD_GATEWAY);

	}

	@DeleteMapping("customers/beneficiary")
	public ResponseEntity<Beneficiary> removeBeneficiaryHandler(@RequestBody Beneficiary beneficiary,
			@RequestParam(required = false) String key) throws CustomerException, BeneficiaryException {

		Beneficiary removedBeneficiary = beneficiaryService.deleteBeneficiary(beneficiary, key);
		return new ResponseEntity<Beneficiary>(removedBeneficiary, HttpStatus.OK);
	}

	@GetMapping("/customers/beneficiry")
	public ResponseEntity<List<Beneficiary>> viewBeneficiary(@RequestParam(required = false) String mobileNo,
			@RequestParam(required = false) String key) throws CustomerException, BeneficiaryException {
		List<Beneficiary> beneficiaries = beneficiaryService.viewBeneficiaries(mobileNo, key);
		return new ResponseEntity<List<Beneficiary>>(beneficiaries, HttpStatus.OK);

	}
}
