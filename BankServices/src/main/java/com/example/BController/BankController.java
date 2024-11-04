package com.example.BController;

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

import com.example.BService.BankService;
import com.example.Brepository.BankRepository;
import com.example.model.Bank;
import com.example.model.ResponseDto;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/bcc")
public class BankController {

	@Autowired
	private BankService bankService;

	@PostMapping("/BSave")
	public ResponseEntity<Bank> createBank(@RequestBody Bank bank) {
		return new ResponseEntity<Bank>(bankService.save(bank), HttpStatus.CREATED);

	}

	@GetMapping("/BgetAll")
	public List<Bank> getAll() {
		List<Bank> findAll = bankService.findAll();
		return findAll;
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseDto> getById(@PathVariable("id") Long id) {

		 ResponseDto responseDto = bankService.getByIds(id);
	        return ResponseEntity.ok(responseDto);
	}

	@DeleteMapping("/Bdelete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) {

		bankService.deleteById(id);
		return new ResponseEntity<String>("deleted Successfully", HttpStatus.OK);

	}

	@PutMapping("/Bupdate/{id}")
	public ResponseEntity<Bank> Update(@PathVariable Long id, @RequestBody Bank bank) {
		return new ResponseEntity<Bank>(bankService.Update(id, bank), HttpStatus.OK);
	}

}
