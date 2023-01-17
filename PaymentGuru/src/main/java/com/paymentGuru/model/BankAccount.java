package com.paymentGuru.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BankAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer accountNo;
	private String ifscCode;
	private String bankName;
	private Long balance;

	@OneToOne(cascade = CascadeType.ALL)
	private Wallet wallet;
}