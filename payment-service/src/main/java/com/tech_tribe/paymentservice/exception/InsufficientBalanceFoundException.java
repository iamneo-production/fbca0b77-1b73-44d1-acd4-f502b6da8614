package com.tech_tribe.paymentservice.exception;

public class InsufficientBalanceFoundException extends RuntimeException {
    public InsufficientBalanceFoundException(String customerName) {
        super(String.format("Dear %s, Your account balance is insufficient to pay the bill amount!", customerName));
    }
}
