package com.task.loan;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class LoanC implements Loan {

    double interest;
    BigDecimal loanAmount;
    int loanTerm;
    BigDecimal monthlyPayment;
    BigDecimal totalRepayAmount;
    BigDecimal totalInterest;


    ArrayList<MonthlyPayment> monthlyPaymentList = new ArrayList<>();


    int loanType;
    public static final int AMORTIZED_LOAN = 1;
    public static final int DEFERRED_PAYMENT_LOAN = 2;
    public static final int FIXED_PRINCIPAL_LOAN = 3;



    public LoanC(double interest, int loanType, int loanTerm, BigDecimal loanAmount, boolean calculate) {
        this.interest = interest;
        this.loanType = loanType;
        this.loanTerm = loanTerm;
        this.loanAmount = loanAmount;
        if (calculate)
            calculate();
    }


    @Override
    public void calculate() {
        if (loanType == AMORTIZED_LOAN)
            amortizedLoan();
        if (loanType == DEFERRED_PAYMENT_LOAN)
            deferredPaymentLoan();
        if (loanType == FIXED_PRINCIPAL_LOAN)
            fixedPrincipalPaymentLoan();
    }

    /*
        P = a ÷ { [ (1 + r) n ] - 1 } ÷ [ r (1 + r) n] <-- formula to calculate monthly payment from https://www.moneygeek.com/personal-loans/calculate-loan-payments/
        P is your monthly loan payment
        a is your principal
        r is your periodic interest rate, which is your interest rate divided by 12
        n is the total number of months in your loan term
        converted to P = a ÷ (X ÷ Y)
    */
    private void amortizedLoan() {
        BigDecimal r = BigDecimal.valueOf(interest / 12);
        BigDecimal x = BigDecimal.ONE.add(r).pow(loanTerm).subtract(BigDecimal.ONE);
        BigDecimal y = r.multiply(BigDecimal.ONE.add(r).pow(loanTerm));
        BigDecimal z = x.divide(y, 8, RoundingMode.HALF_DOWN);
        BigDecimal p = loanAmount.divide(z, 8, RoundingMode.HALF_DOWN);

        monthlyPayment= p.setScale(2, RoundingMode.HALF_EVEN);
        totalRepayAmount = p.multiply(BigDecimal.valueOf(loanTerm)).setScale(2, RoundingMode.HALF_EVEN);
        totalInterest = totalRepayAmount.subtract(loanAmount);
    }

    private void fixedPrincipalPaymentLoan() {
        BigDecimal fixedPrincipal = loanAmount.divide(BigDecimal.valueOf(loanTerm), 2, RoundingMode.HALF_EVEN);
        BigDecimal remainedPrincipal = loanAmount;

        for (int month = 1; month <= loanTerm; month++) {
            BigDecimal monthsInterest = remainedPrincipal.multiply(BigDecimal.valueOf(interest / 12)).setScale(2, RoundingMode.HALF_EVEN);
            remainedPrincipal = remainedPrincipal.subtract(fixedPrincipal);
            monthlyPaymentList.add(new MonthlyPayment(month, fixedPrincipal, monthsInterest, fixedPrincipal.add(monthsInterest)));
        }
    }

    private void deferredPaymentLoan() {
        System.out.println("Deferred payment loan calculation is not implemented");
    }

    @Override
    public BigDecimal getTotalAmount() {
        return totalRepayAmount;
    }

    @Override
    public BigDecimal getMonthlyPayment() {
        return monthlyPayment;
    }

    @Override
    public BigDecimal getTotalInterest() {
        return totalInterest;
    }

    public ArrayList<MonthlyPayment> getMonthlyPaymentList() {
        return monthlyPaymentList;
    }

}
