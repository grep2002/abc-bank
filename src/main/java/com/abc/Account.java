package com.abc;

import java.util.ArrayList;
import java.util.List;
import com.abc.AccountConstants.AccountType;

public abstract class Account {

	private double balance = 0.0;
	private final AccountType accountType;
	private List<Transaction> transactions;
	private final Object lock = new Object();

	public abstract double interestEarned();

	public double getBalance() {
		return balance;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	private void setBalance(double balance) {
		this.balance = balance;
	}

	/*
	 * deposit money into the account
	 */
	public boolean deposit(double amount) {
		synchronized (lock) {
			boolean depositsuccess = false;

			amount = Double.parseDouble(Double.toString(amount));
			if (amount <= 0.0)
				throw new IllegalArgumentException("deposit amount must be greater than 0");
			this.setBalance(this.getBalance() + amount);
			this.getTransactions().add(new Transaction(amount));
			depositsuccess = true;

			return depositsuccess;
		}
	}

	/*
	 * withdraw money from an account
	 */
	public boolean withdraw(double amount) {
		boolean withdrawsuccess = false;
		synchronized (lock) {

			amount = Double.parseDouble(Double.toString(amount));
			if ((amount <= 0.0) || (amount > this.getBalance()))
				throw new IllegalArgumentException(
						"amount must be greater than zero and less than current account balance");
			this.setBalance(this.getBalance() - amount);
			this.getTransactions().add(new Transaction(-amount));

			return withdrawsuccess;
		}
	}

	/*
	 * transfer money between accounts
	 */
	public boolean transfer(double amount, Account to) {
		boolean transfersuccess = false;
		synchronized (lock) {

			amount = Double.parseDouble(Double.toString(amount));
			if ((amount <= 0.0) || (amount > this.getBalance()))
				throw new IllegalArgumentException(
						"amount must be greater than zero and less than current account balance");

			this.setBalance(this.getBalance() - amount);
			to.setBalance(to.getBalance() + amount);
			this.getTransactions().add(new Transaction(-amount));
			to.getTransactions().add(new Transaction(amount));

			return transfersuccess;
		}
	}

	public Account(AccountType accountType) {
		this.accountType = accountType;
		this.transactions = new ArrayList<Transaction>();
	}

	public double sumTransactions() {
		return checkIfTransactionsExist(true);
	}

	private double checkIfTransactionsExist(boolean checkAll) {
		double amount = 0.0;
		for (Transaction t : transactions)
			amount += t.getAmount();
		return amount;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void addTransaction(Transaction transaction) {
		this.getTransactions().add(transaction);
	}
}
