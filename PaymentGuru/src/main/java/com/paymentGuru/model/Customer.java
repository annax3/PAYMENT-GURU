package com.paymentGuru.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;

	@NotBlank
	@NotEmpty
	@NotNull
	@Size(min = 3, max = 20, message = "Customer name should be min 3 and max 20 character length")
	private String name;
	@NotBlank
	@NotEmpty
	@NotNull
	@Size(min = 10, max = 10, message = "mobileNumber Should be 10 digit length")
	private String mobileNumber;
	@NotBlank
	@NotEmpty
	@NotNull
	@Size(min = 8, max = 15, message = "Password should be min 8 and max 15 character length")
	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	private Wallet wallet;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	
	
}

//{"name":"Sandeep","mobileNumber":"8860578503","wallet":{"balance":10000},"password":"123456789"}

//{
//"accountNo":"123456789",
//"ifscCode":"SBIN0788",
//"bankName":"SBI",
//"balance":"20000"
//}
