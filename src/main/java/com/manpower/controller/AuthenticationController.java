package com.manpower.controller;

import com.manpower.common.Contants;
import com.manpower.model.User;
import com.manpower.model.dto.AuthenticateRequest;
import com.manpower.model.dto.AuthenticateResponse;
import com.manpower.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

  private final UserService userService;

  @PostMapping("/login")
  public ResponseEntity<AuthenticateResponse> getUserById(@RequestBody AuthenticateRequest authenticate) {

    Optional<User> user = userService.authenticate(authenticate);

    if(user.isEmpty())
    {
      throw new RuntimeException("Invalid username or password");
    }

    AuthenticateResponse authenticateResponse = AuthenticateResponse.builder()
      .success(true)
      .JWToken("SAMPLE_TOKEN")
      .role(Contants.Role.fromValue(user.get().getRole()))
      .build();

    return ResponseEntity.ok(authenticateResponse);
  }


}
