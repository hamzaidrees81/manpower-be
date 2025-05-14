package com.manpower.service;

import com.manpower.common.Contants;
import com.manpower.mapper.ClientMapper;
import com.manpower.model.Client;
import com.manpower.model.dto.ClientDTO;
import com.manpower.repository.ClientRepository;
import com.manpower.util.SecurityUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientDTO> getAllClients() {
        return clientRepository.findAllByCompany_Id(SecurityUtil.getCompanyClaim()).stream().map(ClientMapper::toDTO).collect(Collectors.toList());
    }

    public List<Client> getAllClientsRaw() {
        return clientRepository.findAllByCompany_Id(SecurityUtil.getCompanyClaim());
    }

    public Optional<Client> getClientById(Integer id) {
        return clientRepository.findById(id);
    }

    @Transactional
    public ClientDTO createClient(ClientDTO client) {

        client.setStatus(Contants.StatusInt.ACTIVE.getValue());
        client.setCompanyId(SecurityUtil.getCompanyClaim());
        Client newClient = clientRepository.save(ClientMapper.toEntity(client));
        return ClientMapper.toDTO(newClient);
    }


    @Transactional
    public ClientDTO updateClient(Integer id, ClientDTO client) {
        Client updatedClient = clientRepository.findById(id).map(existingClient -> {
            existingClient.setClientId(client.getClientId());
            existingClient.setName(client.getName());
            existingClient.setAddress(client.getAddress());
            return clientRepository.save(existingClient);
        }).orElseThrow(() -> new RuntimeException("Client not found"));
        return ClientMapper.toDTO(updatedClient);
    }

    @Transactional
    public void deleteClient(Integer id) {
        clientRepository.findById(id).ifPresent(client -> {
            client.setStatus(Contants.StatusInt.ACTIVE.getValue());
            clientRepository.save(client);
        });
    }}
