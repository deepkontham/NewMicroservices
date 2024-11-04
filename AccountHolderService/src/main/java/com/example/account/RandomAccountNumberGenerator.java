package com.example.account;

import java.security.SecureRandom;

public class RandomAccountNumberGenerator {

	
	  private static final String CHARACTERS = "0123456789";
	    private static final int RANDOM_LENGTH = 6;  // Length of random suffix
	    private static final SecureRandom random = new SecureRandom();

	    public static String generateAccountNumber(String mobileNumber) {
	        StringBuilder accountNumber = new StringBuilder(mobileNumber.substring(0, 5));
	        for (int i = 0; i < RANDOM_LENGTH; i++) {	        	
	            accountNumber.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));

	        }
	        	
	    
	        return accountNumber.toString();


	    }
}
