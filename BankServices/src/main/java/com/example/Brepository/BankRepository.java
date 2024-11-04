package com.example.Brepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Bank;

public interface BankRepository extends JpaRepository<Bank, Long>{

}
