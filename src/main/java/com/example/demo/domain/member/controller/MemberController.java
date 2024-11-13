package com.example.demo.domain.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.member.service.MemberCommandService;
import com.example.demo.global.kakao.KakaoLoginParams;
import com.example.demo.global.oAuth.AuthTokens;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

  private final MemberCommandService memberCommandService;

  @PostMapping("/kakao")
  public ResponseEntity<AuthTokens> loginKakao(@RequestBody KakaoLoginParams params) {
    return ResponseEntity.ok(memberCommandService.login(params));
  }
}
