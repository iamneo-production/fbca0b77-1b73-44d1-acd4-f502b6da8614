package com.tech_tribe.billservice.exception;

public class BillNotFoundException extends RuntimeException{
    public BillNotFoundException(String referenceId) {
        super("Bill is not found for given reference ID: " + referenceId);
    }
}
