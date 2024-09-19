package com.devsu.devsu.infrastructure.repository.mysql;

import com.devsu.devsu.infrastructure.repository.entities.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MysqlPersonRepository extends CrudRepository<PersonEntity, Long> {

}
