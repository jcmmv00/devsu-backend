package com.devsu.devsu.infrastructure.repository.mysql;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.devsu.devsu.infrastructure.repository.entities.AccountEntity;
import java.util.Optional;

@Repository
public interface MysqlAccountRepository extends CrudRepository<AccountEntity, Long>{
    @Query("SELECT a FROM AccountEntity a WHERE a.accountNumber = ?1")
    Optional<AccountEntity> findAccountByAccountNumber(String accountNumber);
}
