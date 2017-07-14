package com.vending;

public interface Supplier {
	VendingMachineBuilder vendingMachineBuilder();

	void reset(VendingMachine vendingMachine);
}
