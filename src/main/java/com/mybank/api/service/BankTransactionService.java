package com.mybank.api.service;

import static com.mybank.api.model.entity.BankTransactionType.DEPOSIT;
import static com.mybank.api.model.entity.BankTransactionType.WITHDRAW;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybank.api.exception.BadRequestException;
import com.mybank.api.exception.InternalServerException;
import com.mybank.api.model.dto.BankTransactionDTO;
import com.mybank.api.model.entity.BankAccount;
import com.mybank.api.model.entity.BankTransaction;
import com.mybank.api.repository.BankTransactionRepository;
import com.mybank.api.transformer.BankTransactionTransformer;

@Service
public class BankTransactionService {

  private static Logger LOGGER = LoggerFactory.getLogger(BankTransactionService.class);
  private BankTransactionRepository bankTransactionRepository;
  private BankTransactionTransformer bankTransactionTransformer;
  private BankAccountService bankAccountService;
  private final ReentrantLock lock = new ReentrantLock();


  @Autowired
  public BankTransactionService(BankTransactionRepository bankTransactionRepository, BankTransactionTransformer bankTransactionTransformer, BankAccountService bankAccountService) {
    this.bankTransactionRepository = bankTransactionRepository;
    this.bankTransactionTransformer = bankTransactionTransformer;
    this.bankAccountService = bankAccountService;
  }

  public List<BankTransactionDTO> getBankTransactionsFromAccount(Long bankAccountId) {
    LOGGER.info("Getting all bank transactions from account id: " + bankAccountId);

    List<BankTransaction> bankTransactionList = bankTransactionRepository.findBankTransactionsByAccountId(bankAccountId);

    return bankTransactionList.stream()
        .map(bankTransactionTransformer::convertToDto)
        .collect(Collectors.toList());
  }

  public BankTransactionDTO createBankTransaction(Long bankAccountId, BankTransactionDTO bankTransactionDTO) {
    lock.lock();
    try {
      BankAccount bankAccount = bankAccountService.getBankAccountFromRepository(bankAccountId);

      if(bankTransactionDTO.getType().equalsIgnoreCase(DEPOSIT.name()))
        return depositAmountTransaction(bankTransactionDTO, bankAccount);

      if(bankTransactionDTO.getType().equalsIgnoreCase(WITHDRAW.name()))
        return withdrawAmountTransaction(bankTransactionDTO, bankAccount);

      throw new BadRequestException("No actions for transaction type: " + bankTransactionDTO.getType());

    } finally {
      lock.unlock();
    }
  }

  private BankTransactionDTO depositAmountTransaction(BankTransactionDTO bankTransactionDTO, BankAccount bankAccount) {
    BigDecimal newBalance = bankAccount.getBalance().add(bankTransactionDTO.getAmount());

    try {
      updateBankAccountBalance(bankAccount, newBalance);
      return saveBankTransaction(bankTransactionDTO, bankAccount);

    } catch (Exception ex) {
      LOGGER.error("Deposit transaction fail: ", ex);
      throw new InternalServerException("Deposit transaction fail. Please contact support service.");
    }
  }

  private BankTransactionDTO withdrawAmountTransaction(BankTransactionDTO bankTransactionDTO, BankAccount bankAccount) {
    BigDecimal newBalance = bankAccount.getBalance().subtract(bankTransactionDTO.getAmount());

    if (!isSecureWithdraw(bankAccount, newBalance))
      throw new BadRequestException("Bank account not have sufficient funds for the transaction.");

    try {
      updateBankAccountBalance(bankAccount, newBalance);
      return saveBankTransaction(bankTransactionDTO, bankAccount);

    } catch (Exception ex) {
      LOGGER.error("Withdraw transaction fail: ", ex);
      throw new InternalServerException("Withdraw transaction fail. Please contact support service.");
    }
  }

  private BankTransactionDTO saveBankTransaction(BankTransactionDTO bankTransactionDTO, BankAccount bankAccount) {
    BankTransaction bankTransaction = bankTransactionTransformer.convertToEntity(bankTransactionDTO, bankAccount);
    BankTransaction bankTransactionCreated = bankTransactionRepository.save(bankTransaction);

    LOGGER.info("New bank transaction type " + bankTransactionDTO.getType() + " created on bank account id: " + bankAccount.getId());

    return bankTransactionTransformer.convertToDto(bankTransactionCreated);
  }

  private void updateBankAccountBalance(BankAccount bankAccount, BigDecimal newBalance) {
    BankAccount bankAccountUpdated = new BankAccount(bankAccount, newBalance);
    bankAccountService.updateBankAccount(bankAccountUpdated);
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