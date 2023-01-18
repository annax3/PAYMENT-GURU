package com.paymentGuru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymentGuru.model.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {

}
