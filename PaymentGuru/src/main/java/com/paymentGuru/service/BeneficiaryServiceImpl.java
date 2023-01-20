package com.paymentGuru.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymentGuru.exception.BeneficiaryException;
import com.paymentGuru.exception.CustomerException;
import com.paymentGuru.model.Beneficiary;
import com.paymentGuru.model.Customer;
import com.paymentGuru.model.CustomerSession;
import com.paymentGuru.repository.BeneficiaryDAO;
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
	public Beneficiary addBeneficiary(Beneficiary beneficiary, Integer Id)
			throws BeneficiaryException, CustomerException {

		CustomerSession loggedInUser = sessionDAO.findByUniqueId(Id);

		if (loggedInUser == null) {
			throw new CustomerException("Please provide a valid key to  add beneficiary...");
		}

		Customer existingCustomer = customerDAO.findById(beneficiary.getCustomer().getcustomerId())
				.orElseThrow(() -> new CustomerException("customer not found..create account"));
		beneficiary.setCustomer(existingCustomer);
		return beneficiaryDAO.save(beneficiary);

	}

	@Override
	public Beneficiary deleteBeneficiary(Beneficiary beneficiary, Integer Id)
			throws CustomerException, BeneficiaryException {
		CustomerSession loggedInUser = sessionDAO.findByUniqueId(Id);

		if (loggedInUser == null) {
			throw new CustomerException("Please provide a valid key to  add beneficiary...");
		}

		Beneficiary existingBeneficiary = beneficiaryDAO.findById(beneficiary.getBid())
				.orElseThrow(() -> new BeneficiaryException("enter corrent beneficiary details"));

		if (existingBeneficiary.getCustomer().getcustomerId() != loggedInUser.getUserId())
			throw new CustomerException("customer not found...");

		beneficiaryDAO.delete(existingBeneficiary);

		return existingBeneficiary;
	}

	@Override
	public List<Beneficiary> viewBeneficiaries(String mobileNo, Integer Id)
			throws CustomerException, BeneficiaryException {

		CustomerSession loggedInUser = sessionDAO.findByUniqueId(Id);

		if (loggedInUser == null) {
			throw new CustomerException("Please provide a valid key to  add beneficiary...");
		}

		List<Beneficiary> beneficiaries = beneficiaryDAO.findByMobileNo(mobileNo);
		if (beneficiaries.isEmpty())
			throw new BeneficiaryException("NO beneficiary found....");

		return beneficiaries;
	}
}
