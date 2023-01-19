package com.paymentGuru.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymentGuru.exception.CustomerException;
import com.paymentGuru.model.BankAccount;
import com.paymentGuru.model.Customer;
import com.paymentGuru.model.CustomerSession;
import com.paymentGuru.repository.CustomerDao;

@Service
public class AccountServiceImpl implements AccountServices {

	@Autowired
	private CustomerService csDao;

	@Autowired
	private CustomerDao cDao;

	@Override
	public Customer addAccount(BankAccount Account, String uniqueId) {
		CustomerSession session = csDao.checkCustomerSession(uniqueId);
		if (session != null) {
			Optional<Customer> opt = cDao.findById(session.getId());
			Customer customer = opt.get();

			// associate wallet
			Account.setWallet(customer.getWallet());
			// add bank account
			customer.getWallet().getBanks().add(Account);
			cDao.save(customer);
			return customer;

		} else {
			throw new CustomerException("Customer not logged in");
		}

	}

	@Override
	public Customer deleteAccount(BankAccount Account, String uniqueId) {
		CustomerSession session = csDao.checkCustomerSession(uniqueId);
		
		if (session != null) {
			Optional<Customer> opt = cDao.findById(session.getId());
			Customer customer = opt.get();
			
			List<BankAccount> banks =   customer.getWallet().getBanks();
			for(BankAccount bank :banks ) {
				if(bank.getAccountNo()==Account.getAccountNo()) {
					banks.remove(Account);
				}
			}
			
			return customer;

		} else {
			throw new CustomerException("Customer not logged in");
		}
		
		
	}

}
