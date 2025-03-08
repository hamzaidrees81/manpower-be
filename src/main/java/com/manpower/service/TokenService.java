package com.manpower.service;


import com.manpower.dto.Token;
import com.manpower.dto.UserRole;

public interface TokenService {
  public Token generateToken(UserRole role, String userID, String companyId);
}
