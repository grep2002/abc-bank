package com.abc;

import java.util.ArrayList;
import java.util.List;

public enum Bank {
	// guarantees single instance of the bank
	BANK_INSTANCE;
	
    private List<Customer> customers;

    public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void addCustomer(Customer customer) {
		if (this.getCustomers() == null)
			this.setCustomers(new ArrayList<Customer>());
        customers.add(customer);     
    }

    public String customerSummary() {
        String summary = "Customer Summary";
        for (Customer c : customers)
            summary += "\n - " + c.getName() + " (" + format(c.getNumberOfAccounts(), "account") + ")";
        return summary;
    }

    //Make sure correct plural of word is created based on the number passed in:
    //If number passed in is 1 just return the word otherwise add an 's' at the end
    private String format(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }

    public double totalInterestPaid() {
        double total = 0;
        if (this.getCustomers() == null || this.getCustomers().isEmpty())
        		return total;
        for(Customer c: customers)
            total += c.totalInterestEarned();
        return total;
    }

}
