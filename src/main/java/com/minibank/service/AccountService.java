package com.minibank.service;

import com.minibank.entity.Account;
import com.minibank.entity.Customer;
import com.minibank.exception.AccountNotFoundException;
import com.minibank.exception.CustomerAlreadyAssignedException;
import com.minibank.exception.CustomerNotFoundException;
import com.minibank.repository.AccountRepository;
import com.minibank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Account createAccount() {
        Account account = new Account();
        return accountRepository.save(account);
    }

    public Account addCustomerToAccount(Long accountId, Long customerId) {
        // Use orElseThrow to handle the account not found case
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + accountId));

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + customerId));

        if (account.getCustomers().contains(customer)) {
            throw new CustomerAlreadyAssignedException("Customer already assigned to this account.");
        }

        account.getCustomers().add(customer);
        return accountRepository.save(account);
    }

    public Account removeCustomerFromAccount(Long accountId, Long customerId) {
        // Use orElseThrow to handle the account not found case
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + accountId));

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + customerId));

        if (!account.getCustomers().contains(customer)) {
            throw new CustomerNotFoundException("Customer not assigned to this account.");
        }

        account.getCustomers().remove(customer);
        return accountRepository.save(account);
    }

    public Account getAccountById(Long accountId) {
        // Use orElseThrow to handle the account not found case
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + accountId));
    }
}
