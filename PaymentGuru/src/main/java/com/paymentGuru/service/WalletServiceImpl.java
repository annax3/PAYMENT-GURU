package com.paymentGuru.service;

import com.paymentGuru.exception.CustomerException;
import com.paymentGuru.exception.WalletException;
import com.paymentGuru.model.BankAccount;
import com.paymentGuru.model.Customer;
import com.paymentGuru.model.CustomerSession;
import com.paymentGuru.model.Wallet;
import com.paymentGuru.repository.CustomerDao;
import com.paymentGuru.repository.CustomerSessionDao;
import com.paymentGuru.repository.WalletDao;
import com.paymentGuru.repository.accountDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private accountDao aDao;

	@Autowired
	private WalletDao wDao;

	@Autowired
	private CustomerDao cDao;

	@Autowired
	private CustomerSessionDao csDao;

	@Autowired
	private CustomerService csSession;

	@Override
	public String showBalance(String uniqueId) {
		CustomerSession cSession = csDao.findByUniqueId(uniqueId);
		if (cSession != null) {
			Optional<Customer> opt = cDao.findById(cSession.getCustomerId());
			Customer customer = opt.get();
			return "customer Balance is : " + customer.getWallet().getBalance();

		} else {
			throw new CustomerException("User not logged in with this number!");
		}

	}

	@Override
	public Wallet addMoneytoWallet(Integer BankId, Long amount, String uniqueId) {

		CustomerSession cSession = csDao.findByUniqueId(uniqueId);
		if (cSession != null) {
			Optional<Customer> opt = cDao.findById(cSession.getCustomerId());
			Customer customer = opt.get();

			Optional<BankAccount> optbank = aDao.findById(BankId);
			BankAccount bank = optbank.get();

			// transaction
			Wallet wallet = customer.getWallet();

			if (bank.getBalance() >= amount) {

				long revisedwalletbalance = wallet.getBalance() + amount;
				long revisedBankBalance = bank.getBalance() - amount;

				bank.setBalance(revisedBankBalance);
				wallet.setBalance(revisedwalletbalance);

				cDao.save(customer);
				return customer.getWallet();

			} else {
				throw new CustomerException("Insufficient bank balance");
			}

		} else {
			throw new CustomerException("User not logged in!");
		}

	}

}
