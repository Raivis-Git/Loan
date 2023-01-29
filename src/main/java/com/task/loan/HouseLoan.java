package com.task.loan;

import java.math.BigDecimal;


public class HouseLoan extends LoanC {

    public HouseLoan(int loanTerm, BigDecimal loanAmount, boolean calculate) {
        super(0.035, FIXED_PRINCIPAL_LOAN, loanTerm, loanAmount, calculate);
    }

}
