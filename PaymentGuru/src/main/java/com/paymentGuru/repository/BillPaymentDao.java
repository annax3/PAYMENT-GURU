package com.paymentGuru.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymentGuru.model.BillPayment;
import com.paymentGuru.model.Wallet;

public interface BillPaymentDao extends JpaRepository<BillPayment, Integer> {
	public List<BillPayment> findByWallet(Wallet wallet);
}
