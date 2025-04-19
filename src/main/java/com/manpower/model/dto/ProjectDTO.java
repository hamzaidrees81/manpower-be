package com.manpower.model.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    private Integer id;
    private Integer companyId;
    private ClientDTO client;
    private Integer clientId;
    private String clientName;
    private String projectId;
    private String name;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
}
