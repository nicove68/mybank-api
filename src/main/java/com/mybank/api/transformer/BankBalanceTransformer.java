package com.mybank.api.transformer;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mybank.api.dao.model.BankAccount;
import com.mybank.api.model.dto.bankbalance.GETBankBalanceDTO;


@Component
public class BankBalanceTransformer {

	@Autowired
	private ModelMapper modelMapper;

	public GETBankBalanceDTO convertToDto(BankAccount bankAccount) {
		return modelMapper.map(bankAccount, GETBankBalanceDTO.class);
	}
}
