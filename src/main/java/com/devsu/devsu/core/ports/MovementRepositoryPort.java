package com.devsu.devsu.core.ports;

import java.util.Date;
import java.util.List;

import com.devsu.devsu.core.model.Movement;
import com.devsu.devsu.core.model.enums.MovementType;

public interface MovementRepositoryPort {

    Long create(Movement movement);

    public List<Movement> getMovementsByDateAndType(Date date, MovementType movementType, Long accountId);

    public List<Movement> getMovementsByRangeDate(Date startDate, Date endDate, Long accountId);

    public List<Movement> getAllMovements();

}
