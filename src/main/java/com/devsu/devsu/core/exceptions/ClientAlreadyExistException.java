package com.devsu.devsu.core.exceptions;

public class ClientAlreadyExistException extends Exception {

    public ClientAlreadyExistException(String identification) {
        super("Client with the identification: " + identification + " already exist");
    }

}
