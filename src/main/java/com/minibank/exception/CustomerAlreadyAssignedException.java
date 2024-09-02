package com.minibank.exception;

public class CustomerAlreadyAssignedException extends RuntimeException {
    public CustomerAlreadyAssignedException(String message) {
        super(message);
    }
}
