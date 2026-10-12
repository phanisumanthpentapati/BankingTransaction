
package org.example.transactionservice.service;

import org.example.transactionservice.TransactionEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TransactionEventConsumer {

    @KafkaListener(
        topics = "bank-transactions",
        groupId = "transaction-service-group"
    )
    public void consumeTransaction(TransactionEvent event) {
        System.out.println("========== KAFKA EVENT RECEIVED ==========");
        System.out.println("Account Number: " + event.getAccountNumber());
        System.out.println("Transaction Type: " + event.getType());
        System.out.println("Amount: " + event.getAmount());
        System.out.println("Transaction Date: " + event.getTransactionDate());
        System.out.println("==========================================");
    }
}
