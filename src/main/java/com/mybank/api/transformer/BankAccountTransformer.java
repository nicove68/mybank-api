package com.mybank.api.transformer;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mybank.api.model.dto.BankAccountDTO;
import com.mybank.api.model.entity.BankAccount;


@Component
public class BankAccountTransformer {

	@Autowired
	private ModelMapper modelMapper;

	public BankAccountDTO convertToDto(BankAccount bankAccount) {
		return modelMapper.map(bankAccount, BankAccountDTO.class);
	}

	public BankAccount convertToEntity(BankAccountDTO bankAccountDTO) {
		return modelMapper.map(bankAccountDTO, BankAccount.class);
	}
}
