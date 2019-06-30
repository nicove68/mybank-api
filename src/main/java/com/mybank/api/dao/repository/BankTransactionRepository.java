package com.mybank.api.dao.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mybank.api.dao.model.BankTransaction;

public interface BankTransactionRepository extends CrudRepository<BankTransaction, Long> {

  List<BankTransaction> findBankTransactionsByAccountId(Long bankAccountId);
  List<BankTransaction> findAll();
}
