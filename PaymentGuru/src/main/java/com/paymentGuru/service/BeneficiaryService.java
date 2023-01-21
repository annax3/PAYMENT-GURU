package com.paymentGuru.service;

import java.util.List;

import com.paymentGuru.exception.BeneficiaryException;
import com.paymentGuru.exception.CustomerException;
import com.paymentGuru.model.Beneficiary;

public interface BeneficiaryService {
	public Beneficiary addBeneficiary(Beneficiary beneficiary, Integer Id)
			throws BeneficiaryException, CustomerException;

	public Beneficiary deleteBeneficiary(Beneficiary beneficiary, Integer Id)
			throws CustomerException, BeneficiaryException;

	public List<Beneficiary> viewBeneficiaries(String mobileNo, Integer Id)
			throws CustomerException, BeneficiaryException;
}

	
