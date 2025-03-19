package com.manpower.model.dto;

import lombok.Data;

@Data
public class AuthenticateRequest {
  String email;
  String password;
}
