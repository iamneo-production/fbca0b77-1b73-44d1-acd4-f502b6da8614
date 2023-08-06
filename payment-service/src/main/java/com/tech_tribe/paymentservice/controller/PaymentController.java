package com.tech_tribe.paymentservice.controller;

import com.tech_tribe.paymentservice.dto.PaymentDTO;
import com.tech_tribe.paymentservice.exception.InsufficientBalanceFoundException;
import com.tech_tribe.paymentservice.exception.PaidBillFoundException;
import com.tech_tribe.paymentservice.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/process")
    public ResponseEntity processPayment(@Valid @RequestBody PaymentDTO paymentDTO) {
        try {
            return ResponseEntity.ok(paymentService.processPayment(paymentDTO));
        } catch (InsufficientBalanceFoundException | PaidBillFoundException i) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(i.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
