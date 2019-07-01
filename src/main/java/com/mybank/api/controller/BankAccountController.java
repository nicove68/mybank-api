package com.mybank.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.mybank.api.controller.validator.BankAccountValidator;
import com.mybank.api.controller.validator.BankTransactionValidator;
import com.mybank.api.model.dto.BankAccountDTO;
import com.mybank.api.model.dto.BankBalanceDTO;
import com.mybank.api.model.dto.BankTransactionDTO;
import com.mybank.api.service.BankAccountService;
import com.mybank.api.service.BankTransactionService;

@RestController
@RequestMapping(value = "/bank_accounts")
public class BankAccountController extends BaseController {

  private BankAccountService bankAccountService;
  private BankTransactionService bankTransactionService;

  @Autowired
  public BankAccountController(BankAccountService bankAccountService, BankTransactionService bankTransactionService) {
    this.bankAccountService = bankAccountService;
    this.bankTransactionService = bankTransactionService;
  }

  @GetMapping
  public List<BankAccountDTO> getAllBankAccounts() {

    return bankAccountService.getAllBankAccounts();
  }

  @GetMapping("/{bankAccountId}")
  public BankAccountDTO getBankAccount(@PathVariable Long bankAccountId) {

    return bankAccountService.getBankAccount(bankAccountId);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public BankAccountDTO createBankAccount(@RequestBody BankAccountDTO body) {

    BankAccountValidator.validateAccount(body);
    return bankAccountService.createBankAccount(body);
  }

  @GetMapping("/{bankAccountId}/balance")
  public BankBalanceDTO getBankBalance(@PathVariable Long bankAccountId) {

    return bankAccountService.getBankBalance(bankAccountId);
  }

  @GetMapping("/{bankAccountId}/transactions")
  public List<BankTransactionDTO> getBankTransactionsFromAccount(@PathVariable Long bankAccountId) {

    return bankTransactionService.getBankTransactionsFromAccount(bankAccountId);
  }

  @PostMapping("/{bankAccountId}/transactions")
  @ResponseStatus(HttpStatus.CREATED)
  public BankTransactionDTO createBankTransaction(@PathVariable Long bankAccountId,
                                                  @RequestBody BankTransactionDTO body) {

    BankTransactionValidator.validateTransaction(body);
    return bankTransactionService.createBankTransaction(bankAccountId, body);
  }
}
