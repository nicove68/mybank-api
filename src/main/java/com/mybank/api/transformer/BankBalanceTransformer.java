package com.mybank.api.transformer;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mybank.api.model.dto.BankBalanceDTO;
import com.mybank.api.model.entity.BankAccount;


@Component
public class BankBalanceTransformer {

	@Autowired
	private ModelMapper modelMapper;

	public BankBalanceDTO convertToDto(BankAccount bankAccount) {
		return modelMapper.map(bankAccount, BankBalanceDTO.class);
	}
}
