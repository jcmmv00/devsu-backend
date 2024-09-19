package com.devsu.devsu.core.services;

import com.devsu.devsu.core.exceptions.ClientAlreadyExistException;
import com.devsu.devsu.core.exceptions.ClientNotFoundException;
import com.devsu.devsu.core.model.Client;

public interface ClientService {

    Long createClient(Client client) throws ClientAlreadyExistException;

    Client getClient(String clientId) throws ClientNotFoundException;

    boolean updateClient(Client client) throws ClientNotFoundException;

    boolean deleteClient(String clientId) throws ClientNotFoundException;
}
