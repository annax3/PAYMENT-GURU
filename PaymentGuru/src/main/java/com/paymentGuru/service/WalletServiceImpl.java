package com.paymentGuru.service;

import com.paymentGuru.exception.WalletException;
import com.paymentGuru.model.Customer;
import com.paymentGuru.model.Wallet;
import com.paymentGuru.repository.CustomerDao;
import com.paymentGuru.repository.WalletDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class WalletServiceImpl implements WalletService{

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private WalletDao walletDao;

    @Autowired
    private CustomerService customerService;

    @Override
    public String showBalance(String mobileNo) {
        Customer customer = customerDao.findByMobileNumber(mobileNo);
        if(customer!=null){
        Wallet wallet=customer.getWallet();
        if(wallet!=null){
            return "customer Balance : "+wallet.getBalance();
        }else {
            throw new WalletException("No wallet found for customer with mobile number " + mobileNo);
        }
        }else {
            throw new WalletException("No customer found with mobile number " + mobileNo);
        }
    }

    @Override
    public boolean fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) {
        return false;
    }


    @Override
    public Customer depositAmount(Customer customer, BigDecimal amount) {
        return null;
    }

    @Override
    public List<Customer> getCustomers() {
        return null;
    }

    @Override
    public Customer addMoney(Wallet wallet, Double amount) {
        return null;
    }
}
