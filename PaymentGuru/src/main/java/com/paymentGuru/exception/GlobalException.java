package com.paymentGuru.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<MyError> CustomerExceptionHandler(CustomerException ae, WebRequest req) {

		MyError err = new MyError();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ae.getMessage());
		err.setDetails(req.getDescription(false));

		return new ResponseEntity<MyError>(err, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyError> ValidationExceptionHandler(MethodArgumentNotValidException ae) {

		MyError err = new MyError();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage("Validation Failed");
		err.setDetails(ae.getBindingResult().getFieldError().getDefaultMessage());

		return new ResponseEntity<MyError>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyError> ExceptionHandler(Exception ae, WebRequest req) {

		MyError err = new MyError();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ae.getMessage());
		err.setDetails(req.getDescription(false));

		return new ResponseEntity<MyError>(err, HttpStatus.BAD_REQUEST);
	}
}
