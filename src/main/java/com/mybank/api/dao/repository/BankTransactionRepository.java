package com.mybank.api.dao.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.mybank.api.dao.model.BankTransaction;

public interface BankTransactionRepository extends CrudRepository<BankTransaction, String> {

  Collection<BankTransaction> findBankTransactionsByAccountId(String bankAccountId);
}