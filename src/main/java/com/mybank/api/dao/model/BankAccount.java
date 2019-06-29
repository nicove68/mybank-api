package com.mybank.api.dao.model;

import java.math.BigDecimal;

import javax.persistence.*;

@Entity
public class BankAccount {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String alias;

  @Enumerated(EnumType.STRING)
  private BankAccountType type;

  private BigDecimal balance = BigDecimal.ZERO;


  public BankAccount() {
  }

  public BankAccount(String alias, BankAccountType type, BigDecimal balance) {
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

  public BankAccountType getType() {
    return type;
  }

  public void setType(BankAccountType type) {
    this.type = type;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }
}
