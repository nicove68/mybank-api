package com.mybank.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mybank.api.model.entity.BankAccount;

@Repository
public interface BankAccountRepository extends CrudRepository<BankAccount, Long> {

  Optional<BankAccount> findBankAccountById(Long bankAccountId);
  List<BankAccount> findAll();
}
