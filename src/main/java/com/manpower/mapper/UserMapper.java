package com.manpower.mapper;

import com.manpower.dto.UserRole;
import com.manpower.model.Company;
import com.manpower.model.User;
import com.manpower.model.dto.UserDTO;
import com.manpower.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setRole(UserRole.valueOf(user.getRole()));
        dto.setStatus(user.getStatus());
        dto.setCreateDate(user.getCreateDate());
        dto.setUpdateDate(user.getUpdateDate());
        return dto;
    }

    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole().name());
        user.setStatus(dto.getStatus());
        user.setCompany(Company.builder().id(SecurityUtil.getCompanyClaim()).build());
        return user;
    }
}
