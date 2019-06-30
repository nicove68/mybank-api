package com.mybank.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.mybank.api.controller.validator.BankAccountValidator;
import com.mybank.api.controller.validator.BankTransactionValidator;
import com.mybank.api.model.dto.bankaccount.GETBankAccountDTO;
import com.mybank.api.model.dto.bankaccount.POSTBankAccountDTO;
import com.mybank.api.model.dto.bankbalance.GETBankBalanceDTO;
import com.mybank.api.model.dto.banktransaction.GETBankTransactionDTO;
import com.mybank.api.model.dto.banktransaction.POSTBankTransactionDTO;
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
  public List<GETBankAccountDTO> getAllBankAccounts() {

    return bankAccountService.getAllBankAccounts();
  }

  @GetMapping("/{bankAccountId}")
  public GETBankAccountDTO getBankAccount(@PathVariable Long bankAccountId) {

    return bankAccountService.getBankAccount(bankAccountId);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public GETBankAccountDTO createBankAccount(@RequestBody POSTBankAccountDTO body) {

    BankAccountValidator.validateAccount(body);
    return bankAccountService.createBankAccount(body);
  }

  @GetMapping("/{bankAccountId}/balance")
  public GETBankBalanceDTO getBankBalance(@PathVariable Long bankAccountId) {

    return bankAccountService.getBankBalance(bankAccountId);
  }

  @GetMapping("/{bankAccountId}/transactions")
  public List<GETBankTransactionDTO> getBankTransactionsFromAccount(@PathVariable Long bankAccountId) {

    return bankTransactionService.getBankTransactionsFromAccount(bankAccountId);
  }

  @PostMapping("/{bankAccountId}/transactions")
  @ResponseStatus(HttpStatus.CREATED)
  public GETBankTransactionDTO createBankTransaction(@PathVariable Long bankAccountId,
                                                     @RequestBody POSTBankTransactionDTO body) {

    BankTransactionValidator.validateTransaction(body);
    return bankTransactionService.createBankTransaction(bankAccountId, body);
  }
}
