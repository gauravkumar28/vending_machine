package com.vending;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class VendingMachineImpl implements VendingMachine{
	@Override
	public String toString() {
		return "VendingMachineImpl [cashInventory=" + cashInventory + ", itemInventory=" + itemInventory
				+ ", totalSales=" + totalSales + ", currentItem=" + currentItem + ", currentBalance=" + currentBalance
				+ "]";
	}


	private Inventory<Cash> cashInventory = new Inventory<Cash>();
    private Inventory<Product> itemInventory = new Inventory<Product>();
    private Integer totalSales;
    private Product currentItem;
    private Integer currentBalance;
    
    public VendingMachineImpl(Inventory<Cash> cashInventory, Inventory<Product> itemInventory){
    	this.totalSales = 0;
    	this.currentItem = null;
    	this.currentBalance = 0;
    	this.cashInventory = cashInventory;
    	this.itemInventory = itemInventory;
    }
    
	public Inventory<Cash> getCashInventory() {
		return cashInventory;
	}
	public void setCashInventory(Inventory<Cash> cashInventory) {
		this.cashInventory = cashInventory;
	}
	public Inventory<Product> getItemInventory() {
		return itemInventory;
	}
	public void setItemInventory(Inventory<Product> itemInventory) {
		this.itemInventory = itemInventory;
	}
	public long getTotalSales() {
		return totalSales;
	}
	public void setTotalSales(Integer totalSales) {
		this.totalSales = totalSales;
	}
	public Product getCurrentItem() {
		return currentItem;
	}
	public void setCurrentItem(Product currentItem) {
		this.currentItem = currentItem;
	}
	public long getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(Integer currentBalance) {
		this.currentBalance = currentBalance;
	}
	
	
	public Integer selectItemAndGetPrice(Product item) {
        if (itemInventory.hasItem(item)) {
            currentItem = item;
            return currentItem.getPrice();
        }
        throw new SoldOutException("Sold Out, Please buy another item");
    }

    public void insertCash(Cash cash) {
        currentBalance = currentBalance + cash.getDenomination();
        cashInventory.add(cash);
    }
	
	public Inventory<Cash> refund() {
		Inventory<Cash>  refund = getChange(currentBalance);
        updateCashInventory(refund);
        currentBalance = 0;
        currentItem = null;
        return refund;
	}
	
	public void reset() {
        cashInventory.clear();
        itemInventory.clear();
        totalSales = 0;
        currentItem = null;
        currentBalance = 0;
    }
	
	public void printStats() {
        System.out.println("Total Sales : " + totalSales);
        System.out.println("Current Item Inventory : " + itemInventory);
        System.out.println("Current Cash Inventory : " + cashInventory);
    }
	
	private boolean hasSufficientChange() {
        return hasSufficientChangeForAmount(currentBalance - currentItem.getPrice());
    }
	
	 private boolean isFullPaid() {
        if (currentBalance >= currentItem.getPrice()) {
            return true;
        }
        return false;
     }
	 
	 
	 public PurchageSummary<Product, Inventory<Cash>> collectItemAndChange() {
		   Product item = collectItem();
	        totalSales = totalSales + currentItem.getPrice();

	        Inventory<Cash> change = collectChange();

	        return new PurchageSummary<Product, Inventory<Cash>>(item, change);
	   }
	 
	 private Product collectItem() throws InsufficientChangeException, PendingAmountException {
		 if (isFullPaid()) {
		     if (hasSufficientChange()) {
		         itemInventory.deduct(currentItem);
		         return currentItem;
		     }
		     throw new InsufficientChangeException("Not Sufficient change in Inventory");
		
		 }
		 long remainingBalance = currentItem.getPrice() - currentBalance;
		 throw new PendingAmountException("Price not full paid, remaining : ", remainingBalance);
	 }
	 
	 private Inventory<Cash> collectChange() {
        Integer changeAmount = currentBalance - currentItem.getPrice();
        Inventory<Cash> change = getChange(changeAmount);
        System.out.println(change.toString());
        updateCashInventory(change);
        currentBalance = 0;
        currentItem = null;
        return change;
     }
	
	private Inventory<Cash> getChange(Integer amount) throws InsufficientChangeException {

		Inventory<Cash> changesCash = new Inventory<Cash>();

        if (amount > 0) {
            Integer balance = amount;
            while (balance > 0) {
                if (balance >= Cash.HUNDRED_RUPEES.getDenomination()
                        && changesCash.getQuantity(Cash.HUNDRED_RUPEES) > cashInventory.getQuantity(Cash.HUNDRED_RUPEES)) {
                	changesCash.add(Cash.HUNDRED_RUPEES);
                    balance = balance - Cash.HUNDRED_RUPEES.getDenomination();
                    continue;

                } else if (balance >= Cash.FIFTY_RUPEES.getDenomination()
                        && changesCash.getQuantity(Cash.FIFTY_RUPEES) > cashInventory.getQuantity(Cash.FIFTY_RUPEES)) {
                	changesCash.add(Cash.FIFTY_RUPEES);
                    balance = balance - Cash.FIFTY_RUPEES.getDenomination();
                    
                    continue;

                } else if (balance >= Cash.TWENTY_RUPEES.getDenomination()
                        && changesCash.getQuantity(Cash.TWENTY_RUPEES) > cashInventory.getQuantity(Cash.TWENTY_RUPEES)) {
                	changesCash.add(Cash.TWENTY_RUPEES);
                    balance = balance - Cash.TWENTY_RUPEES.getDenomination();
                    
                    continue;

                } else if (balance >= Cash.TEN_RUPEES.getDenomination()
                        && changesCash.getQuantity(Cash.TEN_RUPEES) > cashInventory.getQuantity(Cash.TEN_RUPEES)) {
                	changesCash.add(Cash.TEN_RUPEES);
                    balance = balance - Cash.TEN_RUPEES.getDenomination();
                    continue;

                } else if (balance >= Cash.FIVE_RUPEES.getDenomination()
                        && changesCash.getQuantity(Cash.FIVE_RUPEES) > cashInventory.getQuantity(Cash.FIVE_RUPEES)) {
                	changesCash.add(Cash.FIVE_RUPEES);
                    balance = balance - Cash.FIVE_RUPEES.getDenomination();
                    continue;

                } else if (balance >= Cash.TWO_RUPEES.getDenomination()
                        && changesCash.getQuantity(Cash.TWO_RUPEES) > cashInventory.getQuantity(Cash.TWO_RUPEES)) {
                	changesCash.add(Cash.TWO_RUPEES);
                    balance = balance - Cash.TWO_RUPEES.getDenomination();
                    
                    continue;

                } else if (balance >= Cash.ONE_RUPEES.getDenomination()
                        && changesCash.getQuantity(Cash.ONE_RUPEES) > cashInventory.getQuantity(Cash.ONE_RUPEES)) {
                	changesCash.add(Cash.ONE_RUPEES);
                    balance = balance - Cash.ONE_RUPEES.getDenomination();
                    continue;

                }
                else {
                    throw new InsufficientChangeException("NotSufficientChange, Please try another product ");
                }
            
            }
        }

        return changesCash;
    }

	
	private boolean hasSufficientChangeForAmount(Integer amount) {
        boolean hasChange = true;
        try {
            getChange(amount);
        } catch (InsufficientChangeException ince) {
            return hasChange = false;
        }

        return hasChange;
    }
	
	
	private void updateCashInventory(Inventory<Cash> change) {
        for (Entry<Cash, Integer> money : change.getInventory().entrySet()) {
            cashInventory.deduct(money.getKey(), money.getValue());
        }
    }
	
}
