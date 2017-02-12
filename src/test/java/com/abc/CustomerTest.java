package com.abc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import com.abc.AccountConstants.AccountType;

import java.util.Iterator;

import org.junit.Test;

public class CustomerTest {

	@Test
	public void openAccount() {
		Account checking = new CheckingAccount(AccountType.CHECKING);
		Customer customer = new Customer("John");
		customer.openAccount(checking);
		Iterator<Account> i = customer.getAccounts().iterator(); 
		while(i.hasNext()) {
			assertNotNull(i.next());
		}
	}
	
	@Test
	public void deposit() {
		Account checking = new CheckingAccount(AccountType.CHECKING);
		Account savings = new SavingsAccount(AccountType.SAVINGS);
		Account maxi = new MaxiSavingsAccount(AccountType.MAXI_SAVINGS);
		Customer customer = new Customer("John");
		customer.openAccount(checking);
		customer.openAccount(savings);
		customer.openAccount(maxi);
		
		Iterator<Account> i = customer.getAccounts().iterator(); 
		while(i.hasNext()) {
			assertNotNull(i.next());
		}

	}
	
	@Test
	public void statementPerAccount() {
		Account checking = new CheckingAccount(AccountType.CHECKING);
		Account savings = new SavingsAccount(AccountType.SAVINGS);
		Account maxi = new MaxiSavingsAccount(AccountType.MAXI_SAVINGS);
		Customer customer = new Customer("John");
		customer.openAccount(checking);
		customer.openAccount(savings);
		customer.openAccount(maxi);
		
		checking.deposit(1000);
		savings.deposit(200);
		maxi.deposit(500);
		maxi.withdraw(200);
		
		customer.getStatement();
	}
		
	@Test
	public void depostWithdraw() {

	}

    @Test //Test customer statement generation
    public void testApp(){

        Account checkingAccount = new CheckingAccount(AccountType.CHECKING);
        Account savingsAccount = new SavingsAccount(AccountType.SAVINGS);

        Customer henry = new Customer("Henry");
        henry.openAccount(checkingAccount);
        henry.openAccount(savingsAccount);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200.0);

        assertEquals("Statement for Henry\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "Total $100.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "Total $3,800.00\n" +
                "\n" +
                "Total In All Accounts $3,900.00", henry.getStatement()); 
    }

    @Test
    public void testOneAccount(){
        Customer oscar = new Customer("Oscar");
        oscar.openAccount(new SavingsAccount(AccountType.SAVINGS)); 
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar");
        oscar.openAccount(new SavingsAccount(AccountType.SAVINGS)); 
        oscar.openAccount(new CheckingAccount(AccountType.CHECKING));  
        assertEquals(2, oscar.getNumberOfAccounts());
    }

/*    @Ignore
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar");
        
                .openAccount(new Account(Account.SAVINGS));
        oscar.openAccount(new Account(Account.CHECKING));
        assertEquals(3, oscar.getNumberOfAccounts());
    }*/
}
