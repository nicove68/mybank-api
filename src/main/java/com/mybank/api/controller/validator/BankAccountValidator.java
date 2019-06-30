package com.mybank.api.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.mybank.api.dao.model.BankAccountType;
import com.mybank.api.exception.BadRequestException;
import com.mybank.api.model.dto.bankaccount.POSTBankAccountDTO;


@Component
public class BankAccountValidator {

	public static void validateAccount(POSTBankAccountDTO account) {
		if(StringUtils.isEmpty(account.getAlias())) throw new BadRequestException("The field 'alias' is required.");
		if(StringUtils.isEmpty(account.getType())) throw new BadRequestException("The field 'type' is required.");
		if(!BankAccountType.contains(account.getType())) throw new BadRequestException("The field 'type' is incorrect.");
	}
}
