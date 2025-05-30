package com.manpower.mapper;

import com.manpower.model.Client;
import com.manpower.model.Company;
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
                .companyId(client.getCompany() != null ? client.getCompany().getId() : null)
                .phoneNumber(client.getPhoneNumber())
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
        client.setPhoneNumber(dto.getPhoneNumber());
        client.setCompany(Company.builder().id(dto.getCompanyId()).build());

        // You must set Company separately in the service layer since only ID is available in DTO
        return client;
    }
}
