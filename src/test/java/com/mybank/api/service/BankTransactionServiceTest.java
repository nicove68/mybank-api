package com.mybank.api.service;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mybank.api.exception.BadRequestException;
import com.mybank.api.model.dto.banktransaction.GETBankTransactionDTO;
import com.mybank.api.model.dto.banktransaction.POSTBankTransactionDTO;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BankTransactionServiceTest {

  /**
   * Initial database test registers are in: /src/test/resources/data.sql
   */

  @Autowired @InjectMocks
  private BankTransactionService bankTransactionService;

  @Autowired @InjectMocks
  private BankAccountService bankAccountService;



  @Test
  public void when_execute_deposit_transaction_then_return_balance_with_increment() {
    Long axlRoseBankAccountId = 1L;
    BigDecimal actualBalance = bankAccountService.getBankAccount(axlRoseBankAccountId).getBalance();
    Integer actualTransactionsQuantity = bankTransactionService.getBankTransactionsFromAccount(axlRoseBankAccountId).size();

    assertThat(actualBalance, comparesEqualTo(new BigDecimal(100.00).setScale(2, RoundingMode.DOWN)));
    assertEquals(Integer.valueOf(0), actualTransactionsQuantity);

    BigDecimal depositAmount = new BigDecimal(30.55).setScale(2, RoundingMode.DOWN);
    POSTBankTransactionDTO deposit = createDeposit(depositAmount);
    GETBankTransactionDTO transaction = bankTransactionService.createBankTransaction(axlRoseBankAccountId, deposit);

    assertNotNull(transaction);

    BigDecimal updatedBalance = bankAccountService.getBankAccount(axlRoseBankAccountId).getBalance();
    Integer updatedTransactionsQuantity = bankTransactionService.getBankTransactionsFromAccount(axlRoseBankAccountId).size();

    assertThat(updatedBalance, comparesEqualTo(new BigDecimal(130.55).setScale(2, RoundingMode.DOWN)));
    assertEquals(Integer.valueOf(1), updatedTransactionsQuantity);
  }

  @Test
  public void when_execute_withdraw_transaction_then_return_balance_with_decrement() {
    Long paulMccartneyBankAccountId = 2L;
    BigDecimal actualBalance = bankAccountService.getBankAccount(paulMccartneyBankAccountId).getBalance();
    Integer actualTransactionsQuantity = bankTransactionService.getBankTransactionsFromAccount(paulMccartneyBankAccountId).size();

    assertThat(actualBalance, comparesEqualTo(new BigDecimal(100.00).setScale(2, RoundingMode.DOWN)));
    assertEquals(Integer.valueOf(0), actualTransactionsQuantity);

    BigDecimal withdrawAmount = new BigDecimal(10.30).setScale(2, RoundingMode.DOWN);
    POSTBankTransactionDTO withdraw = createWithdraw(withdrawAmount);
    GETBankTransactionDTO transaction = bankTransactionService.createBankTransaction(paulMccartneyBankAccountId, withdraw);

    assertNotNull(transaction);

    BigDecimal updatedBalance = bankAccountService.getBankAccount(paulMccartneyBankAccountId).getBalance();
    Integer updatedTransactionsQuantity = bankTransactionService.getBankTransactionsFromAccount(paulMccartneyBankAccountId).size();

    assertThat(updatedBalance, comparesEqualTo(new BigDecimal(89.70).setScale(2, RoundingMode.DOWN)));
    assertEquals(Integer.valueOf(1), updatedTransactionsQuantity);
  }

  @Test(expected = BadRequestException.class)
  public void when_execute_withdraw_transaction_in_account_with_zero_balance_then_return_bad_request_exception() {
    Long tarjaTurunenBankAccountId = 3L;

    BigDecimal withdrawAmount = new BigDecimal(10.30).setScale(2, RoundingMode.DOWN);
    POSTBankTransactionDTO withdraw = createWithdraw(withdrawAmount);
    GETBankTransactionDTO transaction = bankTransactionService.createBankTransaction(tarjaTurunenBankAccountId, withdraw);
  }

  @Test(expected = BadRequestException.class)
  public void when_execute_withdraw_transaction_in_account_with_negative_balance_then_return_bad_request_exception() {
    Long bruceDickinsonBankAccountId = 4L;

    BigDecimal withdrawAmount = new BigDecimal(10.30).setScale(2, RoundingMode.DOWN);
    POSTBankTransactionDTO withdraw = createWithdraw(withdrawAmount);
    GETBankTransactionDTO transaction = bankTransactionService.createBankTransaction(bruceDickinsonBankAccountId, withdraw);
  }

  @Test(expected = BadRequestException.class)
  public void when_execute_withdraw_transaction_in_account_and_funds_are_enough_then_return_bad_request_exception() {
    Long brianJohnsonBankAccountId = 5L;

    BigDecimal withdrawAmount = new BigDecimal(5000.00).setScale(2, RoundingMode.DOWN);
    POSTBankTransactionDTO withdraw = createWithdraw(withdrawAmount);
    GETBankTransactionDTO transaction = bankTransactionService.createBankTransaction(brianJohnsonBankAccountId, withdraw);
  }

  @Test
  public void when_execute_multi_concurrent_deposit_transactions_then_return_correct_balance() {

  }


  private POSTBankTransactionDTO createDeposit(BigDecimal amount) {
    return new POSTBankTransactionDTO("DEPOSIT", amount);
  }

  private POSTBankTransactionDTO createWithdraw(BigDecimal amount) {
    return new POSTBankTransactionDTO("WITHDRAW", amount);
  }
}