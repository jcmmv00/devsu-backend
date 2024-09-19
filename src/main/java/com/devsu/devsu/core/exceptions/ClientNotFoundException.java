package com.devsu.devsu.core.exceptions;

public class ClientNotFoundException extends Exception {

    public ClientNotFoundException(String identification) {
        super("Client with the identification: " + identification + " does not exist");
    }

}
