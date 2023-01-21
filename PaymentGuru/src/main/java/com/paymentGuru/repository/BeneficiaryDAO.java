package com.paymentGuru.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymentGuru.model.Beneficiary;
import com.paymentGuru.model.Customer;

@Repository
public interface BeneficiaryDAO extends JpaRepository<Beneficiary, Integer> {
	List<Beneficiary> findByMobileNo(String mobileNo);

	List<Beneficiary> findByCustomer(Customer customer);

}
