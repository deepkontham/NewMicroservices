package com.example.account;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AccountHolder {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long   accid;

	private String accHolderName;

	private String mobileNumber;

	@Column(unique = true)
	private String accountNumber;

	public Long getAccid() {
		return accid;
	}

	public void setAccid(Long accid) {
		this.accid = accid;
	}

	public String getAccHolderName() {
		return accHolderName;
	}

	public void setAccHolderName(String accHolderName) {
		this.accHolderName = accHolderName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

}
