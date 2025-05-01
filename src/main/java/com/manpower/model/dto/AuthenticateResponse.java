package com.manpower.model.dto;

import com.manpower.common.Contants;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticateResponse {
  boolean success;
  String JWToken;
  Contants.Role role;
  boolean allowERP = false;
  boolean allowPOS = false;
}
