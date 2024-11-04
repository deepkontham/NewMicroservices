package com.example.acccontroller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.account.AccountHolder;
import com.example.accservice.AccountService;

@RestController
@RequestMapping("/api/acc")
public class AccountHolderController {
	
	@Autowired
	private AccountService accountService;
	
	@PostMapping("/accSave")
	public ResponseEntity<AccountHolder> createAccount(@RequestBody AccountHolder accountHolder){
		return new ResponseEntity<AccountHolder>(accountService.Save(accountHolder),HttpStatus.CREATED);
		
	}
	@GetMapping("/getAll")
	public List<AccountHolder> getAll(){
		List<AccountHolder> accountdetals = accountService.Accountdetals();

		return accountdetals;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AccountHolder> getAccounHolferId( @PathVariable("id") Long AccounHolferId){
		System.out.println("AccounHolferId"+AccounHolferId);
		AccountHolder holder = accountService.AccounHolferId(AccounHolferId);
		
		return ResponseEntity.ok(holder);

	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<AccountHolder> UpdateAccount(@RequestBody AccountHolder accountHolder,@PathVariable("id") Long AccounHolferId){
		return  new ResponseEntity<AccountHolder>(accountService.UpdateById(AccounHolferId,accountHolder),HttpStatus.OK);
		
		
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteById(@PathVariable("id") Long AccounHolferId){
		 accountService.deleteById(AccounHolferId);
		return "delete successfully";
		
			
	
	
	}
	

}
