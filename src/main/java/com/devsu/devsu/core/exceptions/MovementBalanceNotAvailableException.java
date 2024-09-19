package com.devsu.devsu.core.exceptions;

public class MovementBalanceNotAvailableException extends Exception {

    public MovementBalanceNotAvailableException(Double quantity) {
        super("Not enough balance: " + quantity + " for this transaction");
    }
}
