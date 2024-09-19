package com.devsu.devsu.infrastructure.repository.entities;

import java.util.Date;

import com.devsu.devsu.core.model.enums.MovementType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class MovementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long movementId;
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private Double quantity;
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private MovementType movementType;
    @ManyToOne
    private AccountEntity account;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public Long getMovementId() {
        return movementId;
    }

    public void setMovementId(Long movementId) {
        this.movementId = movementId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

}
