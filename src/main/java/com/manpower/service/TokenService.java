package com.manpower.service;


import com.manpower.dto.Token;
import com.manpower.dto.UserRole;

import java.util.List;
import java.util.Map;

public interface TokenService {
  public Token generateToken(UserRole role, String userID, String companyId, boolean allowERP, boolean allowPOS);
}
