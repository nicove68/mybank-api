package com.mybank.api.dao.model;

public enum BankTransactionType {
  DEPOSIT, WITHDRAW, TRANSFER;

  public static boolean contains(String value) {
    for (BankTransactionType type : BankTransactionType.values()) {
      if (type.name().equals(value)) {
        return true;
      }
    }
    return false;
  }
}
