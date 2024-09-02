package com.minibank.service;

import com.minibank.entity.Account;
import com.minibank.entity.Customer;
import com.minibank.exception.CustomerAlreadyAssignedException;
import com.minibank.exception.CustomerNotFoundException;
import com.minibank.exception.AccountNotFoundException;
import com.minibank.repository.AccountRepository;
import com.minibank.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAccount_Success() {
        Account account = new Account();
        account.setId(1L);

        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account createdAccount = accountService.createAccount();
        assertNotNull(createdAccount);
        assertEquals(1L, createdAccount.getId());
    }

    @Test
    void testAddCustomerToAccount_Success() {
        Account account = new Account();
        account.setId(1L);
        account.setCustomers(new HashSet<>()); // Initialize with a modifiable Set

        Customer customer = new Customer();
        customer.setId(1L);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(accountRepository.save(account)).thenReturn(account);

        Account updatedAccount = accountService.addCustomerToAccount(1L, 1L);
        assertEquals(1, updatedAccount.getCustomers().size());
    }

    @Test
    void testAddCustomerToAccount_CustomerAlreadyAssigned() {
        Customer customer = new Customer();
        customer.setId(1L);

        Account account = new Account();
        account.setId(1L);
        account.setCustomers(Set.of(customer));

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        assertThrows(CustomerAlreadyAssignedException.class, () -> accountService.addCustomerToAccount(1L, 1L));
    }

    @Test
    void testAddCustomerToAccount_AccountNotFound() {
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> accountService.addCustomerToAccount(1L, 1L));
    }

    @Test
    void testAddCustomerToAccount_CustomerNotFound() {
        Account account = new Account();
        account.setId(1L);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> accountService.addCustomerToAccount(1L, 1L));
    }

    @Test
    void testRemoveCustomerFromAccount_Success() {
        Customer customer = new Customer();
        customer.setId(1L);

        Account account = new Account();
        account.setId(1L);
        account.setCustomers(new HashSet<>(Set.of(customer))); // Initialize with a modifiable Set

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(accountRepository.save(account)).thenReturn(account);

        Account updatedAccount = accountService.removeCustomerFromAccount(1L, 1L);
        assertEquals(0, updatedAccount.getCustomers().size());
    }

    @Test
    void testRemoveCustomerFromAccount_CustomerNotAssigned() {
        Customer customer = new Customer();
        customer.setId(2L); // Different customer ID

        Account account = new Account();
        account.setId(1L);
        account.setCustomers(new HashSet<>(Set.of(customer)));

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(customerRepository.findById(1L)).thenReturn(Optional.of(new Customer()));

        assertThrows(CustomerNotFoundException.class, () -> accountService.removeCustomerFromAccount(1L, 1L));
    }

    @Test
    void testRemoveCustomerFromAccount_AccountNotFound() {
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> accountService.removeCustomerFromAccount(1L, 1L));
    }

    @Test
    void testRemoveCustomerFromAccount_CustomerNotFound() {
        Account account = new Account();
        account.setId(1L);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> accountService.removeCustomerFromAccount(1L, 1L));
    }

    @Test
    void testGetAccountById_Success() {
        Account account = new Account();
        account.setId(1L);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        Account foundAccount = accountService.getAccountById(1L);
        assertNotNull(foundAccount);
        assertEquals(1L, foundAccount.getId());
    }

    @Test
    void testGetAccountById_AccountNotFound() {
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> accountService.getAccountById(1L));
    }


}
