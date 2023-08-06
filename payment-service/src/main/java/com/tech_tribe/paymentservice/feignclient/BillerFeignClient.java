package com.tech_tribe.paymentservice.feignclient;

import com.tech_tribe.paymentservice.dto.BillerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Service
@FeignClient(name = "biller-service", url = "${api.biller-service.url}")
public interface BillerFeignClient {
    @GetMapping("/reference/{referenceId}")
    BillerDTO getBillerDetailsByReferenceId(@PathVariable("referenceId") String referenceId);

    @PutMapping("/{referenceId}/status/paid")
    String markPaidBill(@PathVariable("referenceId") String referenceId);
}
