package com.devsu.devsu.infrastructure.repository.mysql;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

import org.springframework.stereotype.Service;

import com.devsu.devsu.core.model.Client;
import com.devsu.devsu.core.ports.ClientRepositoryPort;
import com.devsu.devsu.infrastructure.repository.entities.ClientEntity;
import com.devsu.devsu.infrastructure.repository.mysql.mappers.ClientMapper;

@Service
public class ClientRepository implements ClientRepositoryPort {

    private final MysqlClientRepository mysqlClientRepository;
    private final ClientMapper clientMapper;

    public ClientRepository(MysqlClientRepository clientRepository, ClientMapper clientMapper) {
        this.mysqlClientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public Long create(Client client) {
        return this.mysqlClientRepository.save(clientMapper.mapCoreEntityToDatabaseEntity(client)).getPersonId();
    }

    @Override
    public Optional<Client> findClientEntityByIdentification(String identificacion) {
        return mysqlClientRepository.findClientEntityByIdentification(identificacion)
                .map(clientMapper::mapDatabaseEntityToCoreEntity);
    }

     @Override
    public List<Client> findClients() {
        return ((List<ClientEntity>) mysqlClientRepository.findAll()).stream()
                .map(clientMapper::mapDatabaseEntityToCoreEntity).collect(Collectors.toList());
    }

    @Override
    public boolean deleteClient(String clientId) {
        Optional<ClientEntity> existingClient = mysqlClientRepository.findClientEntityByIdentification(clientId);
        if(existingClient.isPresent()) {
            mysqlClientRepository.delete(existingClient.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateClient(Client client) {
        Optional<ClientEntity> updatedClient = mysqlClientRepository
                .findClientEntityByIdentification(client.getIdentification()).map(clientFound -> {
                    clientFound.setAddress(client.getAddress());
                    clientFound.setLastName(client.getLastName());
                    clientFound.setName(client.getName());
                    clientFound.setPassword(client.getPassword());
                    clientFound.setAge(client.getAge());
                    clientFound.setState(client.isState());
                    clientFound.setTelephone(client.getTelephone());
                    clientFound.setGender(client.getGender());
                    return clientFound;
                });
        if (updatedClient.isPresent()) {
            mysqlClientRepository.save(updatedClient.get());
            return true;
        } else {
            return false;
        }
    }
}
