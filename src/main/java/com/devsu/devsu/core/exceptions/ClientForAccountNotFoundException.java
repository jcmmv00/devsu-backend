package com.devsu.devsu.core.exceptions;

public class ClientForAccountNotFoundException extends Exception {

    public ClientForAccountNotFoundException(String identification, String accountNumber) {
        super("Client with the identification: " + identification + " does not exist" +
                " or does not have an account with the number: " + accountNumber);
    }

}
