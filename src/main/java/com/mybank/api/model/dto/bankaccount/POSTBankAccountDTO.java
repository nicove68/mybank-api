package com.mybank.api.model.dto.bankaccount;

public class POSTBankAccountDTO {

  private String alias;
  private String type;

  public POSTBankAccountDTO() {
  }

  public POSTBankAccountDTO(String alias, String type) {
    this.alias = alias;
    this.type = type;
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
}
