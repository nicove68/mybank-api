package com.mybank.api.controller.validator;

import org.springframework.stereotype.Component;

import com.mybank.api.exception.BadRequestException;
import com.mybank.api.model.dto.BankAccountDTO;
import com.mybank.api.model.entity.BankAccountType;


@Component
public class BankAccountValidator {

	public static void validateAccount(BankAccountDTO account) {
		if(account.getAlias() == null || account.getAlias().isEmpty()) throw new BadRequestException("The field 'alias' is required.");
		if(account.getType() == null || account.getType().isEmpty()) throw new BadRequestException("The field 'type' is required.");
		if(!BankAccountType.contains(account.getType())) throw new BadRequestException("The field 'type' is incorrect.");
	}
}
