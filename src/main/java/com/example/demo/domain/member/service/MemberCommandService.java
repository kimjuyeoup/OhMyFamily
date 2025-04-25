package com.example.demo.domain.member.service;

import org.springframework.beans.factory.annotation.Value;
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

  @Value("${jwt.refresh-token-validity}")
  private Long refreshTokenValidityMilliseconds;

  public AuthTokens login(OAuthLoginParams params) {
    OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
    Long userId = findOrCreateMember(oAuthInfoResponse);
    AuthTokens authTokens = authTokensGenerator.generate(userId);
    Member member =
        memberRepository
            .findById(userId)
            .orElseThrow(() -> new GlobalException(GlobalErrorCode.NOT_FOUND_MEMBER));
    member.setRefreshToken(authTokens.getRefreshToken());
    memberRepository.save(member);
    return authTokens;
  }

  public Long findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
    return memberRepository
        .findByKakaoId(oAuthInfoResponse.getKakaoId())
        .map(Member::getId)
        .orElseGet(() -> newUser(oAuthInfoResponse));
  }

  private Long newUser(OAuthInfoResponse oAuthInfoResponse) {
    return memberRepository
        .save(
            Member.builder()
                .kakaoId(oAuthInfoResponse.getKakaoId())
                .kakaoNickname(oAuthInfoResponse.getKakaoNickname())
                .build())
        .getId();
  }

  public AuthTokens reissue(KakaoReissueParams params) {

    String refreshToken = params.getRefreshToken();

    Long memberId = jwtTokenProvider.parseRefreshToken(refreshToken);

    Member member =
        memberRepository
            .findById(memberId)
            .orElseThrow(() -> new GlobalException(GlobalErrorCode.NOT_FOUND_MEMBER));

    if (!refreshToken.equals(member.getRefreshToken())) {
      throw new GlobalException(GlobalErrorCode.INVALID_TOKEN);
    }

    String newAccessToken = jwtTokenProvider.generateAccessToken(member.getId());
    String newRefreshToken = jwtTokenProvider.generateRefreshToken(member.getId());

    member.setRefreshToken(newRefreshToken);

    return AuthTokens.of(newAccessToken, newRefreshToken);
  }
}
