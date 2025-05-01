package com.manpower.model.dto;

import com.manpower.dto.UserRole;
import lombok.Data;

import java.time.Instant;

@Data
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private com.manpower.model.dto.CompanyDTO company;
    private UserRole role;
    private Integer status;
    private Instant createDate;
    private Instant updateDate;
}
