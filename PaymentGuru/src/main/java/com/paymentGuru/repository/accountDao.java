package com.paymentGuru.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymentGuru.model.BankAccount;

public interface accountDao extends JpaRepository<BankAccount, Integer> {
	
}
