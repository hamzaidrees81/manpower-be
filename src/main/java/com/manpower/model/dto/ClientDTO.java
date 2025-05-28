package com.manpower.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ClientDTO {
    private Integer id;
    private String clientId;
    private String name;
    private String address;
    private Integer status;
    private Integer companyId; // just include the company ID to avoid deep nesting
    private String phoneNumber; // //fix

}
