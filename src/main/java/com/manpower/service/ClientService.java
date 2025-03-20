package com.manpower.service;

import com.manpower.common.Contants;
import com.manpower.model.Client;
import com.manpower.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Integer id) {
        return clientRepository.findById(id);
    }

    @Transactional
    public Client createClient(Client client) {

        client.setStatus(Contants.StatusInt.ACTIVE.getValue());
        return clientRepository.save(client);
    }


    @Transactional
    public Client updateClient(Integer id, Client client) {
        return clientRepository.findById(id).map(existingClient -> {
            existingClient.setClientId(client.getClientId());
            existingClient.setName(client.getName());
            existingClient.setAddress(client.getAddress());
            return clientRepository.save(existingClient);
        }).orElseThrow(() -> new RuntimeException("Client not found"));
    }

    @Transactional
    public void deleteClient(Integer id) {
        clientRepository.findById(id).ifPresent(client -> {
            client.setStatus(Contants.StatusInt.ACTIVE.getValue());
            clientRepository.save(client);
        });
    }}
