package com.mybank.api.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.mybank.api.dao.model.BankTransactionType;
import com.mybank.api.exception.BadRequestException;
import com.mybank.api.model.dto.banktransaction.POSTBankTransactionDTO;


@Component
public class BankTransactionValidator {

	public static void validateTransaction(POSTBankTransactionDTO transaction) {
		if(StringUtils.isEmpty(transaction.getType())) throw new BadRequestException("The field 'type' is required.");
		if(!BankTransactionType.contains(transaction.getType())) throw new BadRequestException("The field 'type' is incorrect.");
		if(StringUtils.isEmpty(transaction.getAmount())) throw new BadRequestException("The field 'amount' is required.");
	}
}
