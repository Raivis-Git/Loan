package com.task.loan;

import java.math.BigDecimal;

public class MonthlyPayment {

    public MonthlyPayment(Integer month, BigDecimal principal, BigDecimal interest, BigDecimal total) {
        this.month = month;
        this.principal = principal;
        this.interest = interest;
        this.total = total;
    }

    Integer month;
    BigDecimal principal;
    BigDecimal interest;
    BigDecimal total;

    public Integer getMonth() {
        return month;
    }

    public BigDecimal getPrincipal() {
        return principal;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
