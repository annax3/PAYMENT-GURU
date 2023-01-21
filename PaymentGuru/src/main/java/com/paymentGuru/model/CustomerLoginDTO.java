package com.paymentGuru.model;



import lombok.Data;

@Data
public class CustomerLoginDTO {
	private String mobileNumber;
	private String Password;
	
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	
	
}
