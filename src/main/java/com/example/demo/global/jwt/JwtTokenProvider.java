package com.example.demo.global.jwt;

import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.global.exception.GlobalErrorCode;
import com.example.demo.global.exception.TokenException;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

  private final Key key;
  private final RedisTemplate<String, String> redisTemplate;
  private final long accessTokenValidityMilliseconds;
  private final long refreshTokenValidityMilliseconds;

  public JwtTokenProvider(
      @Value("${jwt.secret}") String secretKey,
      RedisTemplate<String, String> redisTemplate,
      @Value("${jwt.access-token-validity}") final long accessTokenValidityMilliseconds,
      @Value("${jwt.refresh-token-validity}") final long refreshTokenValidityMilliseconds) {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    this.key = Keys.hmacShaKeyFor(keyBytes);
    this.redisTemplate = redisTemplate;
    this.accessTokenValidityMilliseconds = accessTokenValidityMilliseconds;
    this.refreshTokenValidityMilliseconds = refreshTokenValidityMilliseconds;
  }

  private String generateToken(Long userId, long validityMilliseconds) {
    Claims claims = Jwts.claims();

    ZonedDateTime now = ZonedDateTime.now();
    ZonedDateTime tokenValidity = now.plusSeconds(validityMilliseconds / 1000);

    return Jwts.builder()
        .setClaims(claims)
        .setSubject(userId.toString())
        .setIssuedAt(Date.from(now.toInstant()))
        .setExpiration(Date.from(tokenValidity.toInstant()))
        .setIssuer("OMF")
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
  }

  public String generateAccessToken(Long userId) {
    return generateToken(userId, accessTokenValidityMilliseconds);
  }

  public String generateRefreshToken(Long userId) {
    String refreshToken = generateToken(userId, refreshTokenValidityMilliseconds);
    redisTemplate
        .opsForValue()
        .set(
            userId.toString(),
            refreshToken,
            refreshTokenValidityMilliseconds,
            TimeUnit.MILLISECONDS);
    return refreshToken;
  }

  public String extractSubject(String accessToken) {
    Claims claims = parseClaims(accessToken);
    return claims.getSubject();
  }

  public Long getMemberIdFromToken(String accessToken) {
    return Long.valueOf(getClaims(accessToken).getBody().getSubject());
  }

  public Claims parseClaims(String accessToken) {
    try {
      return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
    } catch (ExpiredJwtException e) {
      return e.getClaims();
    }
  }

  private Jws<Claims> getClaims(String token) {
    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
  }

  public boolean isTokenValid(String token) {
    System.out.println("12");
    try {
      System.out.println("13");
      Jws<Claims> claims = getClaims(token);
      System.out.println("14");
      Date expiredDate = claims.getBody().getExpiration();
      System.out.println("15 " + expiredDate.toString());
      return expiredDate.after(new Date());
    } catch (ExpiredJwtException e) {
      System.out.println("16");
      throw new TokenException(GlobalErrorCode.INVALID_TOKEN);
    } catch (SecurityException
        | MalformedJwtException
        | UnsupportedJwtException
        | IllegalArgumentException e) {
      System.out.println("17");
      throw new TokenException(GlobalErrorCode.INVALID_TOKEN);
    }
  }

  public Long parseRefreshToken(String token) {
    if (isTokenValid(token)) {
      Claims claims = getClaims(token).getBody();
      return Long.parseLong(claims.getSubject());
    }
    throw new TokenException(GlobalErrorCode.NOT_FOUND_MEMBER);
  }
}
