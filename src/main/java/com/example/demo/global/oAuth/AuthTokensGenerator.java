package com.example.demo.global.oAuth;

import org.springframework.stereotype.Component;

import com.example.demo.global.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthTokensGenerator {

  private static final String BEARER = "Bearer";

  private final JwtTokenProvider jwtTokenProvider;

  public AuthTokens generate(Long memberId) {

    String accessToken = jwtTokenProvider.generateAccessToken(memberId);
    String refreshToken = jwtTokenProvider.generateRefreshToken(memberId);

    return new AuthTokens(accessToken, refreshToken);
  }

  public Long extractMemberId(String accessToken) {
    return Long.valueOf(jwtTokenProvider.extractSubject(accessToken));
  }
}
