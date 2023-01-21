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

	@Autowired
	private CustomerSessionDao csDao;

	@Override
	public Beneficiary addBeneficiary(Beneficiary beneficiary, String UniqueID)
			throws BeneficiaryException, CustomerException {

		CustomerSession cSession = csDao.findByUniqueId(UniqueID);
		if (cSession != null) {
			Optional<Customer> opt = customerDAO.findById(cSession.getCustomerId());
			Customer existingCustomer = opt.get();
			beneficiary.setCustomer(existingCustomer);
			return beneficiaryDAO.save(beneficiary);
		}
		{
			throw new CustomerException("customer is not logged in with given sourceMobileNo");
		}

	}

	@Override
	public List<Beneficiary> viewBeneficiaries(String UniqueID) throws CustomerException, BeneficiaryException {
		CustomerSession cSession = csDao.findByUniqueId(UniqueID);
		if (cSession != null) {
			Optional<Customer> opt = customerDAO.findById(cSession.getCustomerId());
			Customer existingCustomer = opt.get();
			List<Beneficiary> beneficiaries = beneficiaryDAO.findByCustomer(existingCustomer);

			if (beneficiaries != null) {
				return beneficiaries;

			} else {
				throw new BeneficiaryException("No benebficiary yet");
			}

		}
		{
			throw new CustomerException("customer is not logged in with given sourceMobileNo");
		}

	}

	@Override
	public Beneficiary deleteBeneficiary(String UniqueID, Integer bId) throws CustomerException, BeneficiaryException {
		CustomerSession cSession = csDao.findByUniqueId(UniqueID);
		if (cSession != null) {
			Optional<Beneficiary> opt = beneficiaryDAO.findById(bId);
			if (opt.isPresent()) {
				Beneficiary existingBeneficiary = opt.get();
				beneficiaryDAO.delete(existingBeneficiary);
				return existingBeneficiary;
			} else {
				throw new BeneficiaryException("no beneficiary available with the given id");
			}

		}
		{
			throw new CustomerException("customer is not logged in with given sourceMobileNo");
		}

	}

}
