package com.mybank.api.model.dto;

import java.math.BigDecimal;

public class BankTransactionDTO {

  private Long id;
  private String date;
  private String type;
  private BigDecimal amount;

  public BankTransactionDTO() {
  }

  public BankTransactionDTO(Long id, String date, String type, BigDecimal amount) {
    this.id = id;
    this.date = date;
    this.type = type;
    this.amount = amount;
  }

  public BankTransactionDTO(String type, BigDecimal amount) {
    this.type = type;
    this.amount = amount;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
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
