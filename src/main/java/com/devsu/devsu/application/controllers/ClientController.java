package com.devsu.devsu.application.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.devsu.application.commands.ClientCreationDTO;
import com.devsu.devsu.application.commands.ClientUpdateDTO;
import com.devsu.devsu.core.model.Client;
import com.devsu.devsu.core.exceptions.ClientAlreadyExistException;
import com.devsu.devsu.core.exceptions.ClientNotFoundException;
import com.devsu.devsu.core.services.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping()
    public ResponseEntity<Long> create(@RequestBody @Valid ClientCreationDTO clientCreationDTO)
            throws ClientAlreadyExistException {
        Client client = new Client();
        client.setAddress(clientCreationDTO.getAddress());
        client.setAge(clientCreationDTO.getAge());
        client.setGender(clientCreationDTO.getGender());
        client.setIdentification(clientCreationDTO.getIdentification());
        client.setLastName(clientCreationDTO.getLastName());
        client.setName(clientCreationDTO.getName());
        client.setPassword(clientCreationDTO.getPassword());
        client.setTelephone(clientCreationDTO.getTelephone());
        client.setState(clientCreationDTO.isState());
        return ResponseEntity.created(null).body(clientService.createClient(client));
    }

    @PutMapping()
    public ResponseEntity<Object> update(@RequestBody @Valid ClientUpdateDTO clientUpdateDTO)
            throws ClientNotFoundException {
        Client client = new Client();
        client.setAddress(clientUpdateDTO.getAddress());
        client.setAge(clientUpdateDTO.getAge());
        client.setGender(clientUpdateDTO.getGender());
        client.setIdentification(clientUpdateDTO.getIdentification());
        client.setLastName(clientUpdateDTO.getLastName());
        client.setName(clientUpdateDTO.getName());
        client.setPassword(clientUpdateDTO.getPassword());
        client.setTelephone(clientUpdateDTO.getTelephone());
        client.setState(clientUpdateDTO.isState());
        if (clientService.updateClient(client)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{identification}")
    public ResponseEntity<Client> getClient(@PathVariable String identification) throws ClientNotFoundException {
        Client client = clientService.getClient(identification);
        return ResponseEntity.ok(client);
    }

    @GetMapping()
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @DeleteMapping("/{identification}")
    public ResponseEntity<String> deleteClient(@PathVariable String identification) throws ClientNotFoundException {
        if (clientService.deleteClient(identification)) {
            return ResponseEntity.ok("Deleted client with Id " + identification);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
