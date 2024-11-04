package com.example;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.model.AccountHolderDto;

@FeignClient(value = "Account-HolderService")

public interface ApiClients {
	@GetMapping(value = "/api/acc/{id}")
	AccountHolderDto getAccountHolderId(@PathVariable ("id") Long AccounHolferId);

}
