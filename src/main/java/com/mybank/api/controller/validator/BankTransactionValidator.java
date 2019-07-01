package com.mybank.api.controller.validator;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.mybank.api.exception.BadRequestException;
import com.mybank.api.model.dto.BankTransactionDTO;
import com.mybank.api.model.entity.BankTransactionType;


@Component
public class BankTransactionValidator {

	public static void validateTransaction(BankTransactionDTO transaction) {
		if(transaction.getType() == null || transaction.getType().isEmpty()) throw new BadRequestException("The field 'type' is required.");
		if(!BankTransactionType.contains(transaction.getType())) throw new BadRequestException("The field 'type' is incorrect.");
		if(transaction.getAmount() == null) throw new BadRequestException("The field 'amount' is required.");
		if(transaction.getAmount().compareTo(BigDecimal.ZERO) < 1) throw new BadRequestException("The field 'amount' must be positive number.");
	}
}
