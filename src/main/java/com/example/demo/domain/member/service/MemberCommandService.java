package com.example.demo.domain.member.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.global.exception.GlobalErrorCode;
import com.example.demo.global.exception.GlobalException;
import com.example.demo.global.jwt.JwtTokenProvider;
import com.example.demo.global.kakao.KakaoReissueParams;
import com.example.demo.global.oAuth.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandService {

  private final MemberRepository memberRepository;
  private final AuthTokensGenerator authTokensGenerator;
  private final RequestOAuthInfoService requestOAuthInfoService;
  private final JwtTokenProvider jwtTokenProvider;
  private final RedisTemplate<String, String> redisTemplate;

  @Value("${jwt.refresh-token-validity}")
  private Long refreshTokenValidityMilliseconds;

  public AuthTokens login(OAuthLoginParams params) {
    OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
    Long userId = findOrCreateMember(oAuthInfoResponse);
    return authTokensGenerator.generate(userId);
  }

  public Long findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
    return memberRepository
        .findByKakaoNickname(oAuthInfoResponse.getKakaoNickname())
        .map(Member::getId)
        .orElseGet(() -> newUser(oAuthInfoResponse));
  }

  private Long newUser(OAuthInfoResponse oAuthInfoResponse) {
    Member member = Member.builder().kakaoNickname(oAuthInfoResponse.getKakaoNickname()).build();

    return memberRepository.save(member).getId();
  }

  public AuthTokens reissue(KakaoReissueParams params) {

    String refreshToken = params.getRefreshToken();

    Long memberId = jwtTokenProvider.parseRefreshToken(refreshToken);

    if (!refreshToken.equals(redisTemplate.opsForValue().get(memberId.toString()))) {
      throw new GlobalException(GlobalErrorCode.INVALID_TOKEN);
    }

    Member member =
        memberRepository
            .findById(memberId)
            .orElseThrow(() -> new GlobalException(GlobalErrorCode.NOT_FOUND_MEMBER));

    String newAccessToken = jwtTokenProvider.generateAccessToken(member.getId());
    String newRefreshToken = jwtTokenProvider.generateRefreshToken(member.getId());

    redisTemplate
        .opsForValue()
        .set(
            member.getId().toString(),
            newRefreshToken,
            refreshTokenValidityMilliseconds,
            TimeUnit.MILLISECONDS);

    return AuthTokens.of(newAccessToken, newRefreshToken);
  }
}
