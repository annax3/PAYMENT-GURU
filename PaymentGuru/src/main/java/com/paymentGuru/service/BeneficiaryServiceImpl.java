package com.paymentGuru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymentGuru.exception.BeneficiaryException;
import com.paymentGuru.exception.CustomerException;
import com.paymentGuru.model.Beneficiary;
import com.paymentGuru.repository.CustomerDao;
import com.paymentGuru.repository.CustomerSessionDao;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {
	@Autowired
	private CustomerSessionDao sessionDAO;

	@Autowired
	private CustomerDao customerDAO;

	@Autowired
	private BeneficiaryDAO beneficiaryDAO;

	@Override
	public Beneficiary addBeneficiary(Beneficiary beneficiary, String key)
			throws BeneficiaryException, CustomerException {

		CurrentUserSession loggedInUser = sessionDAO.findByUuid(key);

		if (loggedInUser == null) {
			throw new CustomerException("Please provide a valid key to  add beneficiary...");
		}

		Customer existingCustomer = customerDAO.findById(beneficiary.getCustomer().getCid())
				.orElseThrow(() -> new CustomerException("customer not found..create account"));
		beneficiary.setCustomer(existingCustomer);
		return beneficiaryDAO.save(beneficiary);

	}

	@Override
	public Beneficiary deleteBeneficiary(Beneficiary beneficiary, String key)
			throws CustomerException, BeneficiaryException {
		CurrentUserSession loggedInUser = sessionDAO.findByUuid(key);

		if (loggedInUser == null) {
			throw new CustomerException("Please provide a valid key to  add beneficiary...");
		}

		Beneficiary existingBeneficiary = beneficiaryDAO.findById(beneficiary.getBid())
				.orElseThrow(() -> new BeneficiaryException("enter corrent beneficiary details"));

		if (existingBeneficiary.getCustomer().getCid() != loggedInUser.getUserId())
			throw new CustomerException("customer not found...");

		beneficiaryDAO.delete(existingBeneficiary);

		return existingBeneficiary;
	}

	@Override
	public List<Beneficiary> viewBeneficiaries(String mobileNo, String key)
			throws CustomerException, BeneficiaryException {

		CurrentUserSession loggedInUser = sessionDAO.findByUuid(key);

		if (loggedInUser == null) {
			throw new CustomerException("Please provide a valid key to  add beneficiary...");
		}

		List<Beneficiary> beneficiaries = beneficiaryDAO.findByMobileNo(mobileNo);
		if (beneficiaries.isEmpty())
			throw new BeneficiaryException("NO beneficiary found....");

		return beneficiaries;
	}
}
