package com.mybank.api.dao.model;

public enum BankAccountType {
  STANDARD, GOLD, PLATINUM, BLACK;

  public static boolean contains(String value) {
    for (BankAccountType type : BankAccountType.values()) {
      if (type.name().equals(value)) {
        return true;
      }
    }
    return false;
  }
}
