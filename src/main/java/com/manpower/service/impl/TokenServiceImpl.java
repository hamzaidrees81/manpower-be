package com.manpower.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.manpower.common.Contants;
import com.manpower.common.Contants.RateType.Claims;
import com.manpower.dto.Token;
import com.manpower.dto.UserRole;
import com.manpower.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

  private final Algorithm algorithm;
  @Value("${config.jwt.issuer}")
  private String issuer;

  @Value("${config.jwt.expirymilliseconds}")
  private long expiryOffset;

  @Override
  public Token generateToken(UserRole role, String userID, String companyId, boolean allowERP, boolean allowPOS) {

    String jwtToken = JWT.create()
      .withJWTId(UUID.randomUUID().toString())
      .withSubject("security-demo-token")
      .withIssuer(issuer)
      .withAudience("security-demo-audience")
      .withClaim("roles", List.of(role.toString()))
      .withClaim(Claims.USER_ID.name(), userID)
      .withClaim(Claims.COMPANY_ID.name(), companyId)
      .withExpiresAt(new Date().toInstant().plusMillis(expiryOffset))
      .withClaim(Claims.ALLOW_ERP.name(),allowERP)
      .withClaim(Claims.ALLOW_POS.name(),allowPOS)
      .sign(algorithm); //sign the token

    return Token.builder()
      .accessToken(jwtToken)
      .build();
  }
}
