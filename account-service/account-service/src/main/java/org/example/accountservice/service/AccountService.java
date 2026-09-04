package org.example.accountservice.service;

import org.example.accountservice.entity.Account;
import org.example.accountservice.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final RestClient restClient;

    public AccountService(AccountRepository accountRepository,
                          RestClient restClient) {
        this.accountRepository = accountRepository;
        this.restClient = restClient;
    }

    // Create Account
    public Account createAccount(Account account) {

        account.setAccountNumber(
                UUID.randomUUID()
                        .toString()
                        .replace("-", "")
                        .substring(0, 12)
        );

        account.setBalance(0.0);

        return accountRepository.save(account);
    }

    // Get all accounts
    public List<Account> getAllAccounts() {

        return accountRepository.findAll();
    }

    // Get account by ID
    public Account getAccountById(Long id) {

        return accountRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Account not found"));
    }

    // Get account by account number
    public Account getAccountByNumber(String accountNumber) {

        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new RuntimeException("Account not found"));
    }

    // Deposit money
    public Account deposit(Long id, double amount) {

        Account account = getAccountById(id);

        if (amount <= 0) {
            throw new RuntimeException(
                    "Deposit amount must be greater than 0"
            );
        }

        account.setBalance(
                account.getBalance() + amount
        );

        return accountRepository.save(account);
    }

    // Withdraw money
    public Account withdraw(Long id, double amount) {

        Account account = getAccountById(id);

        if (amount <= 0) {
            throw new RuntimeException(
                    "Withdrawal amount must be greater than 0"
            );
        }

        if (account.getBalance() < amount) {
            throw new RuntimeException(
                    "Insufficient balance"
            );
        }

        account.setBalance(
                account.getBalance() - amount
        );

        return accountRepository.save(account);
    }

    // Delete account
    public void deleteAccount(Long id) {

        if (!accountRepository.existsById(id)) {
            throw new RuntimeException(
                    "Account not found"
            );
        }

        accountRepository.deleteById(id);
    }
}