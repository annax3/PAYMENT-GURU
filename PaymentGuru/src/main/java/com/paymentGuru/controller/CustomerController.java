package com.paymentGuru.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.paymentGuru.model.Customer;
import com.paymentGuru.model.CustomerLoginDTO;
import com.paymentGuru.model.CustomerSession;
import com.paymentGuru.service.CustomerService;

@RestController
public class CustomerController {
	@Autowired
	private CustomerService cService;

	@PostMapping("/customer")
	public ResponseEntity<Customer> createCustomerHandler(@Valid @RequestBody Customer customer) {
		Customer newCustomer = cService.createCustomer(customer);
		return new ResponseEntity<Customer>(newCustomer, HttpStatus.ACCEPTED);
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginCustomerHandler(@Valid @RequestBody CustomerLoginDTO customerDTO) {

		return new ResponseEntity<String>(cService.customerLogin(customerDTO), HttpStatus.ACCEPTED);
	}

	@PatchMapping("/logout/{uniqueId}")
	public ResponseEntity<String> logoutCustomerHandler(@PathVariable String uniqueId) {

		return new ResponseEntity<String>(cService.customerLogout(uniqueId), HttpStatus.ACCEPTED);
	}

	@GetMapping("/customer/{uniqueId}")
	public ResponseEntity<Customer> viewCustomerDetailsHandler(@PathVariable("uniqueId") String uniqueId) {
		Customer newCustomer = cService.viewCustomerDetails(uniqueId);
		return new ResponseEntity<Customer>(newCustomer, HttpStatus.ACCEPTED);
	}

}
