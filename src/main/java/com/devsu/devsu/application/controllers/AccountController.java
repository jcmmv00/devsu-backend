package com.devsu.devsu.application.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsu.devsu.application.commands.AccountCreationDTO;
import com.devsu.devsu.core.exceptions.ClientNotFoundException;
import com.devsu.devsu.core.model.Account;
import com.devsu.devsu.core.services.AccountService;
import com.devsu.devsu.core.exceptions.AccountNotFoundException;

@RestController
@RequestMapping("api/v1/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Object> getAccount(@PathVariable String accountNumber) throws AccountNotFoundException {
        Account accountFound = this.accountService.getAccountByAccountNumber(accountNumber);
        return ResponseEntity.ok(accountFound);
    }

    @PostMapping()
    public ResponseEntity<Object> createAccount(@RequestBody AccountCreationDTO accountCreationDTO)
            throws ClientNotFoundException {
        Account account = new Account();
        account.setAccountNumber(accountCreationDTO.getAccountNumber());
        account.setAccountType(accountCreationDTO.getAccountType());
        account.setBalance(accountCreationDTO.getBalance());
        account.setState(accountCreationDTO.isState());
        String accountId = this.accountService.createAccount(account, accountCreationDTO.getClientIdentification());
        return ResponseEntity.ok(accountId);
    }

    @PutMapping
    public ResponseEntity<Object> updateAccount(
            @RequestBody AccountCreationDTO accountCreationDTO)
            throws AccountNotFoundException {
        Account account = new Account();
        account.setAccountNumber(accountCreationDTO.getAccountNumber());
        account.setAccountType(accountCreationDTO.getAccountType());
        account.setBalance(accountCreationDTO.getBalance());
        account.setState(accountCreationDTO.isState());
        this.accountService.updateAccount(account);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Object> deleteAccount(@PathVariable String accountId) throws AccountNotFoundException {
        if (accountService.deleteAccount(accountId)) {
            return ResponseEntity.ok("Deleted account with number " + accountId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}