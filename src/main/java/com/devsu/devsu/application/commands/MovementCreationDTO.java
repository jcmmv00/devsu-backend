package com.devsu.devsu.application.commands;


import com.devsu.devsu.core.model.enums.MovementType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MovementCreationDTO {

    @NotNull
    private Double quantity;
    @NotNull
    private MovementType movementType;
    @NotBlank
    private String accountNumber;

    public Double getQuantity() {
        return quantity;
    }
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
    public MovementType getMovementType() {
        return movementType;
    }
    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    

}
