package com.example.demo.domain.member.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.domain.member.dto.response.GetMemberResponseDto;
import com.example.demo.domain.member.service.MemberCommandService;
import com.example.demo.domain.member.service.MemberQueryService;
import com.example.demo.global.exception.BaseResponse;
import com.example.demo.global.kakao.KakaoLoginParams;
import com.example.demo.global.kakao.KakaoReissueParams;
import com.example.demo.global.oAuth.AuthTokens;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

  private final MemberCommandService memberCommandService;
  private final MemberQueryService memberQueryService;

  @PostMapping("/kakao")
  public BaseResponse<AuthTokens> loginKakao(@RequestBody KakaoLoginParams params) {
    return BaseResponse.onSuccess(memberCommandService.login(params));
  }

  @PostMapping("/reissue")
  public BaseResponse<AuthTokens> reissue(@RequestBody KakaoReissueParams params) {
    return BaseResponse.onSuccess(memberCommandService.reissue(params));
  }

  @GetMapping
  public BaseResponse<GetMemberResponseDto> getMember(
      @RequestHeader("Authorization") String accessToken) {
    return BaseResponse.onSuccess(memberQueryService.getMemberResponseDto(accessToken));
  }
}
