package com.devsu.devsu.infrastructure.repository.mysql.mappers;

import org.springframework.stereotype.Service;

import com.devsu.devsu.core.model.Account;
import com.devsu.devsu.infrastructure.repository.entities.AccountEntity;

@Service
public class AccountMapper implements Mapper<Account, AccountEntity> {

    @Override
    public Account mapDatabaseEntityToCoreEntity(AccountEntity u) {
        Account account = new Account();
        account.setAccountId(u.getAccountId());
        account.setAccountNumber(u.getAccountNumber());
        account.setAccountType(u.getAccountType());
        account.setBalance(u.getBalance());
        account.setState(u.isState());
        return account;
    }

    @Override
    public AccountEntity mapCoreEntityToDatabaseEntity(Account t) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountId(t.getAccountId());
        accountEntity.setAccountNumber(t.getAccountNumber());
        accountEntity.setAccountType(t.getAccountType());
        accountEntity.setBalance(t.getBalance());
        accountEntity.setState(t.isState());
        return accountEntity;
    }
}
