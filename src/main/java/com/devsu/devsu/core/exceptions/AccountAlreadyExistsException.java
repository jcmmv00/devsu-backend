package com.devsu.devsu.core.exceptions;

public class AccountAlreadyExistsException extends Exception {

    public AccountAlreadyExistsException(String accountNumber) {
        super("Account with number " + accountNumber + " not found");
    }

}
