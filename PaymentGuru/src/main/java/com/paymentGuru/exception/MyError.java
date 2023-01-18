package com.paymentGuru.exception;

import java.time.LocalDateTime;

import com.paymentGuru.model.Wallet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MyError {
	private LocalDateTime timeStamp;
	private String message;
	private String details;
}
