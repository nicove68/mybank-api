package com.mybank.api.controller.validator;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.mybank.api.exception.BadRequestException;
import com.mybank.api.model.dto.BankTransactionDTO;


@RunWith(MockitoJUnitRunner.class)
public class BankTransactionValidatorTest {


  @Test(expected = BadRequestException.class)
  public void when_transaction_type_is_incomplete_then_return_bad_request_exeption() {
    BankTransactionDTO transaction = createIncompleteTransaction();

    BankTransactionValidator.validateTransaction(transaction);
  }

  @Test(expected = BadRequestException.class)
  public void when_transaction_amount_is_negative_then_return_bad_request_exeption() {
    BankTransactionDTO transaction = createNegativeTransaction();

    BankTransactionValidator.validateTransaction(transaction);
  }

  private BankTransactionDTO createIncompleteTransaction() {
    return new BankTransactionDTO(null, BigDecimal.TEN);
  }

  private BankTransactionDTO createNegativeTransaction() {
    return new BankTransactionDTO("WITHDRAW", new BigDecimal(-10));
  }
}