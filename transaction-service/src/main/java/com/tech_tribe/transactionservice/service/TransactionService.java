package com.tech_tribe.transactionservice.service;

import com.tech_tribe.transactionservice.dto.TransactionDTO;
import com.tech_tribe.transactionservice.entity.Transaction;
import com.tech_tribe.transactionservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionDTO saveTransactionDetails(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setCustomerId(transactionDTO.getCustomerId());
        transaction.setPaymentAmount(transactionDTO.getPaymentAmount());
        transaction.setStatus(transactionDTO.getStatus());
        transaction.setReferenceId(transactionDTO.getReferenceId());

        transactionRepository.save(transaction);
        return transaction.build();
    }

}
