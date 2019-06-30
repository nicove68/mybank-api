package com.mybank.api.service;

import static com.mybank.api.dao.model.BankTransactionType.DEPOSIT;
import static com.mybank.api.dao.model.BankTransactionType.WITHDRAW;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybank.api.dao.model.BankAccount;
import com.mybank.api.dao.model.BankTransaction;
import com.mybank.api.dao.repository.BankAccountRepository;
import com.mybank.api.dao.repository.BankTransactionRepository;
import com.mybank.api.exception.BadRequestException;
import com.mybank.api.model.dto.banktransaction.GETBankTransactionDTO;
import com.mybank.api.model.dto.banktransaction.POSTBankTransactionDTO;
import com.mybank.api.transformer.BankTransactionTransformer;

@Service
public class BankTransactionService {

  private static Logger LOGGER = LoggerFactory.getLogger(BankTransactionService.class);
  private BankTransactionRepository bankTransactionRepository;
  private BankAccountRepository bankAccountRepository;
  private BankTransactionTransformer bankTransactionTransformer;
  private BankAccountService bankAccountService;

  @Autowired
  public BankTransactionService(BankTransactionRepository bankTransactionRepository, BankAccountRepository bankAccountRepository, BankTransactionTransformer bankTransactionTransformer, BankAccountService bankAccountService) {
    this.bankTransactionRepository = bankTransactionRepository;
    this.bankAccountRepository = bankAccountRepository;
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
    BankAccount bankAccount = bankAccountService.getBankAccountFromRepository(bankAccountId);

    if(postBankTransactionDTO.getType().equalsIgnoreCase(DEPOSIT.name()))
      return depositAmountTransaction(postBankTransactionDTO, bankAccount);

    if(postBankTransactionDTO.getType().equalsIgnoreCase(WITHDRAW.name()))
      return withdrawAmountTransaction(postBankTransactionDTO, bankAccount);

    throw new BadRequestException("No actions for transaction type: " + postBankTransactionDTO.getType());
  }

  private GETBankTransactionDTO depositAmountTransaction(POSTBankTransactionDTO postBankTransactionDTO, BankAccount bankAccount) {
    BigDecimal newBalance = bankAccount.getBalance().add(postBankTransactionDTO.getAmount());
    updateBankAccountBalance(bankAccount, newBalance);

    return saveBankTransaction(postBankTransactionDTO, bankAccount);
  }

  private GETBankTransactionDTO withdrawAmountTransaction(POSTBankTransactionDTO postBankTransactionDTO, BankAccount bankAccount) {
    BigDecimal newBalance = bankAccount.getBalance().subtract(postBankTransactionDTO.getAmount());

    if (!isSecureWithdraw(bankAccount, newBalance))
      throw new BadRequestException("Bank account not have sufficient funds for the transaction.");

    updateBankAccountBalance(bankAccount, newBalance);

    return saveBankTransaction(postBankTransactionDTO, bankAccount);
  }

  private GETBankTransactionDTO saveBankTransaction(POSTBankTransactionDTO postBankTransactionDTO, BankAccount bankAccount) {
    BankTransaction bankTransaction = bankTransactionTransformer.convertToEntity(postBankTransactionDTO, bankAccount);
    BankTransaction bankTransactionCreated = bankTransactionRepository.save(bankTransaction);

    LOGGER.info("New bank transaction type " + postBankTransactionDTO.getType() + " created on bank account id: " + bankAccount.getId());

    return bankTransactionTransformer.convertToDto(bankTransactionCreated);
  }

  private void updateBankAccountBalance(BankAccount bankAccount, BigDecimal newBalance) {
    BankAccount bankAccountUpdated = new BankAccount(bankAccount, newBalance);
    bankAccountRepository.save(bankAccountUpdated);
    LOGGER.info("Balance updated on bank account id: " + bankAccount.getId());
  }

  private Boolean isSecureWithdraw(BankAccount bankAccount, BigDecimal newBalance) {
    return !isBalanceZero(bankAccount.getBalance()) && !isBalanceNegative(newBalance);
  }

  private Boolean isBalanceZero(BigDecimal balance) {
   return balance.compareTo(BigDecimal.ZERO) == 0;
  }

  private Boolean isBalanceNegative(BigDecimal balance) {
    return balance.compareTo(BigDecimal.ZERO) < 0;
  }
}