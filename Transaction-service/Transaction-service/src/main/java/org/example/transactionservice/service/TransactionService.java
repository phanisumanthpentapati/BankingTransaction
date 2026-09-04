package org.example.transactionservice.service;

import org.example.transactionservice.entity.Transaction;
import org.example.transactionservice.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final RestClient restClient;

    public TransactionService(TransactionRepository transactionRepository,
                              RestClient restClient) {
        this.transactionRepository = transactionRepository;
        this.restClient = restClient;
    }

    // Create Transaction
    public Transaction createTransaction(Transaction transaction) {

        // Check whether account exists
        restClient.get()
                .uri("http://localhost:8082/accounts/number/"
                        + transaction.getAccountNumber())
                .retrieve()
                .toBodilessEntity();

        if (transaction.getTransactionDate() == null) {
            transaction.setTransactionDate(LocalDateTime.now());
        }

        return transactionRepository.save(transaction);
    }

    // Get all transactions
    public List<Transaction> getAllTransactions() {

        return transactionRepository.findAll();
    }

    // Get transaction by ID
    public Transaction getTransactionById(Long id) {

        return transactionRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Transaction not found"));
    }

    // Get transactions by account number
    public List<Transaction> getTransactionsByAccountNumber(
            String accountNumber) {

        return transactionRepository.findByAccountNumber(accountNumber);
    }

    // Delete transaction
    public void deleteTransaction(Long id) {

        if (!transactionRepository.existsById(id)) {
            throw new RuntimeException("Transaction not found");
        }

        transactionRepository.deleteById(id);
    }
}