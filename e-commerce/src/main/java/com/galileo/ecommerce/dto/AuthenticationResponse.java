package com.galileo.ecommerce.dto;

import lombok.Data;

@Data
public class AuthenticationResponse {
  private String jwtToken;

  public AuthenticationResponse(String jwt) {
    jwtToken = jwt;
  }
}
