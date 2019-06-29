package com.mybank.api.dao.model;

import java.math.BigDecimal;

import javax.persistence.*;

@Entity
public class BankTransaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "account_id", nullable = false)
  private BankAccount account;

  private String date;

  @Enumerated(EnumType.STRING)
  private BankTransactionType type;

  private BigDecimal amount;


  public BankTransaction() {
  }

  public BankTransaction(BankAccount account, String date, BankTransactionType type, BigDecimal amount) {
    this.account = account;
    this.date = date;
    this.type = type;
    this.amount = amount;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BankAccount getAccount() {
    return account;
  }

  public void setAccount(BankAccount account) {
    this.account = account;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public BankTransactionType getType() {
    return type;
  }

  public void setType(BankTransactionType type) {
    this.type = type;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }
}
