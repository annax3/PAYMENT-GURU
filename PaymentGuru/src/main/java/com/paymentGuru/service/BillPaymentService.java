package com.paymentGuru.service;

import org.springframework.stereotype.Service;

import com.paymentGuru.exception.BillPaymentException;
import com.paymentGuru.exception.WalletException;
import com.paymentGuru.model.BillPayment;

@Service
public interface BillPaymentService {
	
	public BillPayment addBillPayment(BillPayment payment, String uuid) throws WalletException ;

	
	//public BillPayment viewBillPayment(String uid, Integer billId) throws BillPaymentException ;
	
	
}