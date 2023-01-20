package com.paymentGuru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymentGuru.model.CustomerSession;

@Repository
public interface CustomerSessionDao extends JpaRepository<CustomerSession, Integer> {
	public CustomerSession findByUniqueId(Integer UniqueId);
}
