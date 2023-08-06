package com.tech_tribe.accountservice.controller;

import com.tech_tribe.accountservice.dto.AccountDTO;
import com.tech_tribe.accountservice.exception.AccountNotFoundException;
import com.tech_tribe.accountservice.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity submitAccountDetails(@Valid @RequestBody AccountDTO accountDTO) {
        try {
            return ResponseEntity.ok(accountService.createAccount(accountDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/info/{customerId}")
    public ResponseEntity getAccountDetailsByCustomerId(@PathVariable long customerId) {
        try {
            return ResponseEntity.ok(accountService.getAccountDetailsByCustomerId(customerId));
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{customerId}/{billAmount}")
    public ResponseEntity updateCustomerAccountBalance(@PathVariable long customerId, @PathVariable long billAmount) {
        try {
            return ResponseEntity.ok(accountService.updateAccountBalance(customerId, billAmount));
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
