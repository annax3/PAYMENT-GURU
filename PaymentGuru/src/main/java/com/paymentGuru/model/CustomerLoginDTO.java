package com.paymentGuru.model;

import javax.persistence.Entity;

import lombok.Data;

@Data
public class CustomerLoginDTO {
	private String mobileNumber;
	private String Password;
}
