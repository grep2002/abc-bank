package com.abc;

import com.abc.AccountConstants.AccountType;
import com.abc.AccountConstants.Rates;

public class CheckingAccount extends Account {

	public CheckingAccount(AccountType accountType) {
		super(accountType);
		// TODO Auto-generated constructor stub
	}

	public double interestEarned() {

		return this.getBalance() * Rates.CHECKING.getInterestRate();
	}
}