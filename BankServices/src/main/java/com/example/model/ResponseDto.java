package com.example.model;



public class ResponseDto {

	private AccountHolderDto accountHolder;
	
	private BankDto bank;
	
	public AccountHolderDto getAccountHolder() {
		return accountHolder;
	}
	public void setAccountHolder(AccountHolderDto accountHolder) {
		this.accountHolder = accountHolder;
	}
	public BankDto getBank() {
		return bank;
	}
	public void setBank(BankDto bank) {
		this.bank = bank;
	}
	
}

