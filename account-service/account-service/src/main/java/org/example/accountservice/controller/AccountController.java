package org.example.accountservice.controller;

import org.example.accountservice.entity.Account;
import org.example.accountservice.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Create Account
    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    // Get All Accounts
    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    // Get Account By ID
    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    // Get Account By Account Number
    @GetMapping("/number/{accountNumber}")
    public Account getAccountByNumber(@PathVariable String accountNumber) {
        return accountService.getAccountByNumber(accountNumber);
    }

    // Deposit Money
    @PutMapping("/{id}/deposit")
    public Account deposit(
            @PathVariable Long id,
            @RequestParam double amount) {

        return accountService.deposit(id, amount);
    }

    // Withdraw Money
    @PutMapping("/{id}/withdraw")
    public Account withdraw(
            @PathVariable Long id,
            @RequestParam double amount) {

        return accountService.withdraw(id, amount);
    }

    // Delete Account
    @DeleteMapping("/{id}")
    public String deleteAccount(@PathVariable Long id) {

        accountService.deleteAccount(id);

        return "Account deleted successfully";
    }
}