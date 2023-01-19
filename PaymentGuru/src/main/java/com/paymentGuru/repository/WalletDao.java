package com.paymentGuru.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymentGuru.model.Wallet;

public interface WalletDao extends JpaRepository<Wallet, Integer> {

}
