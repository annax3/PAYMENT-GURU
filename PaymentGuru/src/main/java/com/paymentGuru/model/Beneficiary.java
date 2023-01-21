package com.paymentGuru.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Entity
@Data
public class Beneficiary {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bid;
	private String name;
	private String mobileNo;
	private String relationWithCustomer;
	@JsonIgnore
	@ManyToOne
	// (cascade = CascadeType.DETACH)
	@JoinColumn(name = "cusomerId")
	private Customer customer;

	// {"name":"RAWAT","mobileNo":"9999999999","relationWithCustomer":"Father"}

}