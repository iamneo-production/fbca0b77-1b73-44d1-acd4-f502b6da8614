package com.tech_tribe.billservice.service;

import com.tech_tribe.billservice.dto.BillerDTO;
import com.tech_tribe.billservice.entity.Biller;
import com.tech_tribe.billservice.exception.BillNotFoundException;
import com.tech_tribe.billservice.repository.BillerRepository;
import com.tech_tribe.billservice.utils.BillStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BillerService {
    @Autowired
    private BillerRepository billerRepository;

    public BillerDTO saveBillDetails(BillerDTO billerDTO) {
        Biller biller = new Biller();

        biller.setReferenceId(UUID.randomUUID().toString());
        biller.setCustomerId(billerDTO.getCustomerId());
        biller.setType(billerDTO.getType());
        biller.setBillTotal(billerDTO.getBillTotal());
        biller.setStatus(BillStatus.Pending);

        billerRepository.save(biller);
        return biller.buildDTO();
    }

    public BillerDTO getBillByReferenceId(String referenceId) {
        Optional<Biller> existingBill = billerRepository.findByReferenceId(referenceId);

        if (existingBill.isEmpty()) {
            throw new BillNotFoundException(referenceId);
        }
        return existingBill.get().buildDTO();
    }

    public String markPaidBills(String referenceId) {
        Optional<Biller> existingBill = billerRepository.findByReferenceId(referenceId);

        if (existingBill.isEmpty()) {
            throw new BillNotFoundException(referenceId);
        }

        Biller biller = existingBill.get();
        biller.setStatus(BillStatus.Paid);
        billerRepository.save(biller);

        return String.format("%s mark as paid!", referenceId);
    }

}
