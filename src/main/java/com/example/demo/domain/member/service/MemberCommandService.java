package com.example.demo.domain.member.service;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.global.oAuth.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandService {

  private final MemberRepository memberRepository;
  private final AuthTokensGenerator authTokensGenerator;
  private final RequestOAuthInfoService requestOAuthInfoService;

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
}
