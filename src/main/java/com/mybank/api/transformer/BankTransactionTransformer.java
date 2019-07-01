package com.mybank.api.transformer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mybank.api.model.dto.BankTransactionDTO;
import com.mybank.api.model.entity.BankAccount;
import com.mybank.api.model.entity.BankTransaction;


@Component
public class BankTransactionTransformer {

	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

	@Autowired
	private ModelMapper modelMapper;

	public BankTransactionDTO convertToDto(BankTransaction bankTransaction) {
		return modelMapper.map(bankTransaction, BankTransactionDTO.class);
	}

	public BankTransaction convertToEntity(BankTransactionDTO bankTransactionDTO, BankAccount bankAccount) {
		BankTransaction bankTransaction = modelMapper.map(bankTransactionDTO, BankTransaction.class);
		String now = LocalDateTime.now().format(dateTimeFormatter);

		return new BankTransaction(bankAccount, now, bankTransaction);
	}
}
