package com.mybank.api.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.mybank.api.dao.model.BankAccount;

public interface BankAccountRepository extends CrudRepository<BankAccount, Long> {

  Optional<BankAccount> findBankAccountById(Long bankAccountId);
  List<BankAccount> findAll();
}
