package com.abc;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AccountTest {
	private static final double DOUBLE_DELTA = 1e-15;

	@Test
	public void testDeposit() {
		Account a = new CheckingAccount(AccountConstants.AccountType.CHECKING);
		a.deposit(10.00);
		assertEquals(10.00, a.getBalance(), DOUBLE_DELTA);
	}

	// deposit amount less than 0
	@Test
	public void testSubZeroDeposit() throws IllegalArgumentException { 
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("deposit amount must be greater than 0");
		Account savings = new SavingsAccount(AccountConstants.AccountType.SAVINGS);
		savings.deposit(-10.00);
	}

	@Test
	public void testWithdraw() {
		Account a = new CheckingAccount(AccountConstants.AccountType.CHECKING);
		a.deposit(10.00);
		a.withdraw(10.00);
		assertEquals(0.00, a.getBalance(), DOUBLE_DELTA);
	}

	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	// transfer more than balance throws IllegalArugumentException
	@Test
	public void testExcessTransfer() throws IllegalArgumentException {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("amount must be greater than zero and less than current account balance");
		Account savings = new SavingsAccount(AccountConstants.AccountType.SAVINGS);
		Account checking = new CheckingAccount(AccountConstants.AccountType.CHECKING);
		savings.deposit(10.00);
		savings.transfer(15.00, checking);
	}

	@Test
	public void testTransfer() throws IllegalArgumentException {
		Account savings = new SavingsAccount(AccountConstants.AccountType.SAVINGS);
		Account checking = new CheckingAccount(AccountConstants.AccountType.CHECKING);
		savings.deposit(10.00);
		savings.transfer(7.00, checking);
		assertEquals(7.00, checking.getBalance(), DOUBLE_DELTA);
	}

	@Test
	public void testInterestEarned() {
		Account a = new SavingsAccount(AccountConstants.AccountType.SAVINGS);
		a.deposit(2000.00);
		assertEquals(3.00, a.interestEarned(), DOUBLE_DELTA);
	}

	@Test
	public void testSumTransactions() {
		Account a = new SavingsAccount(AccountConstants.AccountType.SAVINGS);
		a.deposit(1000.00);
		a.withdraw(10.00);
		a.deposit(2000.00);
		double[] expected = { 1000.00, -10, 2000.00 };
		double[] actual = new double[3];
		int item = 0;
		Iterator<Transaction> i = a.getTransactions().iterator();
		while (i.hasNext()) {
			actual[item] = i.next().getAmount();
			item++;
		}
		assertArrayEquals(expected, actual, DOUBLE_DELTA);
	}
}
