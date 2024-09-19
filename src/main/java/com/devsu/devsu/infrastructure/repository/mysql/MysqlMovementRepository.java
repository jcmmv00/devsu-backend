package com.devsu.devsu.infrastructure.repository.mysql;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.devsu.devsu.core.model.enums.MovementType;
import com.devsu.devsu.infrastructure.repository.entities.MovementEntity;

@Repository
public interface MysqlMovementRepository extends CrudRepository<MovementEntity, Long> {

    @Query("SELECT m FROM MovementEntity m WHERE FUNCTION('DATE', m.date) = FUNCTION('DATE', ?1) AND m.movementType = ?2 AND m.account.accountId = ?3")
    List<MovementEntity> findMovementsByDateAndType(Date date, MovementType movementType, Long accountId);

    @Query("SELECT m FROM MovementEntity m WHERE m.date BETWEEN ?1 AND ?2 AND m.account.accountId = ?3")
    List<MovementEntity> findMovementsByRangeDate(Date starDate, Date endDate, Long accountId);

}
