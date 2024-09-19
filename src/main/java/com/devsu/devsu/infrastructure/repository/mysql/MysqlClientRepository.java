package com.devsu.devsu.infrastructure.repository.mysql;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.devsu.devsu.infrastructure.repository.entities.ClientEntity;

@Repository
public interface MysqlClientRepository extends CrudRepository<ClientEntity, Long> {

    @Query("SELECT c FROM ClientEntity c WHERE c.identification = ?1")
    Optional<ClientEntity> findClientEntityByIdentification(String identification);
    
}
