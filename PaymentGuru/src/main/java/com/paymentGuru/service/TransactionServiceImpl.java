package com.paymentGuru.service;

import com.paymentGuru.exception.CustomerException;
import com.paymentGuru.exception.TransactionException;
import com.paymentGuru.model.Customer;
import com.paymentGuru.model.CustomerSession;
import com.paymentGuru.model.Transaction;
import com.paymentGuru.model.Wallet;
import com.paymentGuru.repository.CustomerDao;
import com.paymentGuru.repository.CustomerSessionDao;
import com.paymentGuru.repository.TransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionDao tDao;

	@Autowired
	private WalletService walletService;

	@Autowired
	private CustomerSessionDao csDao;

	@Autowired
	private CustomerDao cDao;

	@Override
	public Transaction addTransaction12(Transaction trans, String uniqueId) {
		CustomerSession cSession = csDao.findByUniqueId(uniqueId);
		if (cSession != null) {
			Wallet wallet = cDao.findById(cSession.getCustomerId()).get().getWallet();

			// associate
			trans.setWallet(wallet);
			return tDao.save(trans);

		} else {
			throw new CustomerException("User not logged in!");
		}

	}

	@Override
	public List<Transaction> viewAllTransaction(String uniqueId) {

		CustomerSession cSession = csDao.findByUniqueId(uniqueId);
		if (cSession != null) {
			Wallet wallet = cDao.findById(cSession.getCustomerId()).get().getWallet();

			List<Transaction> transactios = tDao.findByWallet(wallet);
			if (transactios.size() == 0) {
				throw new CustomerException("no transactio found");
			} else {
				return transactios;
			}

		} else {
			throw new CustomerException("User not logged in!");
		}

	}

//
//    @Override
//    public List<Transaction> viewTransactionByDate(LocalDateTime from, LocalDateTime to) {
//        List<Transaction> transList = transactionRepository.findByTransactionDateBetween(from, to);
//        if(transList.isEmpty()) {
//            throw new TransactionException("No transactions found for the specified date range");
//        }
//        return transList;
//    }
//
	@Override
	public List<Transaction> viewAllTransactionByType(String type, String uniqueId) {
		CustomerSession cSession = csDao.findByUniqueId(uniqueId);
		if (cSession != null) {

			List<Transaction> transactios = tDao.findByTransactionType(type);
			if (transactios.size() == 0) {
				throw new CustomerException("no transactio found with this type!");
			} else {
				return transactios;
			}

		} else {
			throw new CustomerException("User not logged in!");
		}

	}

}
