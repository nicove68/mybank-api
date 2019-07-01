package com.mybank.api.controller.validator;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.mybank.api.exception.BadRequestException;
import com.mybank.api.model.dto.banktransaction.POSTBankTransactionDTO;


@RunWith(MockitoJUnitRunner.class)
public class BankTransactionValidatorTest {


  @Test(expected = BadRequestException.class)
  public void when_transaction_type_is_incomplete_then_return_bad_request_exeption() {
    POSTBankTransactionDTO transaction = createIncompleteTransaction();

    BankTransactionValidator.validateTransaction(transaction);
  }

  @Test(expected = BadRequestException.class)
  public void when_transaction_amount_is_negative_then_return_bad_request_exeption() {
    POSTBankTransactionDTO transaction = createNegativeTransaction();

    BankTransactionValidator.validateTransaction(transaction);
  }

  private POSTBankTransactionDTO createIncompleteTransaction() {
    return new POSTBankTransactionDTO(null, BigDecimal.TEN);
  }

  private POSTBankTransactionDTO createNegativeTransaction() {
    return new POSTBankTransactionDTO("WITHDRAW", new BigDecimal(-10));
  }
}