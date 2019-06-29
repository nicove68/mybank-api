package com.mybank.api.model.dto.bankaccount;

import java.math.BigDecimal;

public class GETBankAccountDTO {

  private Long id;
  private String alias;
  private String type;
  private BigDecimal balance;

  public GETBankAccountDTO() {
  }

  public GETBankAccountDTO(Long id, String alias, String type, BigDecimal balance) {
    this.id = id;
    this.alias = alias;
    this.type = type;
    this.balance = balance;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }
}
