package com.mybank.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.mybank.api.controller.validator.BankAccountValidator;
import com.mybank.api.model.dto.bankaccount.GETBankAccountDTO;
import com.mybank.api.model.dto.bankaccount.POSTBankAccountDTO;
import com.mybank.api.service.BankAccountService;

@RestController
@RequestMapping(value = "/bank_accounts")
public class BankAccountController extends BaseController {

  private BankAccountService bankAccountService;

  @Autowired
  public BankAccountController(BankAccountService bankAccountService) {
    this.bankAccountService = bankAccountService;
  }

  @GetMapping
  public List<GETBankAccountDTO> getAllBankAccounts() {

    return bankAccountService.getAllBankAccounts();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public GETBankAccountDTO createBankAccount(@RequestBody POSTBankAccountDTO body) {

    BankAccountValidator.validateRequest(body);
    return bankAccountService.createBankAccount(body);
  }
}
