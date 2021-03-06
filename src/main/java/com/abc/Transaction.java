package com.abc;

import java.util.Date;

public class Transaction {
    private final double amount; 

    public double getAmount() {
		return amount;
	}

	private Date transactionDate;

    public Date getTransactionDate() {
		return transactionDate;
	}

	public Transaction(double amount) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
    }

}
