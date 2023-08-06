package com.tech_tribe.transactionservice.controller;

import com.tech_tribe.transactionservice.dto.TransactionDTO;
import com.tech_tribe.transactionservice.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/record")
    public ResponseEntity saveTransactionDetails(@Valid @RequestBody TransactionDTO transactionDTO) {
        try {
            return ResponseEntity.ok(transactionService.saveTransactionDetails(transactionDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
