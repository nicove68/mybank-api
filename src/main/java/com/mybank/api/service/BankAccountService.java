package com.mybank.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybank.api.exception.InternalServerException;
import com.mybank.api.exception.ResourceNotFoundException;
import com.mybank.api.model.dto.BankAccountDTO;
import com.mybank.api.model.dto.BankBalanceDTO;
import com.mybank.api.model.entity.BankAccount;
import com.mybank.api.repository.BankAccountRepository;
import com.mybank.api.transformer.BankAccountTransformer;
import com.mybank.api.transformer.BankBalanceTransformer;

@Service
public class BankAccountService {

  private static Logger LOGGER = LoggerFactory.getLogger(BankAccountService.class);
  private BankAccountRepository bankAccountRepository;
  private BankAccountTransformer bankAccountTransformer;
  private BankBalanceTransformer bankBalanceTransformer;

  @Autowired
  public BankAccountService(BankAccountRepository bankAccountRepository, BankAccountTransformer bankAccountTransformer, BankBalanceTransformer bankBalanceTransformer) {
    this.bankAccountRepository = bankAccountRepository;
    this.bankAccountTransformer = bankAccountTransformer;
    this.bankBalanceTransformer = bankBalanceTransformer;
  }

  public List<BankAccountDTO> getAllBankAccounts() {
    LOGGER.info("Getting all bank accounts...");

    List<BankAccount> bankAccountList = bankAccountRepository.findAll();

    return bankAccountList.stream()
        .map(bankAccountTransformer::convertToDto)
        .collect(Collectors.toList());
  }

  public BankAccountDTO getBankAccount(Long bankAccountId) {

    BankAccount bankAccount = getBankAccountFromRepository(bankAccountId);

    return bankAccountTransformer.convertToDto(bankAccount);
  }

  public BankAccountDTO createBankAccount(BankAccountDTO bankAccountDTO) {
    try {
      LOGGER.info("Creating new bank account...");

      BankAccount bankAccount = bankAccountTransformer.convertToEntity(bankAccountDTO);
      BankAccount bankAccountCreated = bankAccountRepository.save(bankAccount);

      LOGGER.info("New bank account created with id: " + bankAccountCreated.getId());

      return bankAccountTransformer.convertToDto(bankAccountCreated);

    } catch (Exception ex) {
      LOGGER.error("Bank account creation fail: ", ex);
      throw new InternalServerException("Bank account creation fail. Please contact support service.");
    }
  }

  public BankBalanceDTO getBankBalance(Long bankAccountId) {

    BankAccount bankAccount = getBankAccountFromRepository(bankAccountId);

    return bankBalanceTransformer.convertToDto(bankAccount);
  }

  public BankAccount getBankAccountFromRepository(Long bankAccountId) {
    LOGGER.info("Find bank account with id: " + bankAccountId);

    Optional<BankAccount> bankAccount = bankAccountRepository.findBankAccountById(bankAccountId);

    return bankAccount.orElseThrow(ResourceNotFoundException::new);
  }
}