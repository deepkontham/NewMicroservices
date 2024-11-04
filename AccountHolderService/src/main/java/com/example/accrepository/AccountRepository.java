package com.example.accrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.account.AccountHolder;

public interface AccountRepository extends JpaRepository<AccountHolder, Long> {
	 
	

}
