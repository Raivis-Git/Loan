package com.task.loan;

import java.math.BigDecimal;
import java.util.Scanner;

public class LoanApplication {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Housing loan calculator");
		System.out.println("Input housing loan amount: ");
		BigDecimal loanAmount = BigDecimal.valueOf(scanner.nextLong());

		System.out.println("Input housing loan term: ");
		int loanTerm = scanner.nextInt();

		LoanC houseLoan = new HouseLoan(loanTerm, loanAmount, true);

		System.out.println("Month\t\tPrincipal\t\tInterest\t\tTotal");
		for (MonthlyPayment monthlyPayment : houseLoan.getMonthlyPaymentList()) {
			System.out.println(monthlyPayment.getMonth() + "\t\t\t" + monthlyPayment.getPrincipal() + "\t\t\t" + monthlyPayment.getInterest() + "\t\t\t" + monthlyPayment.getTotal());
		}

	}

}
