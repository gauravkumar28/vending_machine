package com.vending;

public class PendingAmountException extends RuntimeException {
	private String message;
	private long pending;

	public PendingAmountException(String message, long remaining) {
		this.message = message;
		this.pending = remaining;
	}

	public long getRemaining() {
		return pending;
	}

	@Override
	public String getMessage() {
		return message + pending;
	}

}