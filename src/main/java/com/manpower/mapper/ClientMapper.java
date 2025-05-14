package com.manpower.mapper;

import com.manpower.model.Client;
import com.manpower.model.dto.ClientDTO;

public class ClientMapper {

    public static ClientDTO toDTO(Client client) {
        if (client == null) return null;

        return ClientDTO.builder()
                .id(client.getId())
                .clientId(client.getClientId())
                .name(client.getName())
                .address(client.getAddress())
                .status(client.getStatus())
                .companyId(client.getCompany().getId())
                .build();
    }

    public static Client toEntity(ClientDTO dto) {
        if (dto == null) return null;

        Client client = new Client();
        client.setId(dto.getId());
        client.setClientId(dto.getClientId());
        client.setName(dto.getName());
        client.setAddress(dto.getAddress());
        client.setStatus(dto.getStatus());

        // You must set Company separately in the service layer since only ID is available in DTO
        return client;
    }
}
