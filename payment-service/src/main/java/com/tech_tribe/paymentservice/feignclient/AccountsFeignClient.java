package com.tech_tribe.paymentservice.feignclient;

import com.tech_tribe.paymentservice.dto.AccountDTO;
import com.tech_tribe.paymentservice.dto.BillerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Service
@FeignClient(name = "account-service", url = "${api.account-service.url}")
public interface AccountsFeignClient {
    @GetMapping("info/{customerId}")
    AccountDTO getAccountDetailsByCustomerId(@PathVariable("customerId") long customerId);
    @PutMapping("/{customerId}/{billAmount}")
    AccountDTO updateAccountBalance(
            @PathVariable("customerId") long customerId,
            @PathVariable("billAmount") long billAmount);
}
