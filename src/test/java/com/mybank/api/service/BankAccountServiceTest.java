package com.mybank.api.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.mybank.api.dao.repository.BankAccountRepository;
import com.mybank.api.dao.repository.BankTransactionRepository;
import com.mybank.api.exception.ResourceNotFoundException;
import com.mybank.api.transformer.BankAccountTransformer;
import com.mybank.api.transformer.BankBalanceTransformer;
import com.mybank.api.transformer.BankTransactionTransformer;


@RunWith(MockitoJUnitRunner.class)
public class BankAccountServiceTest {

  @InjectMocks
  private BankAccountService bankAccountService;

  @Mock
  private BankAccountRepository bankAccountRepository;


  @Test(expected = ResourceNotFoundException.class)
  public void when_get_bank_account_and_not_exists_then_return_resource_not_found_exception() {

    when(bankAccountRepository.findBankAccountById(Mockito.anyLong())).thenReturn(Optional.empty());

    bankAccountService.getBankAccount(Mockito.anyLong());
  }
}