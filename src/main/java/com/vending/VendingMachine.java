package com.vending;

import java.util.List;

public interface VendingMachine {
	
	public Integer selectItemAndGetPrice(Product item);

    public void insertCash(Cash cash);

    public Inventory<Cash> refund();

    public void reset();

	public PurchageSummary<Product, Inventory<Cash>> collectItemAndChange();
		

}
