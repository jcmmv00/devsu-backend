package com.devsu.devsu.application.commands;

import com.devsu.devsu.core.model.enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AccountCreationDTO {

    @NotBlank
    private String accountNumber;
    @NotBlank
    private AccountType accountType;
    @NotBlank
    private double balance;
    @NotNull
    private boolean state;
    @NotBlank
    private String clientIdentification;

    public AccountCreationDTO(String accountNumber, AccountType type, double initialBalance, boolean state, String clientIdentification) {
        this.accountNumber = accountNumber;
        this.accountType = type;
        this.balance = initialBalance;
        this.state = state;
        this.clientIdentification = clientIdentification;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountType getAccountType() {
        return this.accountType;
    }

    public void setType(AccountType type) {
        this.accountType = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double initialBalance) {
        this.balance = initialBalance;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getClientIdentification() {
        return clientIdentification;
    }

    public void setClientIdentification(String clientId) {
        this.clientIdentification = clientId;
    }
}