package org.example.transactionservice.controller;

import org.example.transactionservice.entity.Transaction;
import org.example.transactionservice.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // Create Transaction
    @PostMapping
    public Transaction createTransaction(
            @Valid @RequestBody Transaction transaction) {

        return transactionService.createTransaction(transaction);
    }

    // Get All Transactions
    @GetMapping
    public List<Transaction> getAllTransactions() {

        return transactionService.getAllTransactions();
    }

    // Get Transaction By ID
    @GetMapping("/{id}")
    public Transaction getTransactionById(
            @PathVariable Long id) {

        return transactionService.getTransactionById(id);
    }

    // Get Transactions By Account Number
    @GetMapping("/account/{accountNumber}")
    public List<Transaction> getTransactionsByAccountNumber(
            @PathVariable String accountNumber) {

        return transactionService
                .getTransactionsByAccountNumber(accountNumber);
    }

    // Delete Transaction
    @DeleteMapping("/{id}")
    public String deleteTransaction(
            @PathVariable Long id) {

        transactionService.deleteTransaction(id);

        return "Transaction deleted successfully";
    }
}