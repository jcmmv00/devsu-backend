package com.devsu.devsu.application.query;

import java.util.*;
import com.devsu.devsu.core.model.enums.MovementType;

public class MovementViewQuery {
  private Long movementId;
    private Date date;
    private Double quantity;
    private MovementType movementType;
    private String accountNumber;
    
    public MovementViewQuery(Long movementId, Date date2, Double quantity, MovementType movementType, String accountNumber) {
        this.movementId = movementId;
        this.date = date2;
        this.quantity = quantity;
        this.movementType = movementType;
        this.accountNumber = accountNumber;
    }
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

    public String getAccount() {
        return accountNumber;
    }

    public void setAccount(String account) {
        this.accountNumber = account;
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