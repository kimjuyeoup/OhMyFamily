package com.example.demo.domain.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.member.dto.response.GetMemberResponseDto;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.global.exception.GlobalErrorCode;
import com.example.demo.global.exception.GlobalException;
import com.example.demo.global.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryService {

  private final JwtTokenProvider jwtTokenProvider;
  private final MemberRepository memberRepository;

  public GetMemberResponseDto getMemberResponseDto(String accessToken) {

    Long memberId = jwtTokenProvider.getMemberIdFromToken(accessToken);

    Member member =
        memberRepository
            .findById(memberId)
            .orElseThrow(() -> new GlobalException(GlobalErrorCode.NOT_FOUND_MEMBER));

    return GetMemberResponseDto.fromEntity(member);
  }

  public Long getMemberId(String accessToken) {

    Long memberId = jwtTokenProvider.getMemberIdFromToken(accessToken);

    memberRepository
        .findById(memberId)
        .orElseThrow(() -> new GlobalException(GlobalErrorCode.NOT_FOUND_MEMBER));

    return memberId;
  }
}
