package com.devsu.devsu.core.services.handlers;

import org.springframework.stereotype.Service;
import java.util.Optional;

import com.devsu.devsu.core.exceptions.ClientAlreadyExistException;
import com.devsu.devsu.core.exceptions.ClientNotFoundException;
import com.devsu.devsu.core.model.Client;
import com.devsu.devsu.core.ports.ClientRepositoryPort;
import com.devsu.devsu.core.services.ClientService;

import jakarta.validation.Valid;

@Service
public class ClientServiceHandler implements ClientService {

    private final ClientRepositoryPort clientRepositoryPort;

    public ClientServiceHandler(ClientRepositoryPort clientRepositoryPort) {
        this.clientRepositoryPort = clientRepositoryPort;
    }

    @Override
    public Client getClient(String clientId) throws ClientNotFoundException {
        return clientRepositoryPort.findClientEntityByIdentification(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));
    }

    @Override
    public Long createClient(@Valid Client client) throws ClientAlreadyExistException {
        Optional<Client> existingClient = clientRepositoryPort
                .findClientEntityByIdentification(client.getIdentification());
        if (existingClient.isPresent()) {
            throw new ClientAlreadyExistException(client.getIdentification());
        } else {
            return clientRepositoryPort.create(client);
        }
    }

    @Override
    public boolean deleteClient(String clientId) throws ClientNotFoundException {
        boolean isDeleted = clientRepositoryPort.deleteClient(clientId);
        if (isDeleted) {
            return isDeleted;
        } else {
            throw new ClientNotFoundException(clientId);
        }
    }

    @Override
    public boolean updateClient(Client client) throws ClientNotFoundException {
        boolean isUpdated = clientRepositoryPort.updateClient(client);
        if (isUpdated) {
            return isUpdated;
        } else {
            throw new ClientNotFoundException(client.getIdentification());
        }
    }
}
