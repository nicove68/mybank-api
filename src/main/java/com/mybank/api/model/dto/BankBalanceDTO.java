package com.mybank.api.model.dto;

import java.math.BigDecimal;

public class BankBalanceDTO {

  private BigDecimal balance;

  public BankBalanceDTO() {
  }

  public BankBalanceDTO(BigDecimal balance) {
    this.balance = balance;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }
}
