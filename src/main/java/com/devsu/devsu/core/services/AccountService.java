package com.devsu.devsu.core.services;

import com.devsu.devsu.core.exceptions.AccountNotFoundException;
import com.devsu.devsu.core.exceptions.ClientNotFoundException;
import com.devsu.devsu.core.model.Account;

public interface AccountService {

    String createAccount(Account account, String clientIdentification) throws ClientNotFoundException;

    Account getAccountByAccountNumber(String accountNumber) throws AccountNotFoundException;

    boolean updateAccount(Account account) throws AccountNotFoundException;

    boolean deleteAccount(String accountNumber) throws AccountNotFoundException;
}
