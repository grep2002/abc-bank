package com.abc;

import com.abc.AccountConstants.AccountType;
import com.abc.AccountConstants.Thresholds;
import com.abc.AccountConstants.Rates;

public class MaxiSavingsAccount extends Account {

	public MaxiSavingsAccount(AccountType accountType) {
		super(accountType);
		// TODO Auto-generated constructor stub
	}

	public double interestEarned() {
		double threshold1 = Thresholds.MAXI1.getThreshold();
		double threshold2 = Thresholds.MAXI2.getThreshold();
		if (this.getBalance() <= threshold1)
			return (this.getBalance() * Rates.MAXI_LOW.getInterestRate());
		else if ((this.getBalance() > threshold1) && (this.getBalance() <= threshold2))
			return ((threshold1 * Rates.MAXI_LOW.getInterestRate())
					+ ((this.getBalance() - threshold1) * Rates.MAXI_MED.getInterestRate()));
		return ((threshold1 * Rates.MAXI_LOW.getInterestRate())
				+ ((threshold2 - threshold1) * Rates.MAXI_MED.getInterestRate())
				+ ((this.getBalance() - threshold2) * Rates.MAXI_HIGH.getInterestRate()));
	}
}