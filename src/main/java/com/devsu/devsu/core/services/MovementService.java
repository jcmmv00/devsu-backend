package com.devsu.devsu.core.services;

import java.util.Date;
import java.util.List;

import com.devsu.devsu.core.exceptions.AccountNotFoundException;
import com.devsu.devsu.core.exceptions.DailyLimitExceeded;
import com.devsu.devsu.core.exceptions.MovementBalanceNotAvailableException;
import com.devsu.devsu.core.model.Movement;

public interface MovementService {

    Long createMovement(Movement movement, String accountNumber) throws AccountNotFoundException,
            DailyLimitExceeded, MovementBalanceNotAvailableException;

    List<Movement> queryMovementsBydateRange(Date dateA, Date dateB, String accountNumber) throws AccountNotFoundException;

    List<Movement> getAllMovements() throws AccountNotFoundException;
}
