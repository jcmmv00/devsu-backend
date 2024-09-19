package com.devsu.devsu.infrastructure.repository.entities;

import java.util.List;

import com.devsu.devsu.core.model.enums.AccountType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;
    @Column(nullable = false, unique = true)
    private String accountNumber;
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private AccountType accountType;
    @Column(nullable = false)
    private Double balance;
    @Column(nullable = false)
    private boolean state;
    @OneToMany
    private List<MovementEntity> movements;
    @ManyToOne
    private ClientEntity client;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public List<MovementEntity> getMovements() {
        return movements;
    }

    public void setMovements(List<MovementEntity> movements) {
        this.movements = movements;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }
}
