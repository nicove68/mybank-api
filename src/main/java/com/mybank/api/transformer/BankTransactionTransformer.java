package com.mybank.api.transformer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mybank.api.dao.model.BankTransaction;
import com.mybank.api.model.dto.banktransaction.GETBankTransactionDTO;
import com.mybank.api.model.dto.banktransaction.POSTBankTransactionDTO;


@Component
public class BankTransactionTransformer {

	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

	@Autowired
	private ModelMapper modelMapper;

	public GETBankTransactionDTO convertToDto(BankTransaction bankTransaction) {
		return modelMapper.map(bankTransaction, GETBankTransactionDTO.class);
	}

	public BankTransaction convertToEntity(POSTBankTransactionDTO bankTransactionDTO) {
		BankTransaction bankTransaction = modelMapper.map(bankTransactionDTO, BankTransaction.class);
		String now = LocalDateTime.now().format(dateTimeFormatter);
		return new BankTransaction(bankTransaction, now);
	}
}
