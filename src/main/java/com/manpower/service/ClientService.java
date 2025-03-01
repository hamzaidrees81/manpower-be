package com.manpower.service;

import com.manpower.model.Client;
import com.manpower.repository.ClientRepository;
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

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClient(Integer id) {
        clientRepository.deleteById(id);
    }

    public Client updateClient(Integer id, Client client) {
        throw new RuntimeException("Not implemented yet");

    }
}
