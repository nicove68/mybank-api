package com.mybank.api.transformer;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mybank.api.dao.model.BankAccount;
import com.mybank.api.model.dto.bankaccount.GETBankAccountDTO;
import com.mybank.api.model.dto.bankaccount.POSTBankAccountDTO;


@Component
public class BankAccountTransformer {

	@Autowired
	private ModelMapper modelMapper;

	public GETBankAccountDTO convertToDto(BankAccount bankAccount) {
		return modelMapper.map(bankAccount, GETBankAccountDTO.class);
	}

	public BankAccount convertToEntity(POSTBankAccountDTO bankAccountDTO) {
		return modelMapper.map(bankAccountDTO, BankAccount.class);
	}
}
