package com.vending;

public enum Cash {
	ONE_RUPEES(1), TWO_RUPEES(2), FIVE_RUPEES(5), TEN_RUPEES(10), TWENTY_RUPEES(20), FIFTY_RUPEES(50), HUNDRED_RUPEES(
			100);

	private int denomination;

	private Cash(int denomination) {
		this.denomination = denomination;
	}

	public int getDenomination() {
		return denomination;
	}

	@Override
	public String toString() {
		return "" + denomination;
	}

}
