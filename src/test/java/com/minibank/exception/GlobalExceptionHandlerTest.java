package com.minibank.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandleAccountNotFoundException() {
        AccountNotFoundException ex = new AccountNotFoundException("Account not found");
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleAccountNotFoundException(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Account not found", response.getBody().getMessage());
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getBody().getStatusCode());
    }

    @Test
    void testHandleCustomerAlreadyAssignedException() {
        CustomerAlreadyAssignedException ex = new CustomerAlreadyAssignedException("Customer already assigned");
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleCustomerAlreadyAssignedException(ex);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Customer already assigned", response.getBody().getMessage());
        assertEquals(HttpStatus.CONFLICT.value(), response.getBody().getStatusCode());
    }

    // Add similar tests for all other exception handlers
}
