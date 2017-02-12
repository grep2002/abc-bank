package com.abc;

public class AccountConstants {

	enum AccountType {
		CHECKING, SAVINGS, MAXI_SAVINGS;
	}

	enum Rates {
		CHECKING(0.001), SAVINGS_LOW(0.001), SAVINGS_HIGH(0.002), MAXI_LOW(.02), MAXI_MED(.05), MAXI_HIGH(.1);

		private final double rate;

		private Rates(double rate) {
			this.rate = rate;
		}

		double getInterestRate() {
			return rate;
		}
	}

	enum Thresholds {
		SAVINGS(1000.00), MAXI1(1000.00), MAXI2(2000.00);

		private final double threshold;

		private Thresholds(double threshold) {
			this.threshold = threshold;
		}

		double getThreshold() {
			return threshold;
		}
	}

}
