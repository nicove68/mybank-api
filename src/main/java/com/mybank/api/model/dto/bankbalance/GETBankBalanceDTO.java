package com.mybank.api.model.dto.bankbalance;

import java.math.BigDecimal;

public class GETBankBalanceDTO {

  private BigDecimal balance;

  public GETBankBalanceDTO() {
  }

  public GETBankBalanceDTO(BigDecimal balance) {
    this.balance = balance;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }
}
