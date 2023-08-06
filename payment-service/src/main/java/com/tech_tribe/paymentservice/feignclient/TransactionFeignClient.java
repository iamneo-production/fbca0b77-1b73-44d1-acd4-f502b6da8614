package com.tech_tribe.paymentservice.feignclient;

import com.tech_tribe.paymentservice.dto.BillerDTO;
import com.tech_tribe.paymentservice.dto.TransactionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@FeignClient(name = "transaction-service", url = "${api.transaction-service.url}")
public interface TransactionFeignClient {
    @PostMapping("/record")
    String saveTransactionDetails(@RequestBody TransactionDTO transactionDTO);
}
