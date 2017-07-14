package com.vending;

public class InsufficientChangeException extends RuntimeException {
    private String message;

    public InsufficientChangeException(String string) {
        this.message = string;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
