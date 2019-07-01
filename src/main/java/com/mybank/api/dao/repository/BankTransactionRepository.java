package com.mybank.api.dao.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mybank.api.dao.model.BankTransaction;

@Repository
public interface BankTransactionRepository extends CrudRepository<BankTransaction, Long> {

  List<BankTransaction> findBankTransactionsByAccountId(Long bankAccountId);
  List<BankTransaction> findAll();
}
