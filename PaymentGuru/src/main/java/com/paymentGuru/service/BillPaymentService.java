package com.paymentGuru.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paymentGuru.exception.BillPaymentException;
import com.paymentGuru.exception.WalletException;
import com.paymentGuru.model.BillPayment;

@Service
public interface BillPaymentService {
	// add bill -- pay bill -- add paid bills to database
	public BillPayment addBillPayment(BillPayment bill, String uuid) throws WalletException;

	public List<BillPayment> viewPaidBills(String uid) throws BillPaymentException;

}