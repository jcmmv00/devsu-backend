package com.devsu.devsu.core.exceptions;

public class AccountNotFoundException extends Exception {

    public AccountNotFoundException(String accountNumber) {
        super("Account with number " + accountNumber + " not found");
    }

}
