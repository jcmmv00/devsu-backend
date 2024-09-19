package com.devsu.devsu.infrastructure.repository.mysql.mappers;

import org.springframework.stereotype.Service;

import com.devsu.devsu.core.model.Movement;
import com.devsu.devsu.infrastructure.repository.entities.AccountEntity;
import com.devsu.devsu.infrastructure.repository.entities.MovementEntity;

@Service
public class MovementMapper implements Mapper<Movement, MovementEntity> {

    @Override
    public Movement mapDatabaseEntityToCoreEntity(MovementEntity movementEntity) {
        Movement movement = new Movement();
        movement.setDate(movementEntity.getDate());
        movement.setMovementId(movementEntity.getMovementId());
        movement.setMovementType(movementEntity.getMovementType());
        movement.setQuantity(movementEntity.getQuantity());
        return movement;
    }

    @Override
    public MovementEntity mapCoreEntityToDatabaseEntity(Movement movement) {
        MovementEntity movementEntity = new MovementEntity();
        movementEntity.setDate(movement.getDate());
        movementEntity.setMovementId(movement.getMovementId());
        movementEntity.setMovementType(movement.getMovementType());
        movementEntity.setQuantity(movement.getQuantity());
        AccountEntity account = new AccountEntity();
        account.setAccountId(movement.getAccount().getAccountId());
        movementEntity.setAccount(account);
        return movementEntity;
    }

}
