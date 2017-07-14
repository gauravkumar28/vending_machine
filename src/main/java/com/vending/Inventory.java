package com.vending;

import java.util.HashMap;
import java.util.Map;

public class Inventory<T> {
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inventory other = (Inventory) obj;
		if (inventory == null) {
			if (other.inventory != null)
				return false;
		} else if (!inventory.equals(other.inventory))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Inventory [inventory=" + inventory + "]";
	}

	private Map<T, Integer> inventory = new HashMap<T, Integer>();

	public Map<T, Integer> getInventory() {
		return inventory;
	}

	public int getQuantity(T item) {
		Integer value = inventory.get(item);
		return value == null ? 0 : value;
	}

	public void add(T item) {
		int count = inventory.get(item);
		inventory.put(item, count + 1);
	}

	public void add(T item, int quantity) {
		int count = inventory.get(item);
		inventory.put(item, count + quantity);
	}

	public void deduct(T item) {
		if (hasItem(item)) {
			int count = inventory.get(item);
			inventory.put(item, count - 1);
		}
	}

	public void deduct(T item, int quantity) {
		if (hasItem(item)) {
			int count = inventory.get(item);
			inventory.put(item, count - quantity);
		}
	}

	public boolean hasItem(T item) {
		return getQuantity(item) > 0;
	}

	public void clear() {
		inventory.clear();
	}

	public void put(T item, int quantity) {
		inventory.put(item, quantity);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inventory == null) ? 0 : inventory.hashCode());
		return result;
	}

}
