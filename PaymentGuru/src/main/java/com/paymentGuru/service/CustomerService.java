package com.paymentGuru.service;

import org.springframework.stereotype.Service;

import com.paymentGuru.model.Customer;

@Service
public interface CustomerService {
	public Customer createCustomer(Customer customer);
}
