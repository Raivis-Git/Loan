package com.task.loan;

import java.math.BigDecimal;

public interface Loan {

    public void calculate();
    public BigDecimal getTotalAmount();
    public BigDecimal getMonthlyPayment();
    public BigDecimal getTotalInterest();

}
