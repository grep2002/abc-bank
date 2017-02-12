package com.abc;

import java.util.ArrayList;
import java.util.List;
import com.abc.AccountConstants.AccountType;

public abstract class Account {

	// ** private final int accountType;
	private final AccountType accountType;
	public List<Transaction> transactions;
	final static Object lock = new Object();

	double balance = 0.0;

	double getBalance() {
		return balance;
	}

	public boolean deposit(double amount) {
		synchronized (lock) {
			boolean depositsuccess = false;
			try {
				amount = Double.parseDouble(Double.toString(amount));
				if (amount <= 0.0) {
					System.out.println("Not a valid deposit amount.");
					return depositsuccess;
				}
				this.setBalance(this.getBalance() + amount);
				this.getTransactions().add(new Transaction(amount));
				depositsuccess = true;
			} catch (NumberFormatException nfe) {
				System.out.println("Deposit amount is not a number.");
			}
			return depositsuccess;
		}
	}

	public double withdraw(double amount) {
		synchronized (lock) {
			try {
				amount = Double.parseDouble(Double.toString(amount));
				if ((amount <= 0.0) || (amount > this.getBalance())) {
					System.out.println("Not a valid withdrawal amount.");
					return amount;
				}
				this.setBalance(this.getBalance() - amount);
				this.getTransactions().add(new Transaction(-amount));
			} catch (NumberFormatException nfe) {
				System.out.println("Withdrawal amount is not a number.");
			}
			return amount;
		}
	}
	
	public double transfer(double amount, Account to) {
		synchronized (lock) {
			try {
				amount = Double.parseDouble(Double.toString(amount));
				if ((amount <= 0.0) || (amount > this.getBalance())) {
					System.out.println("Not a valid withdrawal amount.");
					return amount;
				}
				this.setBalance(this.getBalance() - amount);
				to.setBalance(to.getBalance() + amount);
				this.getTransactions().add(new Transaction(-amount));
				to.getTransactions().add(new Transaction(amount));
			} catch (NumberFormatException nfe) {
				System.out.println("Withdrawal amount is not a number.");
			}
			return amount;
		}
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	// ** public Account(int accountType) {
	public Account(AccountType accountType) {
		this.accountType = accountType;
		this.transactions = new ArrayList<Transaction>();
	}

	/*
	 * public void deposit(double amount) { if (amount <= 0) { throw new
	 * IllegalArgumentException("amount must be greater than zero"); } else {
	 * transactions.add(new Transaction(amount)); } }
	 * 
	 * public void withdraw(double amount) { if (amount <= 0) { throw new
	 * IllegalArgumentException("amount must be greater than zero"); } else {
	 * transactions.add(new Transaction(-amount)); } }
	 */

	public abstract double interestEarned();

	public double sumTransactions() {
		return checkIfTransactionsExist(true);
	}

	private double checkIfTransactionsExist(boolean checkAll) {
		double amount = 0.0;
		for (Transaction t : transactions)
			amount += t.amount;
		return amount;
	}

	// ** public int getAccountType() {
	public AccountType getAccountType() {
		return accountType;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void addTransaction(Transaction transaction) {
		this.getTransactions().add(transaction);
	}
}
