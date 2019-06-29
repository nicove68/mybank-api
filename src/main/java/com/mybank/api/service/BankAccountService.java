package com.mybank.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybank.api.dao.model.BankAccount;
import com.mybank.api.dao.repository.BankAccountRepository;
import com.mybank.api.model.dto.bankaccount.GETBankAccountDTO;
import com.mybank.api.model.dto.bankaccount.POSTBankAccountDTO;
import com.mybank.api.transformer.BankAccountTransformer;

@Service
public class BankAccountService {

  private static Logger LOGGER = LoggerFactory.getLogger(BankAccountService.class);
  private BankAccountRepository bankAccountRepository;
  private BankAccountTransformer bankAccountTransformer;

  @Autowired
  public BankAccountService(BankAccountRepository bankAccountRepository, BankAccountTransformer bankAccountTransformer) {
    this.bankAccountRepository = bankAccountRepository;
    this.bankAccountTransformer = bankAccountTransformer;
  }

  public List<GETBankAccountDTO> getAllBankAccounts() {
    LOGGER.info("Getting all bank accounts...");

    List<BankAccount> bankAccountList = bankAccountRepository.findAll();

    return bankAccountList.stream().map(bankAccountTransformer::convertToDto).collect(Collectors.toList());
  }

  public GETBankAccountDTO createBankAccount(POSTBankAccountDTO postBankAccountDTO) {
    LOGGER.info("Creating new bank account...");

    BankAccount bankAccount = bankAccountTransformer.convertToEntity(postBankAccountDTO);
    BankAccount bankAccountCreated = bankAccountRepository.save(bankAccount);

    LOGGER.info("New bank account created with id: " + bankAccountCreated.getId());

    return bankAccountTransformer.convertToDto(bankAccountCreated);
  }

}