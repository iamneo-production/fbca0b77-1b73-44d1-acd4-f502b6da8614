package com.tech_tribe.paymentservice.service;

import com.tech_tribe.paymentservice.dto.*;
import com.tech_tribe.paymentservice.exception.InsufficientBalanceFoundException;
import com.tech_tribe.paymentservice.exception.PaidBillFoundException;
import com.tech_tribe.paymentservice.feignclient.AccountsFeignClient;
import com.tech_tribe.paymentservice.feignclient.BillerFeignClient;
import com.tech_tribe.paymentservice.feignclient.NotificationFeignClient;
import com.tech_tribe.paymentservice.feignclient.TransactionFeignClient;
import com.tech_tribe.paymentservice.utils.BillStatus;
import com.tech_tribe.paymentservice.utils.NotificationType;
import com.tech_tribe.paymentservice.utils.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {

    @Autowired
    private AccountsFeignClient accountsFeignClient;
    @Autowired
    private BillerFeignClient billerFeignClient;
    @Autowired
    private TransactionFeignClient transactionFeignClient;
    @Autowired
    private NotificationFeignClient notificationFeignClient;

    public String processPayment(PaymentDTO paymentDTO) {
        AccountDTO accountDTO = accountsFeignClient.getAccountDetailsByCustomerId(paymentDTO.getCustomerId());
        BillerDTO billerDTO = billerFeignClient.getBillerDetailsByReferenceId(paymentDTO.getReferenceId());

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.assignData(accountDTO, billerDTO);

        if (billerDTO.getStatus().equals(BillStatus.Paid)) {
            throw new PaidBillFoundException(accountDTO.getName());
        }

        if (billerDTO.getBillTotal() > accountDTO.getAccountBalance()) {
            transactionDTO = transactionDTO.buildDTO(TransactionStatus.Declined);
            transactionFeignClient.saveTransactionDetails(transactionDTO);

            NotificationDTO notificationDTO = this.buildNotificationDTO(paymentDTO, false);
            notificationFeignClient.sendNotification(notificationDTO);

            throw new InsufficientBalanceFoundException(accountDTO.getName());
        }

        accountsFeignClient.updateAccountBalance(paymentDTO.getCustomerId(), billerDTO.getBillTotal());
        billerFeignClient.markPaidBill(paymentDTO.getReferenceId());

        transactionDTO = transactionDTO.buildDTO(TransactionStatus.Successful);
        transactionFeignClient.saveTransactionDetails(transactionDTO);

        NotificationDTO notificationDTO = this.buildNotificationDTO(paymentDTO, true);
        notificationFeignClient.sendNotification(notificationDTO);

        return "Payment processed successfully!";
    }

    public NotificationDTO buildNotificationDTO(PaymentDTO paymentDTO, boolean status) {
        return NotificationDTO.builder()
                .customerId(paymentDTO.getCustomerId())
                .message(String.format("Payment %s for bill reference %s", status ? "success" : "fail", paymentDTO.getReferenceId()))
                .status(NotificationType.Sent)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
