package com.example.Interest;

public class Calculater {

	public static void main(String[] args) {

		double amount = 5000.00;

		int days = 365;

		int rate = 6;
		double dailyInterestRate =amount * rate /days;

		double totalAmount = amount+dailyInterestRate;

		for (int i = 1; i <= days; i++) {
			totalAmount +=(totalAmount*rate/365);

		
		System.out.println(totalAmount);

	}

}
}