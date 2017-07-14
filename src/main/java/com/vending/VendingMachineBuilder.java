package com.vending;


public class VendingMachineBuilder {
	@Override
	public String toString() {
		return "VendingMachineBuilder [cashInventory=" + cashInventory + ", itemInventory=" + itemInventory + "]";
	}
	private Inventory<Cash> cashInventory = new Inventory<Cash>();
    private Inventory<Product> itemInventory = new Inventory<Product>();

    
    public void addProduct(Product product, Integer numUnits)
    {
    	itemInventory.put(product, numUnits);
    	
    }
    public void setCash(Cash cash, Integer numUnits){
    	cashInventory.put(cash, numUnits);
    }
    public VendingMachine build(){
    	VendingMachineImpl vendingMachine = new VendingMachineImpl(cashInventory, itemInventory);
    	return vendingMachine;
    	
    }
}
