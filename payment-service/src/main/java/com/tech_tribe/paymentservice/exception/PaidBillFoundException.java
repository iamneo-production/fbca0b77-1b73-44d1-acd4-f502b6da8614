package com.tech_tribe.paymentservice.exception;

public class PaidBillFoundException extends RuntimeException {
    public PaidBillFoundException(String customerName) {
        super(String.format("Dear %s, Your bill amount is already paid!", customerName));
    }
}
