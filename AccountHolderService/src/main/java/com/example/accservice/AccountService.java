package com.example.accservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.account.AccountHolder;
import com.example.account.RandomAccountNumberGenerator;
import com.example.accrepository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public AccountHolder Save(AccountHolder accountHolder) {
		 String accountNumber =RandomAccountNumberGenerator.generateAccountNumber(accountHolder.getMobileNumber());
	        accountHolder.setAccountNumber(accountNumber);
	        return accountRepository.save(accountHolder);
	}

	public List<AccountHolder> Accountdetals() {
		return accountRepository.findAll();

	}

	public String deleteById(Long AccounHolferId) {
		
	  accountRepository.deleteById(AccounHolferId);
	return "delete successfully";
	}
	
	public AccountHolder AccounHolferId(Long AccounHolferId) {
		return  accountRepository.findById(AccounHolferId).get();
	
		
	}
	
	public AccountHolder UpdateById(Long AccounHolferId,AccountHolder accountHolder) {
	AccountHolder acc = accountRepository.findById(AccounHolferId).get();
	acc.setAccid(accountHolder.getAccid());
	acc.setAccHolderName(accountHolder.getAccHolderName());
	acc.setMobileNumber(accountHolder.getMobileNumber());
	acc.setAccountNumber(accountHolder.getAccountNumber());
		return accountRepository.save(acc);
	}
}
