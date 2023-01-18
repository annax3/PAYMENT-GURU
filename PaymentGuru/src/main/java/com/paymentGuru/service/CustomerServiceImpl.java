package com.paymentGuru.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymentGuru.exception.CustomerException;
import com.paymentGuru.model.Customer;
import com.paymentGuru.model.CustomerLoginDTO;
import com.paymentGuru.model.CustomerSession;
import com.paymentGuru.repository.CustomerDao;
import com.paymentGuru.repository.CustomerSessionDao;

import net.bytebuddy.asm.Advice.Return;
import net.bytebuddy.utility.RandomString;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao cDao;

	@Autowired
	private CustomerSessionDao csDao;

	@Override
	public Customer createCustomer(Customer customer) {
		Customer existingCustomer = cDao.findByMobileNumber(customer.getMobileNumber());
		if (existingCustomer == null) {
			return cDao.save(customer);
		} else {
			throw new CustomerException("Customer already registered");
		}

	}

	@Override
	public String customerLogin(CustomerLoginDTO customerDto) {
		Customer customer = cDao.findByMobileNumberAndPassword(customerDto.getMobileNumber(),
				customerDto.getPassword());

		if (customer != null) {
			CustomerSession cs = new CustomerSession();
			cs.setCustomerId(customer.getCustomerId());
			cs.setTimeStamp(LocalDateTime.now());
			cs.setUniqueId(RandomString.make(8));

			CustomerSession cSession = csDao.save(cs);
			return cSession.toString();

		} else {
			throw new CustomerException("Wrong credentials");
		}

	}

	@Override
	public String customerLogout(String uniqueId) {
		CustomerSession cSession = csDao.findByUniqueId(uniqueId);
		if (cSession != null) {
			csDao.delete(cSession);
			return "Logged out !";
		} else {
			throw new CustomerException("User not logged in with this number!");
		}

	}

}
