package com.example.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Bank {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long   id;
	    private String name;
	    private String branchCode;
	    private String ifscCode;
	    
	    private Long AccounHolferId;
	    
	    		
		public Long getAccounHolferId() {
			return AccounHolferId;
		}
		public void setAccounHolferId(Long accounHolferId) {
			AccounHolferId = accounHolferId;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getBranchCode() {
			return branchCode;
		}
		public void setBranchCode(String branchCode) {
			this.branchCode = branchCode;
		}
		public String getIfscCode() {
			return ifscCode;
		}
		public void setIfscCode(String ifscCode) {
			this.ifscCode = ifscCode;
		}
	    
	    

}
