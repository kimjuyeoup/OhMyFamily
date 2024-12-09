package com.example.demo.global.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GlobalErrorCode {
  NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "회원을 찾을 수 없습니다."),
  INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "잘못된 토큰입니다.");

  private final HttpStatus httpStatus;
  private final String message;
}
