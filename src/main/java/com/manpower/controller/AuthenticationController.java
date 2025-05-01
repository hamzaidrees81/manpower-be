package com.manpower.controller;

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

  @PostMapping("/api/login")
  public ResponseEntity<AuthenticateResponse> getUserById(@RequestBody AuthenticateRequest authenticate) {

    Optional<User> user = userService.authenticate(authenticate);

    if(user.isEmpty())
    {
      throw new RuntimeException("Invalid username or password");
    }

    String userId = user.get().getId().toString();
    String companyId = user.get().getCompany().getId().toString();

    boolean allowERP;
    boolean allowPOS;
    boolean isAdmin = false;

    switch (UserRole.valueOf(user.get().getRole())) {
      case ADMIN:
        allowERP = true;
        allowPOS = true;
        isAdmin = true;
        break;
      case POS_ADMIN:
        allowERP = false;
        allowPOS = true;
        isAdmin = true;
          break;
      case ERP_ADMIN:
        allowERP = true;
        allowPOS = false;
        isAdmin = true;
        break;
      case POS_ERP_USER:
        allowERP = true;
        allowPOS = true;
          break;
      case ERP_USER:
        allowERP = true;
        allowPOS = false;
        break;
      case POS_USER:
        allowERP = false;
        allowPOS = true;
        break;
              default:
                allowERP = false;
                allowPOS = false;
                break;
    }


    AuthenticateResponse authenticateResponse = AuthenticateResponse.builder()
      .success(true)
      .JWToken(tokenService.generateToken(UserRole.valueOf(user.get().getRole()), userId, companyId, allowERP, allowPOS).getAccessToken())
      .role(UserRole.valueOf(user.get().getRole()))
      .allowERP(allowERP)
      .allowPOS(allowPOS)
      .isAdmin(isAdmin)
      .build();

    return ResponseEntity.ok(authenticateResponse);
  }


}
