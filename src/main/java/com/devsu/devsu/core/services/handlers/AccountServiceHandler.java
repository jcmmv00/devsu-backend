package com.devsu.devsu.core.services.handlers;

import com.devsu.devsu.core.exceptions.ClientNotFoundException;
import com.devsu.devsu.core.model.Account;
import com.devsu.devsu.core.services.AccountService;
import com.devsu.devsu.core.services.ClientService;
import com.devsu.devsu.core.model.Client;
import com.devsu.devsu.core.ports.AccountRepositoryPort;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devsu.devsu.core.exceptions.AccountNotFoundException;

@Service
public class AccountServiceHandler implements AccountService {

    private final ClientService clientService;
    private final AccountRepositoryPort accountRepository;

    public AccountServiceHandler(ClientService clientService, AccountRepositoryPort accountRepository) {
        this.clientService = clientService;
        this.accountRepository = accountRepository;
    }

    @Override
    public String createAccount(Account account, String clientIdentification) throws ClientNotFoundException {
        Client client = this.clientService.getClient(clientIdentification);
        account.setClient(client);
        return accountRepository.create(account);
    }

    @Override
    public Account getAccountByAccountNumber(String accountNumber) throws AccountNotFoundException {
        return this.accountRepository.getAccountByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException(accountNumber));
    }

    @Override
    public boolean updateAccount(Account account) throws AccountNotFoundException {
        boolean isUpdated = this.accountRepository.updateAccount(account);
        if (isUpdated) {
            return true;
        } else {
            throw new AccountNotFoundException(account.getAccountNumber());
        }
    }

    @Override
    public boolean deleteAccount(String accountNumber) throws AccountNotFoundException {
        boolean isDeleted = accountRepository.deleteAccount(accountNumber);
        if (isDeleted) {
            return isDeleted;
        } else {
            throw new AccountNotFoundException(accountNumber);
        }
    }

    @Override
    public List<Account> getAccounts() {
        return this.accountRepository.getAllAcounts();
    }
}
