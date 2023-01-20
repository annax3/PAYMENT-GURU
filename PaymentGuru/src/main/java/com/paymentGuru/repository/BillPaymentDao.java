package com.paymentGuru.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymentGuru.model.BillPayment;

public interface BillPaymentDao extends JpaRepository<BillPayment, Integer>{

}
