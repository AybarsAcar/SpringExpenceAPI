package com.aybarsacar.expensetrackerapi.services;

import com.aybarsacar.expensetrackerapi.domain.Transaction;
import com.aybarsacar.expensetrackerapi.exceptions.EtBadRequestException;
import com.aybarsacar.expensetrackerapi.exceptions.EtResourceNotFoundException;

import java.util.List;

public interface TransactionService {

  List<Transaction> fetchAllTransaction(Integer userId, Integer categoryId);

  Transaction fetchTransactionById(Integer userId, Integer categoryId, Integer transactionId)
      throws EtResourceNotFoundException;

  Transaction addTransaction(Integer userId, Integer categoryId, Double amount, String note, Long transactionDate)
      throws EtBadRequestException;

  void updateTransaction(Integer userId, Integer categoryId, Integer transactionId, Transaction transaction)
      throws EtBadRequestException;

  void removeTransaction(Integer userId, Integer categoryId, Integer transactionId)
      throws EtResourceNotFoundException;


}
