package com.devsu.devsu.core.ports;

import java.util.Optional;

import com.devsu.devsu.core.model.Client;

public interface ClientRepositoryPort {

    Long create(Client client);

    Optional<Client> findClientEntityByIdentification(String identificacion);

    boolean deleteClient(String clientId);

    boolean updateClient(Client client);
}
