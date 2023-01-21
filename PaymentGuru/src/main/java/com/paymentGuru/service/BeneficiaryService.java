package com.paymentGuru.service;

import java.util.List;

import com.paymentGuru.exception.BeneficiaryException;
import com.paymentGuru.exception.CustomerException;
import com.paymentGuru.model.Beneficiary;

public interface BeneficiaryService {
	public Beneficiary addBeneficiary(Beneficiary beneficiary, String UniqueID)
			throws BeneficiaryException, CustomerException;

	public List<Beneficiary> viewBeneficiaries(String UniqueID) throws CustomerException, BeneficiaryException;

	public Beneficiary deleteBeneficiary(String UniqueID, Integer bId) throws CustomerException, BeneficiaryException;

}
