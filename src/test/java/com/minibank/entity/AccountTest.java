package com.minibank.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void testAccountEntity() {
        Account account = new Account();
        account.setId(1L);
        account.setNumberOfOwners(2);

        assertNotNull(account);
        assertEquals(1L, account.getId());
        assertEquals(2, account.getNumberOfOwners());
    }

    @Test
    void testCustomerEntity() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John");
        customer.setLastname("Doe");

        assertNotNull(customer);
        assertEquals(1L, customer.getId());
        assertEquals("John", customer.getName());
        assertEquals("Doe", customer.getLastname());
    }
}
