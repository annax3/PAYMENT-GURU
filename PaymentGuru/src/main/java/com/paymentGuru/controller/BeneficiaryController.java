package com.paymentGuru.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@PostMapping("/beneficiary/{uniqueId}")
	public ResponseEntity<Beneficiary> addBeneficiary(@RequestBody Beneficiary beneficiary,
			@PathVariable String uniqueId) throws BeneficiaryException, CustomerException {
		Beneficiary savedBeneficiary = beneficiaryService.addBeneficiary(beneficiary, uniqueId);
		return new ResponseEntity<Beneficiary>(savedBeneficiary, HttpStatus.BAD_GATEWAY);

	}

	@GetMapping("/beneficiary/{uniqueId}")
	public ResponseEntity<List<Beneficiary>> viewBeneficiary(@PathVariable String uniqueId)
			throws CustomerException, BeneficiaryException {
		List<Beneficiary> beneficiaries = beneficiaryService.viewBeneficiaries(uniqueId);
		return new ResponseEntity<List<Beneficiary>>(beneficiaries, HttpStatus.OK);

	}

	@DeleteMapping("/beneficiary/{uniqueId}/{bId}")
	public ResponseEntity<Beneficiary> removeBeneficiaryHandler(@PathVariable String uniqueId,
			@PathVariable Integer bId) throws CustomerException, BeneficiaryException {

		Beneficiary removedBeneficiary = beneficiaryService.deleteBeneficiary(uniqueId, bId);
		return new ResponseEntity<Beneficiary>(removedBeneficiary, HttpStatus.OK);
	}

}
