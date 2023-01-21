package com.paymentGuru.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymentGuru.exception.CustomerException;
import com.paymentGuru.model.BankAccount;
import com.paymentGuru.model.Customer;
import com.paymentGuru.model.CustomerSession;
import com.paymentGuru.model.Wallet;
import com.paymentGuru.repository.CustomerDao;
import com.paymentGuru.repository.WalletDao;
import com.paymentGuru.repository.accountDao;

@Service
public class AccountServiceImpl implements AccountServices {

	@Autowired
	private CustomerService csDao;

	@Autowired
	private CustomerDao cDao;

	@Autowired
	private accountDao bDao;

	@Autowired
	private WalletDao wDao;

	@Override
	public Customer addAccount(BankAccount Account, String uniqueId) {


		CustomerSession session = csDao.checkCustomerSession(uniqueId);

		if (session != null) {
			Optional<Customer> opt = cDao.findById(session.getId());
			Customer customer = opt.get();

			List<BankAccount> banks = customer.getWallet().getBanks();

			boolean flag = false;
			for (BankAccount bank : banks) {
				if (bank.getAccountNo().equals(Account.getAccountNo())) {
					flag = true;
				}
			}

			// return desired bank account
			if (!flag) {
				// associate wallet
				Account.setWallet(customer.getWallet());
				// add bank account
				customer.getWallet().getBanks().add(Account);
				cDao.save(customer);
				return customer;
			} else {
				throw new CustomerException("Bank Account alread exist");
			}

		} else {
			throw new CustomerException("Customer not logged in");
		}

	}

	@Override
	public Customer deleteAccount(Integer accountId, String uniqueId) {
		CustomerSession session = csDao.checkCustomerSession(uniqueId);

		if (session != null) {
			Optional<BankAccount> opt = bDao.findById(accountId);
			if (opt.isPresent()) {
				bDao.delete(opt.get());
				Optional<Customer> optc = cDao.findById(session.getId());
				Customer customer = optc.get();
				return customer;
			} else {
				throw new CustomerException("Wrong bank id");
			}

		} else {
			throw new CustomerException("Customer not logged in");
		}

	}

	@Override
	public BankAccount ViewAccount(String accountNo, String uniqueId) {
		CustomerSession session = csDao.checkCustomerSession(uniqueId);
		BankAccount bankAccount = null;

		if (session != null) {
			Optional<Customer> opt = cDao.findById(session.getId());
			Customer customer = opt.get();

			List<BankAccount> banks = customer.getWallet().getBanks();

			for (BankAccount bank : banks) {
				if (bank.getAccountNo().equals(accountNo)) {
					bankAccount = bank;
				}
			}

			// return desired bank account
			if (bankAccount != null) {
				return bankAccount;
			} else {
				throw new CustomerException("No bank account available with this account number");
			}

		} else {
			throw new CustomerException("Customer not logged in");
		}
	}

	@Override
	public List<BankAccount> ViewAllAccount(Integer walletId, String uniqueId) {
		CustomerSession session = csDao.checkCustomerSession(uniqueId);
		if (session != null) {
			Optional<Wallet> opt = wDao.findById(walletId);
			if (opt.isPresent()) {
				List<BankAccount> banks = opt.get().getBanks();
				return banks;
			} else {
				throw new CustomerException("Wronge wallet id");
			}
		} else {
			throw new CustomerException("Customer not logged in");
		}

	}

	@Override
	public Customer showBalance(String mobileNo) {
		Customer customer = cDao.findByMobileNumber(mobileNo);
		if (customer != null) {
			//prashant
			return customer;
		} else {
			throw new CustomerException("No customer found with mobile number " + mobileNo);
		}
	}

}
