package com.manpower.controller;

import com.manpower.common.Contants;
import com.manpower.dto.UserRole;
import com.manpower.model.User;
import com.manpower.model.dto.AuthenticateRequest;
import com.manpower.model.dto.AuthenticateResponse;
import com.manpower.service.TokenService;
import com.manpower.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "") // Exclude from authentication
public class AuthenticationController {

  private final UserService userService;
  private final TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity<AuthenticateResponse> getUserById(@RequestBody AuthenticateRequest authenticate) {

    Optional<User> user = userService.authenticate(authenticate);

    if(user.isEmpty())
    {
      throw new RuntimeException("Invalid username or password");
    }

    String userId = user.get().getId().toString();
    String companyId = user.get().getCompany().getId().toString();

    AuthenticateResponse authenticateResponse = AuthenticateResponse.builder()
      .success(true)
      .JWToken(tokenService.generateToken(UserRole.ADMIN, userId, companyId).getAccessToken())
      .role(Contants.Role.fromValue(user.get().getRole()))
      .build();

    return ResponseEntity.ok(authenticateResponse);
  }


}
