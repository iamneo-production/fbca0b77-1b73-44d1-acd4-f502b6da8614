package com.tech_tribe.billservice.controller;

import com.tech_tribe.billservice.dto.BillerDTO;
import com.tech_tribe.billservice.exception.BillNotFoundException;
import com.tech_tribe.billservice.service.BillerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/biller")
public class BillerController {
    @Autowired
    private BillerService billerService;

    @PostMapping("/payments")
    public ResponseEntity saveBillerDetails(@Valid @RequestBody BillerDTO billerDTO) {
        try {
            return ResponseEntity.ok(billerService.saveBillDetails(billerDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/reference/{referenceId}")
    public ResponseEntity getBillByReferenceId(@PathVariable String referenceId) {
        try {
            return ResponseEntity.ok(billerService.getBillByReferenceId(referenceId));
        } catch (BillNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{referenceId}/status/paid")
    public ResponseEntity markPaidBill(@PathVariable String referenceId) {
        try {
            return ResponseEntity.ok(billerService.markPaidBills(referenceId));
        } catch (BillNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
