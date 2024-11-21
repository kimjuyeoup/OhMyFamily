package com.example.demo.global.oAuth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthTokens {

  private String accessToken;
  private String refreshToken;

  public static AuthTokens of(String accessToken, String refreshToken) {
    return new AuthTokens(accessToken, refreshToken);
  }
}
