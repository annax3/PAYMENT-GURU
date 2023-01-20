package com.paymentGuru.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymentGuru.exception.BillPaymentException;
import com.paymentGuru.exception.WalletException;
import com.paymentGuru.model.BillPayment;
import com.paymentGuru.model.Customer;
import com.paymentGuru.model.CustomerSession;
import com.paymentGuru.repository.BillPaymentDao;
import com.paymentGuru.repository.CustomerDao;
import com.paymentGuru.repository.CustomerSessionDao;

@Service
public class BillPaymentServiceImpl implements BillPaymentService{
	@Autowired
	private BillPaymentDao bpd;
	
	@Autowired
	private CustomerSessionDao csd;
	
	@Autowired
	private CustomerDao cdao;
	
	

	@Override
	public BillPayment viewBillPayment(String uid, Integer billId) throws BillPaymentException {
		// TODO Auto-generated method stub
		CustomerSession customerSession = csd.findByUniqueId(uid);
		

		Optional<Customer> Opcustomer = cdao.findById(customerSession.getCustomerId());					
				
		
			Customer customer = Opcustomer.get()  ;
		BillPayment payment = null;

		for (BillPayment payment2 : customer.getWallet().getBillPayments()) {
			if (payment2.getBillId() == billId) {
				payment = payment2;
				break;
			}
		}

		if (payment != null) {
			return payment;
		} else
			throw new BillPaymentException("Bill Details not Found");
		
		
		return null;
	}
	
	
	
	
	public BillPayment addBillPayment(BillPayment payment , String uid) {
		
		
			CustomerSession customerSession = csd.findByUniqueId(uid);
			

			Optional<Customer> Opcustomer = cdao.findById(customerSession.getCustomerId());					
					
			
				Customer customer = Opcustomer.get()  ;
		long updatedWalletbal = customer.getWallet().getBalance() - payment.getAmount();
		
		if (updatedWalletbal < 0) {
			throw new WalletException("Insufficient balance!");
	}
	else {
			customer.getWallet().setBalance(updatedWalletbal);
			payment.setWallet(customer.getWallet());
			//Transaction transaction = new Transaction();
		//	transaction.setTransactionDate(LocalDateTime.now());
		//	transaction.setAmount(payment.getAmount());
		//	transaction.setDescription("Bill is paid");
		//	transaction.setTransactionType(payment.getBillType());
		//	ts.addTransaction(transaction, uid);

			return bpd.save(payment);

		//}
	
	}

}



