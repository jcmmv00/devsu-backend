package com.devsu.devsu.infrastructure.repository.mysql;

import com.devsu.devsu.core.model.Account;
import com.devsu.devsu.core.ports.AccountRepositoryPort;
import com.devsu.devsu.infrastructure.repository.entities.AccountEntity;
import com.devsu.devsu.infrastructure.repository.entities.ClientEntity;
import com.devsu.devsu.infrastructure.repository.mysql.mappers.AccountMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class AccountRepository implements AccountRepositoryPort {

    private final MysqlAccountRepository mysqlAccountRepository;
    private final AccountMapper accountMapper;

    public AccountRepository(MysqlAccountRepository mysqlAccountRepository, AccountMapper accountMapper) {
        this.mysqlAccountRepository = mysqlAccountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public Optional<Account> getAccountByAccountNumber(String accountNumber) {
        return this.mysqlAccountRepository.findAccountByAccountNumber(accountNumber)
                .map(accountMapper::mapDatabaseEntityToCoreEntity);
    }

    @Override
    public boolean updateAccount(Account account) {
        Optional<AccountEntity> updatedAccount = mysqlAccountRepository
                .findAccountByAccountNumber(account.getAccountNumber()).map(accountFound -> {
                    accountFound.setBalance(account.getBalance());
                    accountFound.setState(account.isState());
                    return accountFound;
                });

        if (updatedAccount.isPresent()) {
            mysqlAccountRepository.save(updatedAccount.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String create(Account account) {

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountNumber(account.getAccountNumber());
        accountEntity.setAccountType(account.getAccountType());
        accountEntity.setBalance(account.getBalance());
        accountEntity.setState(account.isState());
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setPersonId(account.getClient().getPersonId());
        accountEntity.setClient(clientEntity);

        return this.mysqlAccountRepository.save(accountEntity)
                .getAccountNumber();
    }

    @Override
    public boolean deleteAccount(String accountNumber) {
        Optional<AccountEntity> existingAccount = mysqlAccountRepository.findAccountByAccountNumber(accountNumber);
        if (existingAccount.isPresent()) {
            mysqlAccountRepository.delete(existingAccount.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Account> getAllAcounts() {
        return ((List<AccountEntity>) mysqlAccountRepository.findAll()).stream()
                .map(accountMapper::mapDatabaseEntityToCoreEntity).collect(Collectors.toList());
    }

}
