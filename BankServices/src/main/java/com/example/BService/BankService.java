package com.example.BService;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.example.ApiClients;
import com.example.Brepository.BankRepository;
import com.example.model.AccountHolderDto;
import com.example.model.Bank;
import com.example.model.BankDto;
import com.example.model.ResponseDto;

@Service
public class BankService {

	@Autowired
	private BankRepository repository;
	
//	@Autowired
//	private RestTemplate restTemplate;
	
	
//	@Autowired
//	private WebClient.Builder webClientBuilder;
	
	
	@Autowired
	private ApiClients apiClients; 
	
	
	public List<Bank> findAll() {
		return repository.findAll();
	}

	public Bank findById(Long id) {
		return repository.findById(id).get();
	}

	public Bank save(Bank bank) {
		return repository.save(bank);
	}

	public ResponseDto getByIds(Long id) {
		ResponseDto responseDto = new ResponseDto();
		Bank bank = repository.findById(id).get();
		BankDto banks= mapToBank(bank);
		
//For RestTemplate Using
		
//		ResponseEntity<AccountHolderDto> responseEntity = restTemplate
//				.getForEntity("http://Account-HolderService/api/acc/" + bank.getAccounHolferId(), AccountHolderDto.class);

		//for webClientBuilder Using
		
//		AccountHolderDto HolderDto = webClientBuilder.build() .get().uri("http://Account-HolderService/api/acc/"+ bank.getAccounHolferId())
//				.retrieve().bodyToMono(AccountHolderDto.class).block();
//		AccountHolderDto AccountDto = responseEntity.getBody();

//        System.out.println(responseEntity.getStatusCode());
		
		//Feign Client Using
		AccountHolderDto accountHolderDto=apiClients.getAccountHolderId(bank.getAccounHolferId());
		
		
		responseDto.setBank(banks);
		responseDto.setAccountHolder(accountHolderDto);


		return responseDto;
	}

	private BankDto mapToBank(Bank bank) {
		BankDto bankDto = new BankDto();
		bankDto.setId(bank.getId());
		bankDto.setName(bank.getName());
		bankDto.setBranchCode(bank.getBranchCode());
		bankDto.setIfscCode(bank.getIfscCode());
		return bankDto;
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public Bank Update(Long id, Bank bank) {
		Bank fi = repository.findById(id).get();
		fi.setId(bank.getId());
		fi.setName(bank.getName());
		fi.setBranchCode(bank.getBranchCode());
		fi.setIfscCode(bank.getIfscCode());
		return repository.save(fi);
	}

}
