package com.vending;

public class PurchageSummary<E1, E2> {
	
	    @Override
	public String toString() {
		return "PurchageSummary [first=" + first + ", second=" + second + "]";
	}

		private E1 first;
	    private E2 second;

	    public PurchageSummary(E1 first, E2 second) {
	        this.first = first;
	        this.second = second;
	    }

	    public E1 getFirst() {
	        return first;
	    }

	    public E2 getSecond() {
	        return second;
	    }
}
