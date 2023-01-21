package com.paymentGuru.controller;

import com.paymentGuru.model.Customer;
import com.paymentGuru.service.AccountServices;
import com.paymentGuru.service.CustomerService;
import com.paymentGuru.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {

    @Autowired
    private AccountServices accountServices;
    @Autowired
   private WalletService walletService;


    @GetMapping("/balance/{mobileNo}")
    public ResponseEntity<String> showBalance(@PathVariable String mobileNo) {
        String balance = walletService.showBalance(mobileNo);
        return new ResponseEntity<String>(balance, HttpStatus.OK);
    }
}
