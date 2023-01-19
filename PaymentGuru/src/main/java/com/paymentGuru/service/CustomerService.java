package com.paymentGuru.service;

import org.springframework.stereotype.Service;

import com.paymentGuru.model.Customer;
import com.paymentGuru.model.CustomerLoginDTO;
import com.paymentGuru.model.CustomerSession;

@Service
public interface CustomerService {
	public Customer createCustomer(Customer customer);

	public String customerLogin(CustomerLoginDTO customerDto);

	public String customerLogout(String uniqueId);

	public CustomerSession checkCustomerSession(String UniqueId);
}
