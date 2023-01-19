package com.paymentGuru.service;

import java.util.List;

import com.paymentGuru.exception.BeneficiaryException;
import com.paymentGuru.exception.CustomerException;
import com.paymentGuru.model.Beneficiary;

public interface BeneficiaryService {
	public Beneficiary addBeneficiary(Beneficiary beneficiary, String key)
			throws BeneficiaryException, CustomerException;

	public Beneficiary deleteBeneficiary(Beneficiary beneficiary, String key)
			throws CustomerException, BeneficiaryException;

	public List<Beneficiary> viewBeneficiaries(String mobileNo, String key)
			throws CustomerException, BeneficiaryException;
}
