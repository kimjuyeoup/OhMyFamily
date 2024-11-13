package com.example.demo.global.oAuth;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.example.demo.global.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthTokensGenerator {

  private static final String BEARER = "Bearer";
  private static final long ACCESS_TOKEN_EXPIRED_TIME = 1000 * 60 * 30; // 30분
  private static final long REFRESH_TOKEN_EXPIRED_TIME = 1000 * 60 * 60; // 1시간

  private final JwtTokenProvider jwtTokenProvider;

  public AuthTokens generate(Long memberId) {
    long now = (new Date()).getTime();

    Date accessTokenExpiredTime = new Date(now + ACCESS_TOKEN_EXPIRED_TIME);
    Date refreshTokenExpiredTime = new Date(now + REFRESH_TOKEN_EXPIRED_TIME);

    String subject = memberId.toString();
    String accessToken = jwtTokenProvider.generate(subject, accessTokenExpiredTime);
    String refreshToken = jwtTokenProvider.generate(subject, refreshTokenExpiredTime);

    return AuthTokens.of(accessToken, refreshToken, BEARER, ACCESS_TOKEN_EXPIRED_TIME / 1000L);
  }

  public Long extractMemberId(String accessToken) {
    return Long.valueOf(jwtTokenProvider.extractSubject(accessToken));
  }
}
