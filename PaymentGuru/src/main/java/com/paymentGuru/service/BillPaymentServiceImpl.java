package com.paymentGuru.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymentGuru.exception.BillPaymentException;
import com.paymentGuru.exception.CustomerException;
import com.paymentGuru.exception.WalletException;
import com.paymentGuru.model.BillPayment;
import com.paymentGuru.model.Customer;
import com.paymentGuru.model.CustomerSession;
import com.paymentGuru.model.Transaction;
import com.paymentGuru.model.Wallet;
import com.paymentGuru.repository.BillPaymentDao;
import com.paymentGuru.repository.CustomerDao;
import com.paymentGuru.repository.CustomerSessionDao;
import com.paymentGuru.repository.TransactionDao;

@Service
public class BillPaymentServiceImpl implements BillPaymentService {
	@Autowired
	private BillPaymentDao bDao;

	@Autowired
	private CustomerSessionDao csDao;

	@Autowired
	private CustomerDao cDao;

	@Autowired
	private TransactionDao tDao;

	@Override
	public BillPayment addBillPayment(BillPayment bill, String uniqueId) throws WalletException {
		CustomerSession cSession = csDao.findByUniqueId(uniqueId);
		if (cSession != null) {
			Customer customer = cDao.findById(cSession.getCustomerId()).get();
			Wallet wallet = customer.getWallet();

			if (wallet.getBalance() >= bill.getAmount()) {
				long revisedBalance = wallet.getBalance() - bill.getAmount();
				wallet.setBalance(revisedBalance);
				cDao.save(customer);

				// add transaction
				Transaction transaction = new Transaction();
				transaction.setAmount(bill.getAmount());
				transaction.setDescription(bill.getBillDescription());
				transaction.setTransactionDate(LocalDateTime.now());
				transaction.setTransactionType("Payment");
				transaction.setWallet(wallet);
				tDao.save(transaction);
				bill.setWallet(wallet);
				bDao.save(bill);
				return bill;

			} else {
				throw new WalletException("Insufficient funds in wallet!");
			}

		} else {
			throw new CustomerException("User not logged in!");
		}

	}

	@Override
	public List<BillPayment> viewPaidBills(String uniqueId) throws BillPaymentException {
		CustomerSession cSession = csDao.findByUniqueId(uniqueId);
		if (cSession != null) {
			Customer customer = cDao.findById(cSession.getCustomerId()).get();
			Wallet wallet = customer.getWallet();
			List<BillPayment> bills = bDao.findByWallet(wallet);
			if (bills.size() != 0) {
				return bills;
			} else {
				throw new CustomerException("no bill found for this user");
			}
		} else {
			throw new CustomerException("User not logged in!");
		}
	}

	/*
	 * @Override public BillPayment viewBillPayment(String uid, Integer billId)
	 * throws BillPaymentException { // TODO Auto-generated method stub
	 * CustomerSession customerSession = csd.findByUniqueId(uid);
	 * 
	 * 
	 * Optional<Customer> Opcustomer =
	 * cdao.findById(customerSession.getCustomerId());
	 * 
	 * 
	 * Customer customer = Opcustomer.get() ; BillPayment payment = null;
	 * 
	 * for (BillPayment payment2 : customer.getWallet().getBillPayments()) { if
	 * (payment2.getBillId() == billId) { payment = payment2; break; } }
	 * 
	 * if (payment != null) { return payment; } else throw new
	 * BillPaymentException("invalid credentials");
	 * 
	 * 
	 * return null; }
	 */

	/*
	 * public BillPayment addBillPayment(BillPayment payment , String uid)throws
	 * WalletException{
	 * 
	 * 
	 * CustomerSession customerSession = csd.findByUniqueId(uid);
	 * 
	 * 
	 * Optional<Customer> Opcustomer =
	 * cdao.findById(customerSession.getCustomerId());
	 * 
	 * 
	 * Customer customer = Opcustomer.get() ; long updatedWalletbal =
	 * customer.getWallet().getBalance() - payment.getAmount();
	 * 
	 * if (updatedWalletbal < 0) { throw new
	 * WalletException("Insufficient balance!"); } else {
	 * customer.getWallet().setBalance(updatedWalletbal);
	 * payment.setWallet(customer.getWallet()); //Transaction transaction = new
	 * Transaction(); // transaction.setTransactionDate(LocalDateTime.now()); //
	 * transaction.setAmount(payment.getAmount()); //
	 * transaction.setDescription("Bill is paid"); //
	 * transaction.setTransactionType(payment.getBillType()); //
	 * ts.addTransaction(transaction, uid);
	 * 
	 * return bpd.save(payment);
	 * 
	 * //}
	 * 
	 * }
	 */

//	public BillPayment addBillPayment(BillPayment payment , String uid)throws WalletException{
//		
//		
//		
//		//Transaction transaction = new Transaction();
//	//	transaction.setTransactionDate(LocalDateTime.now());
//	//	transaction.setAmount(payment.getAmount());
//	//	transaction.setDescription("Bill is paid");
//	//	transaction.setTransactionType(payment.getBillType());
//	//	ts.addTransaction(transaction, uid);
//
//		return bpd.save(payment);
//
//	//}
//
//}

}
