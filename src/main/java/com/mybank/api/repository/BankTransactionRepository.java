package com.mybank.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mybank.api.model.entity.BankTransaction;

@Repository
public interface BankTransactionRepository extends CrudRepository<BankTransaction, Long> {

  List<BankTransaction> findBankTransactionsByAccountId(Long bankAccountId);
  List<BankTransaction> findAll();
}
