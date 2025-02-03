package com.example.demo.global.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GlobalErrorCode {
  ACCESS_DENIED(HttpStatus.FORBIDDEN, "u-001", "권한이 없습니다."),
  AUTHENTICATION_REQUIRED(HttpStatus.UNAUTHORIZED, "u-002", "인증이 필요합니다."),
  NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "u-003", "회원을 찾을 수 없습니다."),
  TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "u-004", "토큰의 유효기간이 지났습니다."),
  INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "u-005", "잘못된 토큰입니다.");

  private final HttpStatus httpStatus;
  private final String divisionCode;
  private final String message;
}
