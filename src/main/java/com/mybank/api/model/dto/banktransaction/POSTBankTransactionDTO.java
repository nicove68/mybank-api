package com.mybank.api.model.dto.banktransaction;

import java.math.BigDecimal;

public class POSTBankTransactionDTO {

  private String type;
  private BigDecimal amount;

  public POSTBankTransactionDTO() {
  }

  public POSTBankTransactionDTO(String type, BigDecimal amount) {
    this.type = type;
    this.amount = amount;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }
}
