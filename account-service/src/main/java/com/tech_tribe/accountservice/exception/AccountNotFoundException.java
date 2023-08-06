package com.tech_tribe.accountservice.exception;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(long customerId) {
        super("Account is not found for given customer ID: " + customerId);
    }
}
