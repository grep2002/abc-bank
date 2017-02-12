package com.abc;

import com.abc.AccountConstants.AccountType;
import com.abc.AccountConstants.Thresholds;
import com.abc.AccountConstants.Rates;

public class SavingsAccount extends Account {

	public SavingsAccount(AccountType accountType) {
		super(accountType);
	}

	public double interestEarned() {
		double threshold = Thresholds.SAVINGS.getThreshold();
		if (this.getBalance() <= threshold)
			return this.getBalance() * Rates.SAVINGS_LOW.getInterestRate();

		return ((threshold * Rates.SAVINGS_LOW.getInterestRate())
				+ (this.getBalance() - threshold) * Rates.SAVINGS_HIGH.getInterestRate());
	}
}