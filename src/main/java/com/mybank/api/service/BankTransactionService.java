package com.mybank.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybank.api.dao.model.BankAccount;
import com.mybank.api.dao.model.BankTransaction;
import com.mybank.api.dao.repository.BankTransactionRepository;
import com.mybank.api.model.dto.banktransaction.GETBankTransactionDTO;
import com.mybank.api.model.dto.banktransaction.POSTBankTransactionDTO;
import com.mybank.api.transformer.BankTransactionTransformer;

@Service
public class BankTransactionService {

  private static Logger LOGGER = LoggerFactory.getLogger(BankTransactionService.class);
  private BankTransactionRepository bankTransactionRepository;
  private BankTransactionTransformer bankTransactionTransformer;
  private BankAccountService bankAccountService;

  @Autowired
  public BankTransactionService(BankTransactionRepository bankTransactionRepository, BankTransactionTransformer bankTransactionTransformer, BankAccountService bankAccountService) {
    this.bankTransactionRepository = bankTransactionRepository;
    this.bankTransactionTransformer = bankTransactionTransformer;
    this.bankAccountService = bankAccountService;
  }

  public List<GETBankTransactionDTO> getBankTransactionsFromAccount(Long bankAccountId) {
    LOGGER.info("Getting all bank transactions from account id: " + bankAccountId);

    List<BankTransaction> bankTransactionList = bankTransactionRepository.findBankTransactionsByAccountId(bankAccountId);

    return bankTransactionList.stream()
        .map(bankTransactionTransformer::convertToDto)
        .collect(Collectors.toList());
  }

  public GETBankTransactionDTO createBankTransaction(Long bankAccountId, POSTBankTransactionDTO postBankTransactionDTO) {
    LOGGER.info("Creating new bank transaction for bank account id: " + bankAccountId);

    BankAccount bankAccount = bankAccountService.getBankAccountFromRepository(bankAccountId);
    // TODO: validar si el balance es 0 cero

    BankTransaction bankTransaction = bankTransactionTransformer.convertToEntity(postBankTransactionDTO, bankAccount);
    BankTransaction bankTransactionCreated = bankTransactionRepository.save(bankTransaction);

    LOGGER.info("New bank transaction created with id: " + bankTransactionCreated.getId());

    return bankTransactionTransformer.convertToDto(bankTransactionCreated);
  }

}