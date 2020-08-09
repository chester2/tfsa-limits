package io.github.chester2.tfsalimits.model;

import java.math.BigDecimal;

public class Limit {
    private final int year;
    private final BigDecimal amount;

    public Limit(int year, BigDecimal amount) {
        this.year = year;
        this.amount = amount;
    }

    public int getYear() {
        return year;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
