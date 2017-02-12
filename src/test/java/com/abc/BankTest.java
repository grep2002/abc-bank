package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.abc.AccountConstants.AccountType;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;
    Bank bank = null;
       
    @Test
    public void checking_account_interestpaid() {
        bank = new Bank();
        Account checkingAccount = new CheckingAccount(AccountType.CHECKING);
        Customer bill = new Customer("Bill");
        bank.addCustomer(bill);
        bill.openAccount(checkingAccount);
        checkingAccount.deposit(100.0);

        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savings_account_interestpaid() {
        bank = new Bank();
        Account savingAccount = new SavingsAccount(AccountType.SAVINGS);
        Customer matt = new Customer("Matt");
        bank.addCustomer(matt);
        matt.openAccount(savingAccount);
        savingAccount.deposit(1500.0);

        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_interestpaid() {
        bank = new Bank();
        Account maxiAccount = new MaxiSavingsAccount(AccountType.MAXI_SAVINGS);
        Customer dave = new Customer("Dave");
        bank.addCustomer(dave);
        dave.openAccount(maxiAccount);
        maxiAccount.deposit(3000.0);

        assertEquals(170, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void total_interestpaid() {
    		bank = new Bank();
        Account checkingAccount = new CheckingAccount(AccountType.CHECKING); 
        Account savingAccount = new SavingsAccount(AccountType.SAVINGS);
        Account maxiAccount = new MaxiSavingsAccount(AccountType.MAXI_SAVINGS);
        Customer amos = new Customer("Amos");
        bank.addCustomer(amos);
        amos.openAccount(checkingAccount);
        amos.openAccount(savingAccount);
        amos.openAccount(maxiAccount);
        
        checkingAccount.deposit(100.0);
        savingAccount.deposit(1500.0);
        maxiAccount.deposit(3000.0);
        
        assertEquals(172.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void listofcustomers() {
        bank = new Bank();
        Customer john = new Customer("John");
        Customer amos = new Customer("Amos");
        amos.openAccount(new CheckingAccount(AccountType.CHECKING));
        bank.addCustomer(amos);
        john.openAccount(new CheckingAccount(AccountType.CHECKING));
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - Amos (1 account)\n - John (1 account)", bank.customerSummary());
    }

}
