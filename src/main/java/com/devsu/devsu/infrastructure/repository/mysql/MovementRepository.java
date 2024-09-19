package com.devsu.devsu.infrastructure.repository.mysql;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.devsu.devsu.core.model.Movement;
import com.devsu.devsu.core.model.enums.MovementType;
import com.devsu.devsu.core.ports.MovementRepositoryPort;
import com.devsu.devsu.infrastructure.repository.mysql.mappers.MovementMapper;

@Service
public class MovementRepository implements MovementRepositoryPort {

    private final MysqlMovementRepository mysqlMovementRepository;
    private final MovementMapper movementMapper;

    public MovementRepository(MysqlMovementRepository movementRepository, MovementMapper movementMapper) {
        this.mysqlMovementRepository = movementRepository;
        this.movementMapper = movementMapper;
    }

    @Override
    public Long create(Movement movement) {
        return mysqlMovementRepository.save(movementMapper.mapCoreEntityToDatabaseEntity(movement)).getMovementId();
    }

    @Override
    public List<Movement> getMovementsByDateAndType(Date date, MovementType movementType, Long accountId) {
        return mysqlMovementRepository.findMovementsByDateAndType(date, movementType, accountId).stream()
                .map(movementMapper::mapDatabaseEntityToCoreEntity).toList();
    }

    @Override
    public List<Movement> getMovementsByRangeDate(Date startDate, Date endDate, Long accountId) {
        
        return mysqlMovementRepository.findMovementsByRangeDate(startDate, endDate, accountId).stream()
                .map(movementMapper::mapDatabaseEntityToCoreEntity).toList();
    }

}
