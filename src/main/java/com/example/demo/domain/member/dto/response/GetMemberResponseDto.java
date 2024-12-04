package com.example.demo.domain.member.dto.response;

import com.example.demo.domain.member.entity.Member;

import lombok.Builder;

@Builder
public record GetMemberResponseDto(Long memberId, String nickname) {

  public static GetMemberResponseDto fromEntity(Member member) {
    return GetMemberResponseDto.builder()
        .memberId(member.getId())
        .nickname(member.getKakaoNickname())
        .build();
  }
}
