package com.example.demo.domain.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/kakao")
public class KakaoController {

  @GetMapping("callback")
  public ResponseEntity<?> kakaoCallback(
      @RequestParam(value = "code", required = false) String code,
      @RequestParam(value = "error", required = false) String error,
      @RequestParam(value = "error_description", required = false) String errorDescription) {
    return ResponseEntity.status(HttpStatus.OK).body("good");
  }
}
