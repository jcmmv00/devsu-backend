package com.devsu.devsu.infrastructure.repository.mysql.mappers;

import org.springframework.stereotype.Service;

import com.devsu.devsu.core.model.Client;
import com.devsu.devsu.infrastructure.repository.entities.ClientEntity;

@Service
public class ClientMapper implements Mapper<Client, ClientEntity> {

    @Override
    public ClientEntity mapCoreEntityToDatabaseEntity(Client client) {
        ClientEntity clientEntity = new ClientEntity();
        // clientEntity.setAccounts(client.getAccounts().stream().map(account -> {
        //     AccountEntity accountEntity = new AccountEntity();
        //     accountEntity.setAccountId(account.getAccountId());
        //     accountEntity.setAccountNumber(account.getAccountNumber());
        //     accountEntity.setAccountType(account.getAccountType());
        //     accountEntity.setBalance(account.getBalance());
        //     return accountEntity;
        // }).toList());
        clientEntity.setAddress(client.getAddress());
        clientEntity.setAge(client.getAge());
        clientEntity.setGender(client.getGender());
        clientEntity.setIdentification(client.getIdentification());
        clientEntity.setLastName(client.getLastName());
        clientEntity.setName(client.getName());
        clientEntity.setPassword(client.getPassword());
        clientEntity.setState(client.isState());
        clientEntity.setTelephone(client.getTelephone());
        return clientEntity;
    }

    @Override
    public Client mapDatabaseEntityToCoreEntity(ClientEntity clientEntity) {
        Client client = new Client();
        // client.setAccounts(clientEntity.getAccounts().stream().map(accountEntity -> {
        //     Account account = new Account();
        //     account.setAccountId(accountEntity.getAccountId());
        //     account.setAccountNumber(accountEntity.getAccountNumber());
        //     account.setAccountType(accountEntity.getAccountType());
        //     account.setBalance(accountEntity.getBalance());
        //     return account;
        // }).toList());
        client.setAddress(clientEntity.getAddress());
        client.setAge(clientEntity.getAge());
        client.setGender(clientEntity.getGender());
        client.setIdentification(clientEntity.getIdentification());
        client.setLastName(clientEntity.getLastName());
        client.setName(clientEntity.getName());
        client.setPassword(clientEntity.getPassword());
        client.setPersonId(clientEntity.getPersonId());
        client.setState(clientEntity.isState());
        client.setTelephone(clientEntity.getTelephone());
        return client;
    }
}
