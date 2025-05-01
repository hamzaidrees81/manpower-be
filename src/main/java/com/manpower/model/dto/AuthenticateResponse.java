package com.manpower.model.dto;

import com.manpower.common.Contants;
import com.manpower.dto.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticateResponse {
  boolean success;
  String JWToken;
  UserRole role;
  boolean allowERP = false;
  boolean allowPOS = false;
}
