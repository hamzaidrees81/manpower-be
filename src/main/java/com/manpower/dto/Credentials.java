package com.manpower.dto;

import lombok.Data;

@Data
public class Credentials {
  String username;
  String password;
  UserRole authority;
}
