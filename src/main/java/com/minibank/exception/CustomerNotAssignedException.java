package com.minibank.exception;

public class CustomerNotAssignedException extends RuntimeException {
    public CustomerNotAssignedException(String message) {
        super(message);
    }
}